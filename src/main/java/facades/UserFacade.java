package facades;

import dtos.Role.RolesDTO;
import dtos.User.UserDTO;
import dtos.User.UsersDTO;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

import java.util.List;

public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public Long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(u.userName) FROM User u", Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    //TODO: Error handling if username already exists in the database
    public UserDTO registerUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        User user = new User(username, password);
        user.addRole(new Role("customer"));

        try {

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            return new UserDTO(user);

        } finally {
            em.close();
        }
    }

    public RolesDTO getRolesByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Role> query = em.createQuery("SELECT r from Role r JOIN r.userList u WHERE u.userName =:user_name ", Role.class);
            query.setParameter("user_name", username);
            List<Role> roles = query.getResultList();

            return new RolesDTO(roles);

        } finally {
            em.close();
        }
    }



    public UserDTO getByUsername(String username){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName =:user_name",User.class);
            query.setParameter("user_name", username);
            User user = query.getSingleResult();
            return new UserDTO(user);

        } finally {
            em.close();
        }

    }

    public UsersDTO getAllUsers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT u FROM User u JOIN u.roleList r WHERE r.roleName =:customer", User.class);
            query.setParameter("customer","customer");
            List<User> results = query.getResultList();
            return new UsersDTO(results);
        } finally {
            em.close();
        }
    }
}
