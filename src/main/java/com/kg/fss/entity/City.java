package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class City implements Serializable {
    private Integer idcity;

    private String cityName;

    private Double cityLon;

    private Double cityLat;

    private static final long serialVersionUID = 1L;

    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Double getCityLon() {
        return cityLon;
    }

    public void setCityLon(Double cityLon) {
        this.cityLon = cityLon;
    }

    public Double getCityLat() {
        return cityLat;
    }

    public void setCityLat(Double cityLat) {
        this.cityLat = cityLat;
    }

    public City(Integer idcity, String cityName, Double cityLon, Double cityLat) {
        this.idcity = idcity;
        this.cityName = cityName;
        this.cityLon = cityLon;
        this.cityLat = cityLat;
    }

    public City() {
    }
}