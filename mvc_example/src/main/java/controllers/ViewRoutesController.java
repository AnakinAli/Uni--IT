package controllers;

import beans.Database;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Controller
@Path("/viewRoutes")
@Produces("text/html")
public class ViewRoutesController {

    @Inject
    Models models;

    @Inject
    Database db;

    @GET
    public Response viewRoutes() {

        models.put("routes", db.getRoutes());

        return Response
                .status(Response.Status.OK)
                .entity("viewRoutes.xhtml")
                .build();
    }
}