package facades;

import dtos.CateringOrder.CateringOrderDTO;
import dtos.Menu.MenuDTO;
import entities.CateringOrder;
import entities.Course;
import entities.Menu;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

import static org.junit.jupiter.api.Assertions.*;

class OrderFacadeTest {

    private static EntityManagerFactory emf;
    private static OrderFacade facade;
    private static Menu m1, m2;
    private static Course c1, c2, c3;
    private static CateringOrder o1, o2;

    public OrderFacadeTest(){
    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = OrderFacade.getOrderFacade(emf);
    }

    @AfterAll
    public static void tearDownClass(){
    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        m1 = new Menu();
        m2 = new Menu();

        c1 = new Course("Ratatoullie", "france.fr", 533);
        c2 = new Course("Goulasch", "hungary.hu", 534);
        c3 = new Course("Fish n Chips", "england.uk", 535);

        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m2.addToMenu(c3);

        o1 = new CateringOrder("2021-01-04");
        o2 = new CateringOrder("2021-01-04");

        o1.setMenu(m1);
        o2.setMenu(m2);


        em.getTransaction().begin();
        em.createQuery("delete from CateringOrder").executeUpdate();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.persist(m1);
        em.persist(m2);
        em.persist(o1);
        em.persist(o2);
        em.getTransaction().commit();

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getAllOrderTest(){
        int expected = 2;
        int actual = facade.getAll().getOrders().size();
        assertEquals(expected, actual);
    }

    @Test
    public void getOrderById(){
        int expected = o1.getId();
        int actual = facade.getOrderById(o1.getId()).getId();
        assertEquals(expected,actual);
    }

    @Test
    public void createOrderTest() {
        CateringOrderDTO oDTO = facade.createOrder(m1.getId(),"Today");

        assertThat(facade.getAll().getOrders(), hasItem(oDTO));

        int expected = 3;
        int actual = facade.getAll().getOrders().size();

        assertEquals(expected, actual);
    }
}