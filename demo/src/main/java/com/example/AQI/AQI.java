package com.example.AQI;

public class AQI {
    private int SiteId;
    private String SiteName;
    private String S_city;
    private double S_latitude;
    private double S_longitude;
    private double AQI;
    private String Status;
    private double PM25;
    private int time;

    public void setSiteId(int siteId) {
        this.SiteId = siteId;
    }
    public int getSiteId() {
        return SiteId;
    }
    public void setSiteName(String siteName) {
        this.SiteName = siteName;
    }
    public String getSiteName() {
        return SiteName;
    }
    public void setS_city(String S_city) {
        this.S_city = S_city;
    }
    public String getS_city() {
        return S_city;
    }
    public void setS_latitude(float s_latitude) {
        this.S_latitude = s_latitude;
    }
    public double getS_latitude() {
        return S_latitude;
    }
    public void setS_longitude(float s_longitude) {
        this.S_longitude = s_longitude;
    }
    public double getS_longitude() {
        return S_longitude;
    }
    public void setAQI(float AQI) {
        this.AQI = AQI;
    }
    public double getAQI() {
        return AQI;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public String getStatus() {
        return Status;
    }

    public void setPM25(float PM25) {
        this.PM25 = PM25;
    }
    public double getPM25() {
        return PM25;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public double getTime() {
        return time;
    }
}
