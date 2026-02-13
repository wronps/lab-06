package com.example.listycity;

import java.io.Serializable;

/**
 * This is a class that defines a City.
 */
public class City implements Serializable, Comparable<City>{
    private String city;
    private String province;
    City(String city, String province){
        this.city = city;
        this.province = province;
    }
    public String getCityName() {
        return this.city;
    }
    public String getProvince() {
        return this.province;
    }

    public void setCityName(String name) {
        this.city = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public int compareTo(City o) {
        City city = (City) o;
        return this.city.compareTo(city.getCityName()); // this.city refers to the city name
    }

}

