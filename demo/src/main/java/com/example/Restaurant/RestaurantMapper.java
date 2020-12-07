package com.example.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RestaurantMapper implements RowMapper<Restaurant> {
   public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
      Restaurant restaurant = new Restaurant();
      restaurant.setID(rs.getInt("ID"));
      restaurant.setName(rs.getString("name"));
      restaurant.setCity(rs.getString("city"));
      restaurant.setDistrict(rs.getString("District"));
      restaurant.setLatitude(rs.getFloat("latitude"));
      restaurant.setLongitude(rs.getFloat("longitude"));
      restaurant.setAddress(rs.getString("Address"));
      restaurant.setRating(rs.getFloat("rating"));
      return restaurant;
   }
}
