package beans;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Landmark {
    private String id;
    private String name;
    private String description;
    private LandmarkType type;
    private Double rating;

    public Landmark() {
    }

    public Landmark(String id, String name, String description, LandmarkType type, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LandmarkType getType() {
        return type;
    }

    public void setType(LandmarkType type) {
        this.type = type;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}