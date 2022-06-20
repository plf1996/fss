package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RecRestrictArea implements Serializable {
    private Integer idrestrictArea;

    private String name;

    private Double lat1;

    private Double long1;

    private Double lat2;

    private Double long2;

    private Double lat3;

    private Double long3;

    private Double lat4;

    private Double long4;

    private Double lat5;

    private Double long5;

    private String sh;

    private String eh;

    private String st;

    private String et;

    private Integer points;

    private static final long serialVersionUID = 1L;

    public Integer getIdrestrictArea() {
        return idrestrictArea;
    }

    public void setIdrestrictArea(Integer idrestrictArea) {
        this.idrestrictArea = idrestrictArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLat1() {
        return lat1;
    }

    public void setLat1(Double lat1) {
        this.lat1 = lat1;
    }

    public Double getLong1() {
        return long1;
    }

    public void setLong1(Double long1) {
        this.long1 = long1;
    }

    public Double getLat2() {
        return lat2;
    }

    public void setLat2(Double lat2) {
        this.lat2 = lat2;
    }

    public Double getLong2() {
        return long2;
    }

    public void setLong2(Double long2) {
        this.long2 = long2;
    }

    public Double getLat3() {
        return lat3;
    }

    public void setLat3(Double lat3) {
        this.lat3 = lat3;
    }

    public Double getLong3() {
        return long3;
    }

    public void setLong3(Double long3) {
        this.long3 = long3;
    }

    public Double getLat4() {
        return lat4;
    }

    public void setLat4(Double lat4) {
        this.lat4 = lat4;
    }

    public Double getLong4() {
        return long4;
    }

    public void setLong4(Double long4) {
        this.long4 = long4;
    }

    public Double getLat5() {
        return lat5;
    }

    public void setLat5(Double lat5) {
        this.lat5 = lat5;
    }

    public Double getLong5() {
        return long5;
    }

    public void setLong5(Double long5) {
        this.long5 = long5;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh == null ? null : sh.trim();
    }

    public String getEh() {
        return eh;
    }

    public void setEh(String eh) {
        this.eh = eh == null ? null : eh.trim();
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st == null ? null : st.trim();
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et == null ? null : et.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public RecRestrictArea(Integer idrestrictArea, String name, Double lat1, Double long1, Double lat2, Double long2, Double lat3, Double long3, Double lat4, Double long4, Double lat5, Double long5, String sh, String eh, String st, String et, Integer points) {
        this.idrestrictArea = idrestrictArea;
        this.name = name;
        this.lat1 = lat1;
        this.long1 = long1;
        this.lat2 = lat2;
        this.long2 = long2;
        this.lat3 = lat3;
        this.long3 = long3;
        this.lat4 = lat4;
        this.long4 = long4;
        this.lat5 = lat5;
        this.long5 = long5;
        this.sh = sh;
        this.eh = eh;
        this.st = st;
        this.et = et;
        this.points = points;
    }

    public RecRestrictArea() {
    }
}