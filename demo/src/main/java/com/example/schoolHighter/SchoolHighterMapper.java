package com.example.schoolHighter;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class SchoolHighterMapper implements RowMapper<SchoolHighter> {
   public SchoolHighter mapRow(ResultSet rs, int rowNum) throws SQLException {
      SchoolHighter student = new SchoolHighter();
      student.setId(rs.getInt("id"));
      student.setName(rs.getString("name"));
      student.setGrade(rs.getInt("grade"));
      return student;
   }
}
