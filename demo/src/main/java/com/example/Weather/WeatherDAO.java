package com.example.Weather;

import java.util.List;
import javax.sql.DataSource;

public interface WeatherDAO {
    public void setDataSource(DataSource ds);

    public void create(int ID, String date, String startTime, String endTime, String city, String District, String Wx, int pop, double temperature, double AvgAt);

    public Weather getWeather(Integer ID);

    public List<Weather> listWeathers();

    public void delete(Integer ID);

    public void update(int ID, String date, String Wx, int pop, double temperature, double AvgAt);
}