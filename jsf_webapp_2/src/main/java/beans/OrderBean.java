package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("orderBean")
@SessionScoped
public class OrderBean implements Serializable {

    private String selectedProduct;
    private int price;

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public int getPrice() {
        return price;
    }

    public String buyMario() {
        selectedProduct = "Марио ключодържател";
        price = 1;
        return "orderComplete?faces-redirect=true";
    }

    public String buyLuigi() {
        selectedProduct = "Луиджи ключодържател";
        price = 1;
        return "orderComplete?faces-redirect=true";
    }

    public String buyMushroom() {
        selectedProduct = "Гъба ключодържател";
        price = 1;
        return "orderComplete?faces-redirect=true";
    }
}