package com.example.inWeather;

import java.util.List;
import javax.sql.DataSource;

public interface inWeatherDAO {
    public void setDataSource(DataSource ds);

    public void create(Integer W_ID, Integer R_ID);

    public List<inWeather> getinWeatherR(Integer R_ID);

    public inWeather getinWeather(Integer W_ID);

    public List<inWeather> listinWeathers();

    public void delete(Integer W_ID);

    public void update(Integer W_ID, Integer R_ID);
}
