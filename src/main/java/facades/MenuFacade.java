package facades;

import dtos.Course.CourseDTO;
import dtos.Course.CoursesDTO;
import dtos.Menu.MenuDTO;
import entities.Course;
import entities.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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


    public MenuDTO createMenu(MenuDTO menuDTO) {
        EntityManager em = emf.createEntityManager();
        Menu menu = new Menu();
        for (CourseDTO courseDTO : menuDTO.getCourses()) {
            menu.addToMenu(new Course(courseDTO));
        }

        try {

            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();

            return new MenuDTO(menu);

        } finally {
            em.close();
        }
    }


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
