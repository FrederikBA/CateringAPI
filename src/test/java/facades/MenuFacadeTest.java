package facades;

import dtos.Course.CourseDTO;
import dtos.Menu.MenuDTO;
import entities.Course;
import entities.Menu;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;


class MenuFacadeTest {

    private static EntityManagerFactory emf;
    private static MenuFacade facade;
    private static Menu m1, m2;
    private static Course c1, c2, c3;

    public MenuFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MenuFacade.getMenuFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }


    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        

        em.getTransaction().begin();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.getTransaction().commit();
    }


    @AfterEach
    public void tearDown() {
    }


    @Test
    public void createMenuTest() {
        Menu m3 = new Menu();

        m3.addToMenu(new Course("pizza", "g.dk", 1));
        m3.addToMenu(new Course("burger", "g.dk", 2));

        int actual = 2;
        int expected = facade.createMenu(new MenuDTO(m3)).getCourses().size();

        assertEquals(actual, expected);

    }


}