package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Named
@SessionScoped
public class Database implements Serializable {

    private ArrayList<Route> routes = new ArrayList<>();

    public Database() {
        routes.add(new Route(1, "Ruse", "Sofia", LocalDateTime.of(2025, 4, 10, 10, 20)));
        routes.add(new Route(2, "Sofia", "Ruse", LocalDateTime.of(2025, 5, 10, 10, 20)));
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }
}