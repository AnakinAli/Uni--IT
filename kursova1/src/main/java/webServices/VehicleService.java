package webServices;

import dao.Database;
import entities.Vehicle;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("vehicles")
public class VehicleService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Vehicle> getAllVehicles() {
        return Database.getVehicles();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleById(@PathParam("id") int id) {
        Vehicle vehicle = Database.getVehicleById(id);

        if (vehicle == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Няма превозно средство с ID: " + id)
                    .build();
        }

        return Response.ok(vehicle).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVehicle(Vehicle vehicle) {
        Database.addVehicle(vehicle);

        return Response.status(Response.Status.CREATED)
                .entity(vehicle)
                .build();
    }
}