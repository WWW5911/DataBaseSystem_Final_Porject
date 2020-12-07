package com.example.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ServiceMapper implements RowMapper<Service> {
   public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
      Service service = new Service();
      service.setR_ID(rs.getInt("R_ID"));
      service.setS_ID(rs.getInt("S_ID"));
      return service;
   }
}
