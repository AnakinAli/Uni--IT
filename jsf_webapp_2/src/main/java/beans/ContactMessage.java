package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("contactMessage")
@RequestScoped
public class ContactMessage {

    private String name;
    private String email;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String send() {
        return "messageSent";
    }
}