package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.Restaurant.*;



@SuppressWarnings("unused")
public class RestaurantJDBCTemplate implements RestaurantDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(int ID, String name, String city, String District, float latitude, float longitude, String Address, float rating){
        String SQL = "insert into Restaurant (ID, name, city, District, latitude, longitude, Address, rating) values (?, ?, ?, ?, ?, ?, ?, ?)";     
        jdbcTemplateObject.update( SQL, ID, name, city, District, latitude, longitude, Address, rating);
        System.out.println("Created Record ID = " + ID + " name = " + name + " city = " + city
                            + " District = " + District + " latitude = " + latitude + " longitude = " + longitude + " Address = " + Address + " rating = " + rating);
        return;
    }
    @SuppressWarnings("deprecation")
    public Restaurant getRestaurant(Integer ID) {
        String SQL = "select * from Restaurant where ID = ?";
        Restaurant restaurant = jdbcTemplateObject.queryForObject(SQL, new Object[]{ID}, new RestaurantMapper());
        return restaurant;
    }
    public List<Restaurant> listRestaurants() {
      String SQL = "select * from Restaurant";
      List <Restaurant> restaurants = jdbcTemplateObject.query(SQL, 
                                new RestaurantMapper());
      return restaurants;
    }
    public void delete(Integer ID){
        String SQL = "delete from Restaurant where ID = ?";
        jdbcTemplateObject.update(SQL, ID);
        System.out.println("Deleted Record with ID = " + ID );
        return;
    }
    public void update(Integer ID, float Rating){
        String SQL = "update Restaurant set rating = ?  where ID = ?";
        jdbcTemplateObject.update(SQL, Rating, ID);
        System.out.println("Updated Record with ID = " + ID );
        return;
    }
}
