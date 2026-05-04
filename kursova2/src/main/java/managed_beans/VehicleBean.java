package managed_beans;

import dao.Database;
import entities.Vehicle;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named("vehicleBean")
@RequestScoped
public class VehicleBean {

    @EJB
    private Database database;

    private Vehicle vehicle = new Vehicle();

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Vehicle> getVehicles() {
        return database.getVehicles();
    }

    public String addVehicle() {
        database.addVehicle(vehicle);

        vehicle = new Vehicle();

        return "index?faces-redirect=true";
    }
}