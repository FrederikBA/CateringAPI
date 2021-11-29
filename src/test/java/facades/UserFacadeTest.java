package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {
    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private static User u1, u2;
    private static Role r1;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        u1 = new User("TestCustomerOne", "password1");
        u2 = new User("TestCustomerTwo", "password2");
        r1 = new Role("customer");
        u1.addRole(r1);
        u2.addRole(r1);


        try {
            em.getTransaction().begin();
            em.createQuery("delete from Course").executeUpdate();
            em.createQuery("delete from Menu").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.persist(r1);
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {


    }

    @Test
    public void testCount() {
        long expected = 2;
        long actual = facade.getCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUser() {
        UserDTO u3DTO = facade.registerUser("TestCustomerThree", "password3");
        assertEquals(3, facade.getCount());
        assertEquals("TestCustomerThree", u3DTO.getUserName());
    }

    @Test
    public void getByUsernameTest() {
        String expected = "TestCustomerOne";
        String actual = facade.getByUsername(u1.getUserName()).getUserName();
        assertEquals(expected, actual);
    }
}