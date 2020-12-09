package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.Weather.*;



@SuppressWarnings("unused")
public class WeatherJDBCTemplate implements WeatherDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(int ID, String date, String startTime, String endTime, String city, String District, String Wx, int pop, double temperature, double AvgAt) {
        String SQL = "insert into Weather (ID, date, startTime, endTime, city, District, Wx, pop, temperature, AvgAt) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";     
        jdbcTemplateObject.update( SQL, ID, date, startTime, endTime, city, District, Wx, pop, temperature, AvgAt);
        System.out.println("Created Record ID = " + ID + " date = " + date + " startTime = " + startTime + " endTime = " + endTime 
                            + " city = " + city + " District = " + District + " Wx = " + Wx + " pop = " + pop + " temperature = " + temperature + " AvgAt = " + AvgAt);
        return;
    }
    @SuppressWarnings("deprecation")
    public Weather getWeather(Integer ID) {
        String SQL = "select * from Weather where ID = ?";
        Weather weather = jdbcTemplateObject.queryForObject(SQL, new Object[]{ID}, new WeatherMapper());
        return weather;
    }
    public List<Weather> listWeathers() {
      String SQL = "select * from Weather";
      List <Weather> weathers = jdbcTemplateObject.query(SQL, 
                                new WeatherMapper());
      return weathers;
    }
    public void delete(Integer ID){
        String SQL = "delete from Weather where ID = ?";
        jdbcTemplateObject.update(SQL, ID);
        System.out.println("Deleted Record with ID = " + ID );
        return;
    }
    public void update(int ID, String date, String Wx, int pop, double temperature, double AvgAt){
        String SQL = "update Weather set date = ?, set Wx = ?, set pop = ?, set temperature = ?, set AvgAt = ?,  where ID = ?";
        jdbcTemplateObject.update(SQL, date, Wx, pop, temperature, AvgAt, ID);
        System.out.println("Updated Record with SiteID = " + ID );
        return;
    }
}
