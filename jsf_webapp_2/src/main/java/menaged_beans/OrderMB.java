package menaged_beans;

import java.util.ArrayList;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import dao.Database;
import entities.Order;
import entities.Product;

@Named("orderMB")
@RequestScoped
public class OrderMB {

    private Order order = new Order();

    private ArrayList<Product> products = Database.getProducts();

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String purchase(int id) {
        Product p = products.get(id);

        String orderedProduct = p.getProductName();
        double orderPrice = p.getPrice();

        order.setProductName(orderedProduct);
        order.setPrice(orderPrice);

        return "placeOrder";
    }

    public String placeOrder() {
        Database.addOrder(order);

        return "orderCompleted";
    }
}