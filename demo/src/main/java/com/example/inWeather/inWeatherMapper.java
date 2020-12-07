package com.example.inWeather;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class inWeatherMapper implements RowMapper<inWeather> {
   public inWeather mapRow(ResultSet rs, int rowNum) throws SQLException {
      inWeather inweather = new inWeather();
      inweather.setR_ID(rs.getInt("R_ID"));
      inweather.setW_ID(rs.getInt("W_ID"));
      return inweather;
   }
}
