package webServices;

import dao.Database;
import entities.DayForecast;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

@Path("forecast")
public class WeatherForecastService {

    private ArrayList<DayForecast> getForecastWithCache() {
        ArrayList<DayForecast> forecast;

        Jedis redis = new Jedis();
        Jsonb jsonb = JsonbBuilder.create();

        if (redis.exists("weatherForecast")) {
            String weatherForecastAsJson = redis.get("weatherForecast");

            forecast = jsonb.fromJson(
                    weatherForecastAsJson,
                    new ArrayList<DayForecast>() {}.getClass().getGenericSuperclass()
            );
        } else {
            forecast = Database.getWeatherForecast();

            String weatherForecastAsJson = jsonb.toJson(forecast);
            redis.set("weatherForecast", weatherForecastAsJson);
        }

        redis.close();

        return forecast;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<DayForecast> getAllForecast() {
        return getForecastWithCache();
    }

    @GET
    @Path("/today")
    @Produces(MediaType.APPLICATION_JSON)
    public DayForecast getTodayForecast() {
        ArrayList<DayForecast> forecast = getForecastWithCache();

        if (forecast.size() > 0) {
            return forecast.get(0);
        }

        return null;
    }

    @GET
    @Path("/days/{count}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<DayForecast> getForecastForDays(@PathParam("count") int count) {
        ArrayList<DayForecast> forecast = getForecastWithCache();
        ArrayList<DayForecast> result = new ArrayList<>();

        if (count > forecast.size()) {
            count = forecast.size();
        }

        for (int i = 0; i < count; i++) {
            result.add(forecast.get(i));
        }

        return result;
    }
}