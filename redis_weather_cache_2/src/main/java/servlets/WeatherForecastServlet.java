package servlets;

import dao.Database;
import entities.DayForecast;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/WeatherForecastServlet")
public class WeatherForecastServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        ArrayList<DayForecast> forecast;
        String dataSource;

        Jedis redis = new Jedis();
        Jsonb jsonb = JsonbBuilder.create();

        if (redis.exists("weatherForecast")) {
            String weatherForecastAsJson = redis.get("weatherForecast");

            forecast = jsonb.fromJson(
                    weatherForecastAsJson,
                    new ArrayList<DayForecast>() {}.getClass().getGenericSuperclass()
            );

            dataSource = "Redis cache";
        } else {
            forecast = Database.getWeatherForecast();

            String weatherForecastAsJson = jsonb.toJson(forecast);
            redis.set("weatherForecast", weatherForecastAsJson);

            dataSource = "Database";
        }

        redis.close();

        String source =
                "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset='UTF-8'><title>Weather Forecast</title></head>" +
                "<body>" +
                "<h1>Прогноза за времето</h1>" +
                "<p>Данните са заредени от: <b>" + dataSource + "</b></p>" +
                "<table border='1' cellpadding='10'>" +
                "<tr><th>Дата</th><th>Температура</th></tr>";

        for (DayForecast day : forecast) {
            source +=
                    "<tr>" +
                    "<td>" + day.getDate() + "</td>" +
                    "<td>" + day.getTemperature() + " °C</td>" +
                    "</tr>";
        }

        source +=
                "</table>" +
                "<br>" +
                "<a href='index.html'>Начало</a>" +
                "</body>" +
                "</html>";

        out.print(source);
    }
}