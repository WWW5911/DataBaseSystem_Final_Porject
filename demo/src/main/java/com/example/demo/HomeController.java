package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.SchoolHighterJDBCTemplate;



@RestController
public class HomeController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    SchoolHighterJDBCTemplate schoolHighterJDBCTemplate = (SchoolHighterJDBCTemplate) context.getBean("schoolHighterJDBCTemplate");
    List<SchoolHighter> students = schoolHighterJDBCTemplate.listSchoolHighters();
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    @RequestMapping("")
    public String hello() {
        File index = new File("demo\\src\\main\\java\\web\\index.html");
        String path = index.getAbsolutePath();
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return "there is nothing";
        }
    }
    @RequestMapping("/css/{file}")
    public String getCss(@PathVariable("file") String fi ) {
        File index = new File("demo\\src\\main\\java\\web\\css\\" + fi);
        String path = index.getAbsolutePath();
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return path;
        }
    }
    @RequestMapping(path = "/api/{id}")
    public String getID( @PathVariable("id") int id  ){
        SchoolHighter student = schoolHighterJDBCTemplate.getSchoolHighter(id);
        return "ID : " + student.getId() + ", Name : " + student.getName() + ", Grade : " + student.getGrade();
    }
    @RequestMapping(path = "/api/list")
    public String getALL(){
        List<SchoolHighter> li = schoolHighterJDBCTemplate.listSchoolHighters();
        String str = "";
        li.sort(Comparator.comparing(SchoolHighter::getId));
        for(SchoolHighter sh : li)
            str += "ID : " + sh.getId() + ", Name : " + sh.getName() + ", Grade : " + sh.getGrade() +"\n" ;
        return str;
    }

}