package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.MixedResult.*;



@SuppressWarnings("unused")
public class MixedResultJDBCTemplate {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<MixedResult> listMixedResults(String str) {
      String SQL = str;
      List <MixedResult> restaurants = jdbcTemplateObject.query(SQL, 
                                new MixedResultMapper());
      return restaurants;
    }
}
