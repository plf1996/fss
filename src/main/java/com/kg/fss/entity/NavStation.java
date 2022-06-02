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

    public NavStation(Integer stationId, String stationName, Double navlon, Double navlat, String navtype) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.navlon = navlon;
        this.navlat = navlat;
        this.navtype = navtype;
    }

    public NavStation() {
    }
}