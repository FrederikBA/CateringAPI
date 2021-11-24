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

    }


    @AfterEach
    public void tearDown() {
    }


  //@Test
  //public void createMenuTest() {
  //    Menu m1 = new Menu();
  //    m1.addToMenu(new Course("lasagne", "lasagne.dk"));
  //    m1.addToMenu(new Course("burger", "MCD.dk"));
  //    m1.addToMenu(new Course("pizza", "pizzamaaaan.dk"));

  //    facade.createMenu(new MenuDTO(m1));

  //    int expected = 0;
  //    int actual = m1.getCourses().size();

  //    assertEquals(expected,actual);

  //}


}