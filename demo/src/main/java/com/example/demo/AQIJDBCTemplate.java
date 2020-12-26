package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.AQI.*;



@SuppressWarnings("unused")
public class AQIJDBCTemplate implements AQIDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void create(int SiteID, String SiteName, String S_city, float S_latitude, float S_longitude, float AQI, String Status, float PM25, int time) {
        String SQL = "insert into AQI (SiteID, SiteName, S_city, S_latitude, S_longitude, AQI, Status, PM25, time) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";     
        jdbcTemplateObject.update( SQL, SiteID, SiteName, S_city, S_latitude, S_longitude, AQI, Status, PM25, time);
        System.out.println("Created Record SiteID = " + SiteID + " SiteName = " + SiteName + " S_city = " + S_city
                            + " S_latitude = " + S_latitude + " S_longitude = " + S_longitude + " AQI = " + AQI + " Status = " + Status + " PM25 = " + PM25 + " time = " + time);
        return;
        
    }
    @SuppressWarnings("deprecation")
    public AQI getAQI(Integer SiteID) {
        String SQL = "select * from AQI where SiteID = ?";
        AQI aqi = jdbcTemplateObject.queryForObject(SQL, new Object[]{SiteID}, new AQIMapper());
        return aqi;
    }
    public List<AQI> listAQIs() {
      String SQL = "select * from AQI";
      List <AQI> aqis = jdbcTemplateObject.query(SQL, 
                                new AQIMapper());
      return aqis;
    }
    public void delete(Integer SiteID){
        String SQL = "delete from AQI where SiteID = ?";
        jdbcTemplateObject.update(SQL, SiteID);
        System.out.println("Deleted Record with SiteID = " + SiteID );
        return;
    }
    public void update(Integer SiteID, float AQI, String Status, float PM25){
        String SQL = "update AQI set AQI = ?, set Status = ?, set PM25 = ?  where SiteID = ?";
        jdbcTemplateObject.update(SQL, AQI, Status, PM25, SiteID);
        System.out.println("Updated Record with SiteID = " + SiteID );
        return;
    }
}
