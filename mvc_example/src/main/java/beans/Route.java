package beans;

import java.time.LocalDateTime;

public class Route {

    private int id;
    private String from;
    private String to;
    private LocalDateTime departureTime;

    public Route() {
    }

    public Route(int id, String from, String to, LocalDateTime departureTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}