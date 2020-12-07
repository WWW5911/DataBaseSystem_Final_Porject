package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.ServiceTime.*;
public class ServiceTimeJDBCTemplate implements ServiceTimeDAO {
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public void create(Integer id, Integer week, Integer openTime, Integer closeTime){
        String SQL = "insert into ServiceTime (ID, week, openTime, closeTime) values (?, ?, ?, ?)";     
        jdbcTemplateObject.update( SQL, id, week, openTime, closeTime);
        System.out.println("Created Record ID = " + id + " week = " + week + " openTime = " + openTime + " closeTime = " + closeTime);
        return;
    }
    @SuppressWarnings("deprecation")
    public ServiceTime getServiceTime(Integer id) {
        String SQL = "select * from ServiceTime where ID = ?";
        ServiceTime ServiceTime = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new ServiceTimeMapper());
        return ServiceTime;
    }
    public List<ServiceTime> listServiceTimes() {
      String SQL = "select * from ServiceTime";
      List <ServiceTime> students = jdbcTemplateObject.query(SQL, 
                                new ServiceTimeMapper());
      return students;
    }
    public void delete(Integer id){
        String SQL = "delete from ServiceTime where ID = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }
    public void update(Integer id, Integer week, Integer openTime, Integer closeTime){
        String SQL = "update ServiceTime set grade = ?, set openTime = ?, set closeTime = ? where ID = ?";
        jdbcTemplateObject.update(SQL, week, openTime, closeTime, id);
        System.out.println("Updated Record with ID = " + id );
        return;
    }
}
