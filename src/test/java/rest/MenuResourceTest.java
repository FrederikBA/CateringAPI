package rest;

import dtos.Course.CourseDTO;
import dtos.Menu.MenuDTO;
import entities.Course;
import entities.Menu;
import entities.Role;
import entities.User;
import facades.MenuFacade;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;


class MenuResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static MenuFacade facade;
    private static User u1;
    private static Role r1;
    private static Menu m1, m2;
    private static Course c1, c2, c3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MenuFacade.getMenuFacade(emf);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        u1 = new User("testUser", "testPassword");
        r1 = new Role("customer");

        m1 = new Menu("24/12/2021",3,"bikini bottom");
        m2 = new Menu("31/12/2021",4,"bikini bottom");

        c1 = new Course("Ratatoullie", "france.fr", 533);
        c2 = new Course("Goulasch", "hungary.hu", 534);
        c3 = new Course("Fish n Chips", "england.uk", 535);

        m1.addToMenu(c1);
        m1.addToMenu(c2);
        m2.addToMenu(c3);

        u1.addMenu(m1);
        u1.addMenu(m2);

        u1.addRole(r1);

        em.getTransaction().begin();
        em.createQuery("delete from Course").executeUpdate();
        em.createQuery("delete from Menu").executeUpdate();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Role").executeUpdate();
        em.persist(r1);
        em.persist(u1);
        em.persist(m1);
        em.persist(m2);
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.getTransaction().commit();
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/menu").then().statusCode(200);
    }


    @Test
    public void testGetAll() {
        List<MenuDTO> menus;
        menus = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .get("/menu/all").then()
                .extract()
                .body()
                .jsonPath()
                .getList("menus", MenuDTO.class);

        assertEquals(2, menus.size());
    }

    @Test
    public void testGetById() {
        given()
                .contentType("application/json")
                .pathParam("id", m1.getId())
                .when()
                .get("menu/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMenuByIdTest() {
        given()
                .contentType("application/json")
                .pathParam("id", m2.getId())
                .delete("menu/{id}")
                .then()
                .assertThat()
                .statusCode(200);

        List<MenuDTO> menus;

        menus = given()
                .contentType("application/json")
                .when()
                .get("/menu/all")
                .then()
                .extract().body().jsonPath().getList("menus", MenuDTO.class);

        MenuDTO m2DTO = new MenuDTO(m2);
        assertThat(menus, not(hasItem(m2DTO)));
    }

    @Test
    public void testCreateMenu() {
        Menu m3 = new Menu("01/12/2021",2,"bikini bottom");

        m3.addToMenu(new Course("pizza", "g.dk", 1));
        m3.addToMenu(new Course("burger", "g.dk", 2));

        MenuDTO mDTO = facade.createMenu(u1.getUserName(), new MenuDTO(m3));

        given()
                .contentType("application/json")
                .body(mDTO)
                .when()
                .post("menu/testUser")
                .then()
                .statusCode(200)
                .body("deliveryDate ", equalTo("01/12/2021"));
    }

}



