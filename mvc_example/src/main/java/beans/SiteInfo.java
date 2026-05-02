package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("siteInfo")
@RequestScoped
public class SiteInfo {

    private String shortInfo;
    private String email;

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}