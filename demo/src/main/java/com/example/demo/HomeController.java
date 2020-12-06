package com.example.demo;

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

import com.example.schoolHighter.*;
import com.example.AQI.*;



@RestController
public class HomeController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    SchoolHighterJDBCTemplate schoolHighterJDBCTemplate = (SchoolHighterJDBCTemplate) context.getBean("schoolHighterJDBCTemplate");
    AQIJDBCTemplate aqiJDBCTemplate = (AQIJDBCTemplate) context.getBean("AQIJDBCTemplate");


    List<SchoolHighter> students = schoolHighterJDBCTemplate.listSchoolHighters();
    List<AQI> aqis = aqiJDBCTemplate.listAQIs();

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
    @RequestMapping(path = "/api/schoolHighter/{id}")
    public String getSchoolHighterID( @PathVariable("id") int id  ){
        SchoolHighter student = schoolHighterJDBCTemplate.getSchoolHighter(id);
        return "ID : " + student.getId() + ", Name : " + student.getName() + ", Grade : " + student.getGrade();
    }
    @RequestMapping(path = "/api/schoolHighter/list")
    public String getSchoolHighterALL(){
        List<SchoolHighter> li = schoolHighterJDBCTemplate.listSchoolHighters();
        String str = "";
        li.sort(Comparator.comparing(SchoolHighter::getId));
        for(SchoolHighter sh : li)
            str += "ID : " + sh.getId() + ", Name : " + sh.getName() + ", Grade : " + sh.getGrade() +"\n" ;
        return str;
    }

    @RequestMapping(path = "/api/AQI/{id}")
    public String getID( @PathVariable("id") int id  ){
        AQI aqi = aqiJDBCTemplate.getAQI(id);
        return "ID : " + aqi.getSiteId() + ", SiteName : " + aqi.getSiteName() + ", S_city : " + aqi.getS_city()
                + " S_latitude : " + aqi.getS_latitude() + " S_longitude : " + aqi.getS_longitude() + " AQI : " 
                + aqi.getAQI() + " Status : " + aqi.getStatus() + " PM25 : " + aqi.getPM25();
    }

    @RequestMapping(path = "/api/AQI/list")
    public String getAQIALL(){
        List<AQI> li = aqiJDBCTemplate.listAQIs();
        String str = "";
        li.sort(Comparator.comparing(AQI::getSiteId));
        for(AQI aqi : li)
            str += "ID : " + aqi.getSiteId() + ", SiteName : " + aqi.getSiteName() + ", S_city : " + aqi.getS_city()
            + " S_latitude : " + aqi.getS_latitude() + " S_longitude : " + aqi.getS_longitude() + " AQI : " 
            + aqi.getAQI() + " Status : " + aqi.getStatus() + " PM25 : " + aqi.getPM25() +"\n" ;
        return str;
    }

}