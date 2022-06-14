package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class NavStation implements Serializable {
    private Integer stationId;

    private String stationName;

    private Double navlon;

    private Double navlat;

    private String navtype;

    private String code;

    private String ch;

    private String freq;

    private static final long serialVersionUID = 1L;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public Double getNavlon() {
        return navlon;
    }

    public void setNavlon(Double navlon) {
        this.navlon = navlon;
    }

    public Double getNavlat() {
        return navlat;
    }

    public void setNavlat(Double navlat) {
        this.navlat = navlat;
    }

    public String getNavtype() {
        return navtype;
    }

    public void setNavtype(String navtype) {
        this.navtype = navtype == null ? null : navtype.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch == null ? null : ch.trim();
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq == null ? null : freq.trim();
    }

    public NavStation(Integer stationId, String stationName, Double navlon, Double navlat, String navtype, String code, String ch, String freq) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.navlon = navlon;
        this.navlat = navlat;
        this.navtype = navtype;
        this.code = code;
        this.ch = ch;
        this.freq = freq;
    }

    public NavStation() {
    }
}