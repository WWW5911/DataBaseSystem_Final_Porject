package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.inAir.*;

public class inAirJDBCTemplate implements inAirDAO {
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public void create(Integer R_ID, Integer A_SiteID) {
        String SQL = "insert into inAir (R_ID, A_SiteID) values (?, ?)";     
        jdbcTemplateObject.update( SQL, R_ID, A_SiteID);
        System.out.println("Created Record R_ID = " + R_ID + " A_SiteID = " + A_SiteID);
        return;
    }
    public List<inAir> getinAirA(Integer A_SiteID) {
        String SQL = "select * from inAir where A_SiteID = " + A_SiteID;
        List <inAir> inair = jdbcTemplateObject.query(SQL, new inAirMapper());
        return inair;
    }
    @SuppressWarnings("deprecation")
    public inAir getinAir(Integer R_ID){
        String SQL = "select * from inAir where R_ID = ?";
        inAir inair = jdbcTemplateObject.queryForObject(SQL, new Object[]{R_ID}, new inAirMapper());
        return inair;
    }
    public List<inAir> listinAirs() {
      String SQL = "select * from inAir";
      List <inAir> services = jdbcTemplateObject.query(SQL, 
                                new inAirMapper());
      return services;
    }
    public void delete(Integer R_ID){
        String SQL = "delete from inAir where R_ID = ?";
        jdbcTemplateObject.update(SQL, R_ID);
        System.out.println("Deleted Record with R_ID = " + R_ID );
        return;
    }
    public void update(Integer R_ID, Integer A_SiteID){
        String SQL = "update inAir set A_SiteID = ? where R_ID = ?";
        jdbcTemplateObject.update(SQL, A_SiteID, R_ID);
        System.out.println("Updated Record with R_ID = " + R_ID );
        return;
    }
}
