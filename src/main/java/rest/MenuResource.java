package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Menu.MenuDTO;
import facades.MenuFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed("admin")
    public String getAll() {
        return gson.toJson(facade.getAll());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("menus")
    public String getMenus() {
        return gson.toJson(facade.getAll());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getById(@PathParam("id") int id) {
        return gson.toJson(facade.getById(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public String createMenu(@PathParam("username") String username, String menu) {
        MenuDTO m = gson.fromJson(menu, MenuDTO.class);
        MenuDTO mNew = facade.createMenu(username, m);
        return gson.toJson(mNew);
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteMenu(@PathParam("id") int id) {
        MenuDTO menuDeleted = facade.deleteMenu(id);
        return gson.toJson(menuDeleted);
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getMenuByUsername(@PathParam("username")String username){
        return gson.toJson(facade.getMenuByUsername(username));
    }


    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editDeliveryDate(@PathParam("id")int id, String deliveryDate){
        MenuDTO menuDTO = gson.fromJson(deliveryDate,MenuDTO.class);
        menuDTO.setId(id);
        MenuDTO menuEdited = facade.editDeliveryDate(menuDTO);
        return gson.toJson(menuEdited);
    }

    @Path("courses/{ordernumber}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoursesByOrderNumber(@PathParam("ordernumber") int orderNumber) {
        return gson.toJson(facade.getCoursesByOrderNumber(orderNumber));
    }

}