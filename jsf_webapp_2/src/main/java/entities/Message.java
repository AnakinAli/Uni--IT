package entities;

public class Message {

    private String senderName;
    private String email;
    private String message;

    public Message() {
    }

    public Message(String senderName, String email, String message) {
        this.senderName = senderName;
        this.email = email;
        this.message = message;
    }

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

    @Override
    public String toString() {
        return senderName + " написа: " + message;
    }
}