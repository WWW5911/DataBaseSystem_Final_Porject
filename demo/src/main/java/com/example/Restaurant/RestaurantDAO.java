package com.example.Restaurant;

import java.util.List;
import javax.sql.DataSource;

public interface RestaurantDAO {
    public void setDataSource(DataSource ds);

    public void create(int ID, String name, String city, String District, float latitude, float longitude, String Address, float rating);

    public Restaurant getRestaurant(Integer ID);

    public List<Restaurant> listRestaurants();

    public void delete(Integer ID);

    public void update(Integer ID, float Rating);
}
