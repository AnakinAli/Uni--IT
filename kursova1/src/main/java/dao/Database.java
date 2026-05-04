package dao;

import entities.Vehicle;

import java.util.ArrayList;

public class Database {

    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public static void addVehicle(Vehicle vehicle) {
        vehicle.setId(vehicles.size());
        vehicles.add(vehicle);
    }

    public static Vehicle getVehicleById(int id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }

        return null;
    }
}