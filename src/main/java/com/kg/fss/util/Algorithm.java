package com.kg.fss.util;

import com.kg.fss.entity.Airport;
import com.kg.fss.entity.AirportInCity;
import com.kg.fss.entity.City;


import java.util.*;

public class Algorithm {
    /**
     * 默认地球半径
     */
    private static double EARTH_RADIUS = 6371000;//赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     */
    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     *
     * @param cityList 城市列表
     * @param airportList 机场列表
     * @param radius 半径
     * @return 以城市为圆心，半径为radius中的所有机场列表
     */
    public static List<AirportInCity> GetAirportCount(List<City> cityList, List<Airport> airportList, int radius) {
       List<AirportInCity> airportInCities = new ArrayList<>();
        for (City city : cityList
        ) {
            AirportInCity airportInCity = new AirportInCity();
            airportInCity.setCity(city);
            List<Airport> inairport = new ArrayList<>();
            for (Airport airport: airportList
                 ) {
                if (GetDistance(city.getCityLon(), city.getCityLat(), airport.getLon(), airport.getLat()) < radius) {
                    inairport.add(airport);
                }
            }
            airportInCity.setAirportList(inairport);
            airportInCities.add(airportInCity);
        }
        airportInCities.sort(new Comparator<AirportInCity>() {
            @Override
            public int compare(AirportInCity o1, AirportInCity o2) {
                return o2.getCount()-o1.getCount();
            }
        });
        return airportInCities;
    }


}


