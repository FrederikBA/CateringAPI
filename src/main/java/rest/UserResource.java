package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.User.UserDTO;
import entities.User;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import facades.UserFacade;
import utils.EMF_Creator;


@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade facade = UserFacade.getUserFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("customerinfo")
    @RolesAllowed("customer")
    public String getFromCustomer() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admininfo")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("currentcustomer")
    @RolesAllowed("customer")
    public String getUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return gson.toJson(thisuser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("role/{username}")
    public String getRoleByUsername(@PathParam("username") String username) {
        return gson.toJson(facade.getRolesByUsername(username));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("register")
    public String registerUser(String user){
        UserDTO u = gson.fromJson(user, UserDTO.class);
        UserDTO uNew = facade.registerUser(u.getUserName(), u.getUserPass());
        return gson.toJson(uNew);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public String getByUser(@PathParam("username") String name){
        return gson.toJson(facade.getByUsername(name));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    @RolesAllowed("admin")
    public String getAllUsers (){
        return gson.toJson(facade.getAllUsers());
    }

    @Path("/{username}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("username") String username){
        UserDTO userdeleted = facade.deleteUser(username);
        return gson.toJson(userdeleted);
    }
}