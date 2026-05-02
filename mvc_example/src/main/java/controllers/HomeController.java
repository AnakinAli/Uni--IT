package controllers;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.FormParam;
import beans.Database;
import beans.Route;
import java.time.LocalDateTime;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import beans.SiteInfo;
@Controller
@Path("/home")
@RequestScoped
public class HomeController {
    @Inject
    SiteInfo siteInfo;
    @Inject
    Database db;
    @GET
    @Produces("text/html")
    public String index() {

        siteInfo.setShortInfo(
                "Добре дошли! Тук ще намерите разписанието."
        );

        return "index.xhtml";
    }
    @POST
    public String addRoute(
            @FormParam("from") String from,
            @FormParam("to") String to,
            @FormParam("departureTime") String depTime
    ) {

        LocalDateTime departureTime = LocalDateTime.parse(depTime);

        Route route = new Route(3, from, to, departureTime);

        db.addRoute(route);

        return "routeAdded.xhtml";
    }
}