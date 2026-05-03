package webServices;

import dao.Database;
import entities.DayForecast;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;

@Path("weatherForecast")
public class WeatherForecastService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<DayForecast> getForecast() {
        return Database.getWeatherForecast();
    }

    @GET
    @Path("today")
    @Produces(MediaType.APPLICATION_JSON)
    public DayForecast getTodayForecast() {
        ArrayList<DayForecast> forecast = Database.getWeatherForecast();
        LocalDate today = LocalDate.now();

        for (DayForecast dayForecast : forecast) {
            if (dayForecast.getDate().equals(today)) {
                return dayForecast;
            }
        }

        return null;
    }

    @GET
    @Path("tomorrow")
    @Produces(MediaType.APPLICATION_JSON)
    public DayForecast getTomorrowForecast() {
        ArrayList<DayForecast> forecast = Database.getWeatherForecast();
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        for (DayForecast dayForecast : forecast) {
            if (dayForecast.getDate().equals(tomorrow)) {
                return dayForecast;
            }
        }

        return null;
    }
}