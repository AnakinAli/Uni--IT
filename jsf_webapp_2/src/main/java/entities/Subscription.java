package entities;

public class Subscription {

    private String selectedCategory;
    private String selectedSubCategory;
    private String email;

    public Subscription() {
    }

    public Subscription(String selectedCategory, String selectedSubCategory, String email) {
        this.selectedCategory = selectedCategory;
        this.selectedSubCategory = selectedSubCategory;
        this.email = email;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedSubCategory() {
        return selectedSubCategory;
    }

    public void setSelectedSubCategory(String selectedSubCategory) {
        this.selectedSubCategory = selectedSubCategory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}