package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CateringOrder.CateringOrderDTO;
import facades.OrderFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/order")
public class OrderResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OrderFacade facade = OrderFacade.getOrderFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String showMessage() {
        return "{\"msg\":\"This is the order API section\"}";
    }

    @Path("menu/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createOrder(@PathParam("id") int id, String order) {
        CateringOrderDTO o = gson.fromJson(order, CateringOrderDTO.class);
        CateringOrderDTO oNew = facade.createOrder(id, o);
        return gson.toJson(oNew);
    }


    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrder() {
        return gson.toJson(facade.getAll());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrderById(@PathParam("id")int id){
        return gson.toJson(facade.getOrderById(id));
    }

    
}