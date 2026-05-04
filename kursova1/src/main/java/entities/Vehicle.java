package entities;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicle {

    private int id;
    private String brandAndModel;
    private int passengers;
    private int productionYear;
    private double fuelConsumption;
    private String color;
    private String gearboxType;

    public Vehicle() {
    }

    public Vehicle(int id, String brandAndModel, int passengers, int productionYear,
                   double fuelConsumption, String color, String gearboxType) {
        this.id = id;
        this.brandAndModel = brandAndModel;
        this.passengers = passengers;
        this.productionYear = productionYear;
        this.fuelConsumption = fuelConsumption;
        this.color = color;
        this.gearboxType = gearboxType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBrandAndModel() {
        return brandAndModel;
    }

    public void setBrandAndModel(String brandAndModel) {
        this.brandAndModel = brandAndModel;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }
}