package entities;

public class Order {

    private int id;
    private String productName;
    private String clientName;
    private String address;
    private double price;

    public Order() {
    }

    public Order(String productName, String clientName, String address, double price) {
        this.productName = productName;
        this.clientName = clientName;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Стока: " + productName +
                ", Клиент: " + clientName +
                ", Адрес: " + address +
                ", Цена: " + String.format("%.2f", price);
    }
}