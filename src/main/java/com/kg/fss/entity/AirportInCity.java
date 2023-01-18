package com.kg.fss.entity;

import java.util.List;

public class AirportInCity  {
     private List<Airport> airportList;
     private City city;
     private int count;

    public List<Airport> getAirportList() {
        return airportList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAirportList(List<Airport> airportList) {
        this.airportList = airportList;
        this.count = airportList.size();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public AirportInCity(List<Airport> airportList, City city) {
        this.airportList = airportList;
        this.city = city;
    }

    public AirportInCity() {
    }
}
