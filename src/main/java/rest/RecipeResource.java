package rest;

import com.google.gson.Gson;
import utils.HttpUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@Path("/recipes")
public class RecipeResource {

    Gson gson = new Gson();

    @Context
    private UriInfo context;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String showMessage() {
        return "{\"msg\":\"No ingredient was found\"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{ingredient}")
    public String getRecipe(@PathParam("ingredient") String ingredient) throws IOException {
        return HttpUtils.fetchData("https://api.spoonacular.com/recipes/complexSearch?apiKey=2d511fa06f8f41a4b083ee7f70f8f40d&includeIngredients=" + ingredient);
    }
}
