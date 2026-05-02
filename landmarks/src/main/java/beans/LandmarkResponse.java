package beans;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LandmarkResponse {
    private String id;
    private String name;
    private LandmarkType type;

    public LandmarkResponse() {
    }

    public LandmarkResponse(String id, String name, LandmarkType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public LandmarkType getType() {
        return type;
    }

    public void setType(LandmarkType type) {
        this.type = type;
    }
}