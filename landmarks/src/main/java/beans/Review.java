package beans;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Review {
    private String id;
    private String landmarkId;
    private String username;
    private Integer rating;

    public Review() {
    }

    public Review(String id, String landmarkId, String username, Integer rating) {
        this.id = id;
        this.landmarkId = landmarkId;
        this.username = username;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(String landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}