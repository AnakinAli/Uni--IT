package menaged_beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import dao.Database;
import entities.Message;

@Named
@RequestScoped
public class Contacts {

    private String senderName;
    private String email;
    private String message;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String send() {
        if (email.matches("^[a-zA-Z0-9]+[._]*+[a-zA-Z0-9]*+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$")) {

            Message msg = new Message(senderName, email, message);

            Database.addMessage(msg);

            return "sent";
        } else {
            return "contacts";
        }
    }
}