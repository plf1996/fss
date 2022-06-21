package com.kg.fss.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class CircularRestrictArea implements Serializable {
    private Integer idcircularRestrictArea;

    private Double centerLat;

    private Double centerLong;

    private Integer radius;

    private String sh;

    private String eh;

    private String st;

    private String et;
    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getIdcircularRestrictArea() {
        return idcircularRestrictArea;
    }

    public void setIdcircularRestrictArea(Integer idcircularRestrictArea) {
        this.idcircularRestrictArea = idcircularRestrictArea;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }

    public Double getCenterLong() {
        return centerLong;
    }

    public void setCenterLong(Double centerLong) {
        this.centerLong = centerLong;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public CircularRestrictArea(Integer idcircularRestrictArea, Double centerLat, Double centerLong, Integer radius, String sh, String eh, String st, String et, String name) {
        this.idcircularRestrictArea = idcircularRestrictArea;
        this.centerLat = centerLat;
        this.centerLong = centerLong;
        this.radius = radius;
        this.sh = sh;
        this.eh = eh;
        this.st = st;
        this.et = et;
        this.name = name;
    }

    public CircularRestrictArea() {
    }
}