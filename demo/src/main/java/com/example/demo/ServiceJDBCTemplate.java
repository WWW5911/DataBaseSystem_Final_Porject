package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.Service.*;

public class ServiceJDBCTemplate implements ServiceDAO {
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public void create(Integer S_ID, Integer R_ID) {
        String SQL = "insert into Service (R_ID, S_ID) values (?, ?)";     
        jdbcTemplateObject.update( SQL, R_ID, S_ID);
        System.out.println("Created Record S_ID = " + S_ID + " R_ID = " + R_ID);
        return;
    }
    public List<Service> getServiceR(Integer R_ID) {
        String SQL = "select * from Service where R_ID = " + R_ID;
        List <Service> service = jdbcTemplateObject.query(SQL, new ServiceMapper());
        return service;
    }
    @SuppressWarnings("deprecation")
    public Service getService(Integer S_ID){
        String SQL = "select * from Service where S_ID = ?";
        Service service = jdbcTemplateObject.queryForObject(SQL, new Object[]{S_ID}, new ServiceMapper());
        return service;
    }
    public List<Service> listServices() {
      String SQL = "select * from Service";
      List <Service> services = jdbcTemplateObject.query(SQL, 
                                new ServiceMapper());
      return services;
    }
    public void delete(Integer S_ID){
        String SQL = "delete from Service where S_ID = ?";
        jdbcTemplateObject.update(SQL, S_ID);
        System.out.println("Deleted Record with S_ID = " + S_ID );
        return;
    }
    public void update(Integer S_ID, Integer R_ID){
        String SQL = "update Service set R_ID = ? where S_ID = ?";
        jdbcTemplateObject.update(SQL, R_ID, S_ID);
        System.out.println("Updated Record with S_ID = " + S_ID );
        return;
    }
}
