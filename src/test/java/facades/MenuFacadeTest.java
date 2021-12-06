package facades;

import dtos.Menu.MenuDTO;
import dtos.Menu.MenusDTO;
import entities.Course;
import entities.Menu;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;


class MenuFacadeTest {

    private static EntityManagerFactory emf;
    private static MenuFacade facade;
    private static User u1;
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
        u1 = new User("testUser", "testPassword");

        m1 = new Menu("24/12/2021",3,"lyngby vej");
        m2 = new Menu("/31/12/2021",4,"haslev hovedgade");

        c1 = new Course("Ratatoullie", "france.fr", 533);
        c2 = new Course("Goulasch", "hungary.hu", 534);
        c3 = new Course("Fish n Chips", "england.uk", 535);

        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m2.addToMenu(c3);

        u1.addMenu(m1);
        u1.addMenu(m2);

        em.getTransaction().begin();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
        em.persist(u1);
        em.persist(m1);
        em.persist(m2);
        em.getTransaction().commit();
    }


    @AfterEach
    public void tearDown() {
    }

    @Test
    public void createMenuTest() {
        Menu m3 = new Menu("01/12/2021",2,"mumlebæk");

        facade.createMenu(u1.getUserName(), new MenuDTO(m3));

        m3.addToMenu(new Course("pizza", "g.dk", 1));
        m3.addToMenu(new Course("burger", "g.dk", 2));

        int actual = 3;
        int expected = facade.getAll().getMenus().size();

        assertEquals(actual, expected);
    }

    @Test
    public void getByIdTest() {
        int expected = new MenuDTO(m1).getId();
        int actual = facade.getById(m1.getId()).getId();
        assertEquals(expected, actual);
    }


    @Test
    public void getAllTest() {
        int expected = 2;
        int actual = facade.getAll().getMenus().size();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteMenuTest() {
        facade.deleteMenu(m2.getId());

        assertEquals(1, facade.getAll().getMenus().size());
    }

    @Test
    public void getMenuByUsernameTest(){
        int expected = 2;
        int actual = facade.getMenuByUsername(u1.getUserName()).getMenus().size();
        assertEquals(expected,actual);
    }
}