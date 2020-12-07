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
import com.example.Weather.*;
import com.example.Restaurant.*;

@RestController
public class HomeController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    SchoolHighterJDBCTemplate schoolHighterJDBCTemplate = (SchoolHighterJDBCTemplate) context.getBean("schoolHighterJDBCTemplate");
    AQIJDBCTemplate aqiJDBCTemplate = (AQIJDBCTemplate) context.getBean("AQIJDBCTemplate");
    WeatherJDBCTemplate weatherJDBCTemplate = (WeatherJDBCTemplate) context.getBean("WeatherJDBCTemplate");
    RestaurantJDBCTemplate restaurantJDBCTemplate = (RestaurantJDBCTemplate) context.getBean("RestaurantJDBCTemplate");

    List<SchoolHighter> students = schoolHighterJDBCTemplate.listSchoolHighters();
    List<AQI> aqis = aqiJDBCTemplate.listAQIs();
    List<Weather> weathers = weatherJDBCTemplate.listWeathers();
    List<Restaurant> restaurant = restaurantJDBCTemplate.listRestaurants();

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
    public String getAQIID( @PathVariable("id") int id  ){
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

    @RequestMapping(path = "/api/Weather/{id}")
    public String getWeatherID( @PathVariable("id") int id  ){
        Weather weather = weatherJDBCTemplate.getWeather(id);
        return "ID = " + weather.getID() + " date = " + weather.getDate() + " startTime = " + weather.getStartTime() + " endTime = " + weather.getEndTime() 
        + " city = " + weather.getCity() + " District = " + weather.getDistrict() + " Wx = " + weather.getWx() + " pop = " + weather.getPop() 
        + " temperature = " + weather.getTemperature() + " AvgAt = " + weather.getAvgAt();
    }

    @RequestMapping(path = "/api/Weather/list")
    public String getWeatherALL(){
        List<Weather> li = weatherJDBCTemplate.listWeathers();
        String str = "";
        li.sort(Comparator.comparing(Weather::getID));
        for(Weather weather : li)
            str += "ID = " + weather.getID() + " date = " + weather.getDate() + " startTime = " + weather.getStartTime() + " endTime = " + weather.getEndTime() 
            + " city = " + weather.getCity() + " District = " + weather.getDistrict() + " Wx = " + weather.getWx() + " pop = " + weather.getPop() 
            + " temperature = " + weather.getTemperature() + " AvgAt = " + weather.getAvgAt() +"\n" ;
        return str;
    }

    @RequestMapping(path = "/api/Restaurant/{id}")
    public String getRestaurantID( @PathVariable("id") int id  ){
        Restaurant restaurant = restaurantJDBCTemplate.getRestaurant(id);
        return "ID : " + restaurant.getID() + ", name : " + restaurant.getName() + ", City : " + restaurant.getCity()+ " District : " + restaurant.getDistrict() 
                + " latitude : " + restaurant.getLatitude() + " longitude : " + restaurant.getLongitude() + " Address : " 
                + restaurant.getAddress() + " rating : " + restaurant.getRating();
    }

    @RequestMapping(path = "/api/Restaurant/list")
    public String getRestaurantALL(){
        List<Restaurant> li = restaurantJDBCTemplate.listRestaurants();
        String str = "";
        li.sort(Comparator.comparing(Restaurant::getID));
        for(Restaurant restaurant : li)
            str += "ID : " + restaurant.getID() + ", name : " + restaurant.getName() + ", City : " + restaurant.getCity()+ " District : " + restaurant.getDistrict() 
            + " latitude : " + restaurant.getLatitude() + " longitude : " + restaurant.getLongitude() + " Address : " 
            + restaurant.getAddress() + " rating : " + restaurant.getRating() +"\n" ;
        return str;
    }

}