package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Airport implements Serializable {
    private Integer airportsId;

    private String name;

    private String code;

    private Double lon;

    private Double lat;

    private Double msl;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getAirportsId() {
        return airportsId;
    }

    public void setAirportsId(Integer airportsId) {
        this.airportsId = airportsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Double getMsl() {
        return msl;
    }

    public void setMsl(Double msl) {
        this.msl = msl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Airport(Integer airportsId, String name, String code, Double lon, Double lat, Double msl, Integer type) {
        this.airportsId = airportsId;
        this.name = name;
        this.code = code;
        this.lon = lon;
        this.lat = lat;
        this.msl = msl;
        this.type = type;
    }

    public Airport() {
    }
}