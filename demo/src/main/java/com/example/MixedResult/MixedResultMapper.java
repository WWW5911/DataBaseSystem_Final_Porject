package com.example.MixedResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MixedResultMapper implements RowMapper<MixedResult> {
   public MixedResult mapRow(ResultSet rs, int rowNum) throws SQLException {
      MixedResult restaurant = new MixedResult();
      restaurant.ID = rs.getInt("ID");
      restaurant.latitude = rs.getFloat("latitude");
      restaurant.longitude = rs.getFloat("longitude");
      restaurant.name = rs.getString("name");
      restaurant.Address = rs.getString("Address");
      restaurant.rating = rs.getFloat("rating");
      restaurant.Air = rs.getInt("AQI");
      restaurant.Status = rs.getString("Status");
      restaurant.Wx = rs.getString("Wx");
      restaurant.pop = rs.getInt("pop");
      restaurant.temperature = rs.getFloat("temperature");
      restaurant.AvgAt = rs.getFloat("AvgAt");
      restaurant.weight = 0;
      restaurant.openTime = rs.getInt("openTime");
      restaurant.closeTime = rs.getInt("closeTime");
      return restaurant;
   }
}
