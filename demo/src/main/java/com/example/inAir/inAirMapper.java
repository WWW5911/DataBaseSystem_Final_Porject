package com.example.inAir;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class inAirMapper implements RowMapper<inAir> {
   public inAir mapRow(ResultSet rs, int rowNum) throws SQLException {
      inAir inair = new inAir();
      inair.setR_ID(rs.getInt("R_ID"));
      inair.setA_SiteID(rs.getInt("A_SiteId"));
      return inair;
   }
}
