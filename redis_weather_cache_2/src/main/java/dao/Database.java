package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.DayForecast;
import redis.clients.jedis.Jedis;

public class Database {

    private static ArrayList<DayForecast> forecast = initForecast();

    private static ArrayList<DayForecast> initForecast() {
        ArrayList<DayForecast> result = new ArrayList<>();

        result.add(new DayForecast(LocalDate.now(), 20));
        result.add(new DayForecast(LocalDate.now().plusDays(1), 22));
        result.add(new DayForecast(LocalDate.now().plusDays(2), 24));

        return result;
    }

    public static ArrayList<DayForecast> getWeatherForecast() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return forecast;
    }

    public static void updateWeatherForecast(ArrayList<DayForecast> newForecast) {
        forecast = newForecast;

        Jedis redis = new Jedis();
        redis.del("weatherForecast");
        redis.close();
    }
}