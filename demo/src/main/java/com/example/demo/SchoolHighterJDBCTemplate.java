package com.example.demo;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class SchoolHighterJDBCTemplate implements SchoolHighterDAO{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject; 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public void create(String name, Integer grade) {
        String SQL = "insert into Highschooler (name, grade) values (?, ?)";     
        jdbcTemplateObject.update( SQL, name, grade);
        System.out.println("Created Record Name = " + name + " Age = " + grade);
        return;
    }
    public SchoolHighter getSchoolHighter(Integer id) {
        String SQL = "select * from Highschooler where id = ?";
        SchoolHighter schoolHighter = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new SchoolHighterMapper());
        return schoolHighter;
    }
    public List<SchoolHighter> listSchoolHighters() {
      String SQL = "select * from Highschooler";
      List <SchoolHighter> students = jdbcTemplateObject.query(SQL, 
                                new SchoolHighterMapper());
      return students;
    }
    public void delete(Integer id){
        String SQL = "delete from Highschooler where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }
    public void update(Integer id, Integer grade){
        String SQL = "update Highschooler set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, grade, id);
        System.out.println("Updated Record with ID = " + id );
        return;
    }
}
