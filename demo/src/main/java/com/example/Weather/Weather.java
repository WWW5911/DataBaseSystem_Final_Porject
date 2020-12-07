package com.example.Weather;

public class Weather {
    private int ID;
    private String date;
    private String startTime;
    private String endTime;
    private String city;
    private String District;
    private String Wx;
    private int pop;
    private double temperature;
    private double AvgAt;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setDistrict(String District) {
        this.District = District;
    }
    public String getDistrict() {
        return District;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    public void setWx(String Wx) {
        this.Wx = Wx;
    }
    public String getWx() {
        return Wx;
    }
    public void setPop(int pop) {
        this.pop = pop;
    }
    public double getPop() {
        return pop;
    }
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setAvgAt(float AvgAt) {
        this.AvgAt = AvgAt;
    }
    public double getAvgAt() {
        return AvgAt;
    }
}
