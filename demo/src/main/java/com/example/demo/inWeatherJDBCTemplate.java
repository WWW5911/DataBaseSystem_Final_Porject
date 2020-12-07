package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.inWeather.*;

public class inWeatherJDBCTemplate implements inWeatherDAO {
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public void create(Integer W_ID, Integer R_ID) {
        String SQL = "insert into inWeather (R_ID, W_ID) values (?, ?)";     
        jdbcTemplateObject.update( SQL, R_ID, W_ID);
        System.out.println("Created Record W_ID = " + W_ID + " R_ID = " + R_ID);
        return;
    }
    public List<inWeather> getinWeatherR(Integer R_ID) {
        String SQL = "select * from inWeather where R_ID = " + R_ID;
        List <inWeather> service = jdbcTemplateObject.query(SQL, new inWeatherMapper());
        return service;
    }
    @SuppressWarnings("deprecation")
    public inWeather getinWeather(Integer W_ID){
        String SQL = "select * from inWeather where W_ID = ?";
        inWeather service = jdbcTemplateObject.queryForObject(SQL, new Object[]{W_ID}, new inWeatherMapper());
        return service;
    }
    public List<inWeather> listinWeathers() {
      String SQL = "select * from inWeather";
      List <inWeather> services = jdbcTemplateObject.query(SQL, 
                                new inWeatherMapper());
      return services;
    }
    public void delete(Integer W_ID){
        String SQL = "delete from inWeather where W_ID = ?";
        jdbcTemplateObject.update(SQL, W_ID);
        System.out.println("Deleted Record with W_ID = " + W_ID );
        return;
    }
    public void update(Integer W_ID, Integer R_ID){
        String SQL = "update inWeather set R_ID = ? where W_ID = ?";
        jdbcTemplateObject.update(SQL, R_ID, W_ID);
        System.out.println("Updated Record with W_ID = " + W_ID );
        return;
    }
}
