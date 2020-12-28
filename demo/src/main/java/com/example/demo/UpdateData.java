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
    @Scheduled(fixedRate = 10800000)
    public void reportCurrentTime() throws InterruptedException {
        System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++, dateFormat.format(new Date())));
        update_AQI();
        update_weather();
    }

    public static void update_AQI() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("cmd.exe /c python C:\\Users\\Administrator\\Desktop\\Sublime\\AQI\\AQI.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                float AQI = 0, PM25 = 0;
                Integer SiteId = 0;
                String status = "良好";
                if(tokens[0] != null && tokens[0].length() > 0)
                    SiteId = Integer.valueOf(tokens[0]);
                if(tokens.length > 1 && tokens[1] != null && tokens[1].length() > 0)
                    AQI = Float.parseFloat(tokens[1]);
                if(tokens.length > 2 && tokens[2] != null && tokens[2].length() > 0)
                    PM25 = Float.parseFloat(tokens[2]);
                if(tokens.length > 3 && tokens[3] != null && tokens[3].length() > 0)
                    status = tokens[3];
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
            p = Runtime.getRuntime().exec("cmd.exe /c python C:\\Users\\Administrator\\Desktop\\Sublime\\Weather\\Weather.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                Integer ID = 0, pop = 0;
                String date = "0", Wx = "0";
                double temperature = 0.0, AvgAt = 0.0;
                if(tokens[0] != null && tokens[0].length() > 0)
                    ID = Integer.valueOf(tokens[0]);
                if(tokens.length > 1 && tokens[1] != null && tokens[1].length() > 0)
                    date = tokens[1];
                if(tokens.length > 2 && tokens[2] != null && tokens[2].length() > 0)
                    Wx = tokens[2];
                if(tokens.length > 3 && tokens[3] != null && tokens[3].length() > 0)
                    pop = Integer.valueOf(tokens[3]);
                if(tokens.length > 4 && tokens[4] != null && tokens[4].length() > 0)
                    temperature = Double.parseDouble(tokens[4]);
                if(tokens.length > 5 && tokens[5] != null && tokens[5].length() > 0)
                    AvgAt =  Double.parseDouble(tokens[5]);
                System.out.println(ID + " " + date + " " + Wx + " " + pop + " " + temperature + " " + AvgAt);
                HomeController.weatherJDBCTemplate.update(ID, date, Wx, pop, temperature, AvgAt);
            }
            in.close();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
