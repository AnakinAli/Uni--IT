package webServices;

import dao.Database;
import entities.Shipment;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("shipments")
public class ShipmentService {

    @EJB
    private Database database;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShipments(@QueryParam("is_delivered") Integer isDelivered) {
        if (isDelivered == null) {
            List<Shipment> shipments = database.getShipments();
            return Response.ok(shipments).build();
        }
        if (isDelivered == 1) {
            List<Shipment> shipments = database.getDeliveredShipments();
            return Response.ok(shipments).build();
        }
        if (isDelivered == 0) {
            List<Shipment> shipments = database.getUndeliveredShipments();
            return Response.ok(shipments).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("is_delivered must be 1 or 0")
                .build();
    }
}
