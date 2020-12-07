package com.example.ServiceTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ServiceTimeMapper implements RowMapper<ServiceTime> {
   public ServiceTime mapRow(ResultSet rs, int rowNum) throws SQLException {
      ServiceTime serviceTime = new ServiceTime();
      serviceTime.setID(rs.getInt("ID"));
      serviceTime.setWeek(rs.getInt("week"));
      serviceTime.setOpenTime(rs.getInt("openTime"));
      serviceTime.setCloseTime(rs.getInt("closeTime"));
      return serviceTime;
   }
}
