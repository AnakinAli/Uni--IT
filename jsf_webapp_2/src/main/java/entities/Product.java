package entities;

public class Product {

    private int id;
    private String productName;
    private double price;
    private String image;

    public Product() {
    }

    public Product(int id, String productName, double price, String image) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Стока: " + productName + ", Цена: " + String.format("%.2f", price);
    }
}