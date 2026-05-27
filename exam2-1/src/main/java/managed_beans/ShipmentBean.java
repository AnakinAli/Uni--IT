package managed_beans;

import dao.Database;
import entities.Shipment;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named("shipmentBean")
@RequestScoped
public class ShipmentBean {

    @EJB
    private Database database;

    private Shipment shipment = new Shipment();

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public List<Shipment> getDeliveredShipments() {
        return database.getDeliveredShipments();
    }

    public List<Shipment> getUndeliveredShipments() {
        return database.getUndeliveredShipments();
    }

    public String addShipment() {
        database.addShipment(shipment);
        shipment = new Shipment();
        return "shipments?faces-redirect=true";
    }

    public String markAsDelivered(int id) {
        database.markAsDelivered(id);
        return "shipments?faces-redirect=true";
    }
}
