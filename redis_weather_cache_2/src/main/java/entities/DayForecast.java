package entities;

import java.time.LocalDate;

public class DayForecast {

    private LocalDate date;
    private int temperature;

    public DayForecast() {
    }

    public DayForecast(LocalDate date, int temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}