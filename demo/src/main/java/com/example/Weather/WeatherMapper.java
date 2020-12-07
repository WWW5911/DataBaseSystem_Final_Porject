package com.example.Weather;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class WeatherMapper implements RowMapper<Weather> {
   public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
      Weather weather = new Weather();
      weather.setID(rs.getInt("ID"));
      weather.setDate(rs.getString("date"));
      weather.setStartTime(rs.getString("startTime"));
      weather.setEndTime(rs.getString("endTime"));
      weather.setDistrict(rs.getString("District"));
      weather.setCity(rs.getString("city"));
      weather.setWx(rs.getString("Wx"));
      weather.setPop(rs.getInt("pop"));
      weather.setTemperature(rs.getFloat("temperature"));
      weather.setAvgAt(rs.getFloat("AvgAt"));
      return weather;
   }
}
