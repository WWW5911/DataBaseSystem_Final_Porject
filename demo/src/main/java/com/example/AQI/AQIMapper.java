package com.example.AQI;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AQIMapper implements RowMapper<AQI> {
   public AQI mapRow(ResultSet rs, int rowNum) throws SQLException {
      AQI aqi = new AQI();
      aqi.setSiteId(rs.getInt("SiteId"));
      aqi.setSiteName(rs.getString("SiteName"));
      aqi.setS_city(rs.getString("S_city"));
      aqi.setS_latitude(rs.getFloat("S_latitude"));
      aqi.setS_longitude(rs.getFloat("S_longitude"));
      aqi.setAQI(rs.getFloat("AQI"));
      aqi.setStatus(rs.getString("Status"));
      aqi.setPM25(rs.getFloat("PM25"));
      //aqi.setTime(rs.getInt("time"));
      return aqi;
   }
}
