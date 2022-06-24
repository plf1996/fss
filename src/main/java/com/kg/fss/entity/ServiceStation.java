package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ServiceStation implements Serializable {
    private Integer idserviceStation;

    private Double lon;

    private Double lat;

    private Integer radius;

    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getIdserviceStation() {
        return idserviceStation;
    }

    public void setIdserviceStation(Integer idserviceStation) {
        this.idserviceStation = idserviceStation;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public ServiceStation(Integer idserviceStation, Double lon, Double lat, Integer radius, String name) {
        this.idserviceStation = idserviceStation;
        this.lon = lon;
        this.lat = lat;
        this.radius = radius;
        this.name = name;
    }

    public ServiceStation() {
    }
}