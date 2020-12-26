package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableScheduling
public class UpdateData {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count0 = 1;

    //10800000ms
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++, dateFormat.format(new Date())));
    }

    public static void update_AQI() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("cmd.exe /c python C:\\Users\\User\\Desktop\\Pycharm\\AQI\\main.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                Integer SiteId = Integer.valueOf(tokens[0]);
                float AQI = Float.parseFloat(tokens[1]);
                float PM25 = Float.parseFloat(tokens[2]);
                String status = tokens[3];
                System.out.println(SiteId + " " + AQI + " " + status + " " + PM25);
                HomeController.aqiJDBCTemplate.update(SiteId, AQI, status, PM25);
            }
            in.close();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void update_weather() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("cmd.exe /c python C:\\Users\\User\\Desktop\\Pycharm\\weather\\main.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                Integer ID = Integer.valueOf(tokens[0]);
                String date = tokens[1];
                String Wx = tokens[2];
                Integer pop = Integer.valueOf(tokens[3]);
                double temperature = Double.parseDouble(tokens[4]);
                double AvgAt = Double.parseDouble(tokens[5]);
                System.out.println(ID + " " + date + " " + Wx + " " + pop + " " + temperature + " " + AvgAt);
                HomeController.weatherJDBCTemplate.update(ID, date, Wx, pop, temperature, AvgAt);
            }
            in.close();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        SpringApplication.run(UpdateData.class, args);
        update_AQI();
        update_weather();
    }
}
