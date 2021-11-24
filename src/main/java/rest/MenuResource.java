package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.MenuFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/menu")
public class MenuResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MenuFacade facade = MenuFacade.getMenuFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String showMessage() {
        return "{\"msg\":\"This is the menu API section\"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAll() {
        return gson.toJson(facade.getAll());
    }
}