package com.example.MixedResult;

public class MixedResult{
    public int ID;
    public double latitude;
    public double longitude;
    public String name;
    public String Address;
    public double rating;
    public int Air;
    public String Status;
    public String Wx;
    public int pop;
    public double temperature;
    public double AvgAt;
    public double weight;
    public int openTime;
    public int closeTime;

    public double getWeight(){
        return weight;
    }
    public String toQuery(){
        return ID + "@ " + name + "@ " + Address + "@ " + rating + "@ " + Air + "@ " + Status + "@ " + Wx + "@ " + pop + "@ " + temperature + "@ " + AvgAt + "@ " + openTime + "@ " + closeTime;
    }
}