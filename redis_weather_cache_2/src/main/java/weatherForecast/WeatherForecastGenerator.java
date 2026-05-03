package weatherForecast;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.Database;
import entities.DayForecast;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class WeatherForecastGenerator {

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void updateWeatherForecast() {
        ArrayList<DayForecast> forecast = new ArrayList<>();

        forecast.add(new DayForecast(
                LocalDate.now(),
                generateRandomTemperature()
        ));

        forecast.add(new DayForecast(
                LocalDate.now().plusDays(1),
                generateRandomTemperature()
        ));

        forecast.add(new DayForecast(
                LocalDate.now().plusDays(2),
                generateRandomTemperature()
        ));

        Database.updateWeatherForecast(forecast);

        System.out.println("Weather forecast updated.");
    }

    private int generateRandomTemperature() {
        return (int) (Math.random() * 36) - 10;
    }
}