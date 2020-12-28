package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.schoolHighter.*;
import com.example.AQI.*;
import com.example.Weather.*;
import com.example.Restaurant.*;
import com.example.ServiceTime.*;
import com.example.Service.*;

@RestController
public class HomeController {
    static ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    static SchoolHighterJDBCTemplate schoolHighterJDBCTemplate = (SchoolHighterJDBCTemplate) context.getBean("schoolHighterJDBCTemplate");
    static AQIJDBCTemplate aqiJDBCTemplate = (AQIJDBCTemplate) context.getBean("AQIJDBCTemplate");
    static WeatherJDBCTemplate weatherJDBCTemplate = (WeatherJDBCTemplate) context.getBean("WeatherJDBCTemplate");
    static RestaurantJDBCTemplate restaurantJDBCTemplate = (RestaurantJDBCTemplate) context.getBean("RestaurantJDBCTemplate");
    static ServiceTimeJDBCTemplate serviceTimeJDBCTemplate = (ServiceTimeJDBCTemplate) context.getBean("ServiceTimeJDBCTemplate");
    static ServiceJDBCTemplate serviceJDBCTemplate = (ServiceJDBCTemplate) context.getBean("ServiceJDBCTemplate");
    static MixedResultJDBCTemplate mixedResultJDBCTemplate = (MixedResultJDBCTemplate) context.getBean("MixedResultJDBCTemplate");

    List<SchoolHighter> students = schoolHighterJDBCTemplate.listSchoolHighters();
    List<AQI> aqis = aqiJDBCTemplate.listAQIs();
    List<Weather> weathers = weatherJDBCTemplate.listWeathers();
    List<Restaurant> restaurants = restaurantJDBCTemplate.listRestaurants();
    List<ServiceTime> serviceTimes = serviceTimeJDBCTemplate.listServiceTimes();
    List<Service> services = serviceJDBCTemplate.listServices();

    AES_CBC_PK5 aPk5 = new AES_CBC_PK5();

    public static void main(String[] args) {

    }
    
    @RequestMapping("")
    public String hello() {
        File index = new File("src\\main\\java\\web\\index.html");
        String path = index.getAbsolutePath();
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return path;
        }
    }
    @RequestMapping("/en/{str}")
    public String enTest(@PathVariable("str") String str ) throws Exception {
        return Base64.getEncoder().encodeToString( aPk5.Encrypt(str)).replace('/', '@');
    }
    @RequestMapping("/de/{str}")
    public String deTest(@PathVariable("str") String str ) throws Exception {
        return aPk5.Decrypt( str.replace('@', '/') );
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

    @RequestMapping(path = "/api/ServiceTime/{id}")
    public String getServiceTimeID( @PathVariable("id") int id  ){
        ServiceTime serviceTime = serviceTimeJDBCTemplate.getServiceTime(id);
        return "ID = " + serviceTime.getID() + " week = " + serviceTime.getWeek() + " openTime = " + serviceTime.getOpenTime() + " closeTime = " + serviceTime.getCloseTime();
    }
    @RequestMapping(path = "/api/ServiceTime/list")
    public String getServiceTimeALL(){
        List<ServiceTime> li = serviceTimeJDBCTemplate.listServiceTimes();
        String str = "";
        li.sort(Comparator.comparing(ServiceTime::getID));
        for(ServiceTime serviceTime : li)
            str += "ID = " + serviceTime.getID() + " week = " + serviceTime.getWeek() + " openTime = " + serviceTime.getOpenTime() + " closeTime = " + serviceTime.getCloseTime() +"\n" ;
        return str;
    }

    @RequestMapping(path = "/api/Service/S/{id}")
    public String getServiceS_ID( @PathVariable("id") int id  ){
        Service service = serviceJDBCTemplate.getService(id);
        return "S_ID : " + service.getS_ID() + ", R_ID : " + service.getR_ID() ;
    }
    @RequestMapping(path = "/api/Service/R/{id}")
    public String getServiceR_ID( @PathVariable("id") int id  ){
        List<Service> li = serviceJDBCTemplate.getServiceR(id);
        String str = "";
        li.sort(Comparator.comparing(Service::getS_ID));
        for(Service service : li)
            str += "R_ID : " + service.getR_ID() + ", S_ID : " + service.getS_ID() +"\n" ;
        return str;
    }
    @RequestMapping(path = "/api/Service/list")
    public String getServiceALL(){
        List<Service> li = serviceJDBCTemplate.listServices();
        String str = "";
        li.sort(Comparator.comparing(Service::getR_ID));
        for(Service service : li)
            str += "S_ID : " + service.getS_ID() + ", R_ID : " + service.getR_ID() +"\n" ;
        return str;
    }
    @RequestMapping(path = "/api/Request/{id}")
    public String getRequest( @PathVariable("id") String str  ){
        return reply.SelectResult(str);
    }

    @RequestMapping(path = "/api/updateAQI")
    public String updateAQI( ){
        UpdateData.update_AQI();
        return "updated";
    }
    @RequestMapping(path = "/api/updateWeather")
    public String updateWeather( ){
        UpdateData.update_weather();
        return "updated";
    }
}