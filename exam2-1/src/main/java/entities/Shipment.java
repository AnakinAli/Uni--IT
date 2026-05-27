package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shipmentNumber;
    private String recipientName;
    private String deliveryAddress;
    private double cashOnDelivery;
    private boolean delivered;

    public Shipment() {
    }

    public Shipment(String shipmentNumber, String recipientName, String deliveryAddress,
                    double cashOnDelivery, boolean delivered) {
        this.shipmentNumber = shipmentNumber;
        this.recipientName = recipientName;
        this.deliveryAddress = deliveryAddress;
        this.cashOnDelivery = cashOnDelivery;
        this.delivered = delivered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(double cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
