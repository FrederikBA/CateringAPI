package facades;

import dtos.Menu.MenuDTO;
import entities.Course;
import entities.Menu;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;



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
        m1 = new Menu();
        m2 = new Menu();

        c1 = new Course("Ratatoullie", "france.fr", 533);
        c2 = new Course("Goulasch", "hungary.hu", 534);
        c3 = new Course("Fish n Chips", "england.uk", 535);

        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m2.addToMenu(c3);

        em.getTransaction().begin();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.persist(m1);
        em.persist(m2);
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

    @Test
    public void getByIdTest() {
        MenuDTO expected = new MenuDTO(m1);
        MenuDTO actual = facade.getById(m1.getId());
        assertEquals(expected, actual);
    }
    

    @Test
    public void getAllTest() {
        int expected = 2;
        int actual = facade.getAll().getMenus().size();
        assertEquals(expected, actual);
    }
}