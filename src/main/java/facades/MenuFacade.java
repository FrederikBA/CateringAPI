package facades;

import dtos.Course.CourseDTO;
import dtos.Course.CoursesDTO;
import dtos.Menu.MenuDTO;
import dtos.Menu.MenusDTO;
import entities.Course;
import entities.Menu;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class MenuFacade {
    private static EntityManagerFactory emf;
    private static MenuFacade instance;

    private MenuFacade() {
    }

    public static MenuFacade getMenuFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MenuFacade();
        }
        return instance;
    }

    //TODO: error handling if user does not exist
    public MenuDTO createMenu(String username, MenuDTO menuDTO) {
        EntityManager em = emf.createEntityManager();
        double totalPrice = 0;

        TypedQuery<User> query = em.createQuery("SELECT u from User u WHERE u.userName =:username", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();

        Menu menu = new Menu(menuDTO.getDeliveryDate(), menuDTO.getServings(), menuDTO.getDeliveryAddress());

        user.addMenu(menu);

        for (CourseDTO courseDTO : menuDTO.getCourses()) {
            menu.addToMenu(new Course(courseDTO));
            totalPrice += 150 * menuDTO.getServings();
        }


        menu.setTotalPrice(totalPrice);

        try {

            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();

            return new MenuDTO(menu);

        } finally {
            em.close();
        }
    }

    public MenusDTO getAll() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery query = em.createQuery("SELECT m from Menu m", Menu.class);
            List<Menu> results = query.getResultList();

            return new MenusDTO(results);

        } finally {
            em.close();
        }
    }

    public MenuDTO getById(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Menu menu = em.find(Menu.class, id);

            return new MenuDTO(menu);
        } finally {
            em.close();
        }
    }

    public MenuDTO deleteMenu(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Menu menu = em.find(Menu.class, id);
        try {

            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM COURSE  WHERE MENU_id = ?").setParameter(1, id).executeUpdate();
            em.remove(menu);
            em.getTransaction().commit();

            return new MenuDTO(menu);
        } finally {
            em.close();
        }
    }


    //Used in tests to check size of the DB
    public Long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(a.id) FROM Course a", Long.class);
            long rows = query.getSingleResult();
            return rows;
        } finally {
            em.close();
        }
    }
}
