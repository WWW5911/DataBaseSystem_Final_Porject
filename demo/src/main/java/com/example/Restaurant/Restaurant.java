package com.example.Restaurant;

public class Restaurant {
    private int ID;
    private String name;
    private String city;
    private String District;
    private double latitude;
    private double longitude;
    private String Address;
    private double rating;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    public void setDistrict(String District) {
        this.District = District;
    }
    public String getDistrict() {
        return District;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getAddress() {
        return Address;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public double getRating() {
        return rating;
    }
}
