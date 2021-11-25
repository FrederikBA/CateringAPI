package facades;

import entities.CateringOrder;
import entities.Course;
import entities.Menu;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class OrderFacadeTest {

    private static EntityManagerFactory emf;
    private static OrderFacade facade;
    private static Menu m1, m2;
    private static Course c1, c2, c3;
    private static CateringOrder co1, co2, co3;

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

        co1.setMenu(m1);
        co2.setMenu(m1);
        co3.setMenu(m2);

        em.getTransaction().begin();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.createQuery("delete from CateringOrder").executeUpdate();
        em.persist(co1);
        em.persist(co2);
        em.persist(co3);
        em.getTransaction().commit();

    }

    @AfterEach
    public void tearDown() {
    }

   /* @Test
    public void getAllOrderTest(){
        int expected = 0;
        int actual = facade.getAll().getOrders().size();
        assertEquals(expected, actual);
    }*/
}