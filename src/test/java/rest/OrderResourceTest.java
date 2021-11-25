package rest;

import dtos.CateringOrder.CateringOrderDTO;
import dtos.Course.CourseDTO;
import dtos.Menu.MenuDTO;
import entities.CateringOrder;
import entities.Course;
import entities.Menu;
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

/*
class OrderResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Menu m1, m2;
    private static Course c1, c2, c3;
    private static CateringOrder o1, o2;


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

    @Test
    public void testServerIsUp() {
        given().when().get("/order").then().statusCode(200);
    }

    @Test
    public void testGetAll() {
        List<CateringOrderDTO> orders;
        orders = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .get("order/all").then()
                .extract()
                .body()
                .jsonPath()
                .getList("orders", CateringOrderDTO.class);

        assertEquals(2, orders.size());
    }

    @Test
    public void testGetById(){
        given()
                .contentType("application/json")
                .pathParam("id", o1.getId())
                .when()
                .get("order/{id}")
                .then()
                .statusCode(200);
    }


}
*/
