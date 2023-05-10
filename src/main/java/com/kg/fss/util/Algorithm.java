package com.kg.fss.util;

import com.kg.fss.entity.Airport;
import com.kg.fss.entity.AirportInCity;
import com.kg.fss.entity.City;


import java.util.*;

public class Algorithm {
    /**
     * 默认地球半径
     */
    private static double EARTH_RADIUS = 6378000;//赤道半径(单位m)

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
                double distance = GetDistance(city.getCityLon(), city.getCityLat(), airport.getLon(), airport.getLat());
                if ( distance< radius) {
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

    /**
     *
     * @param cityList  城镇列表
     * @param airportList  机场列表
     * @param radius 半径
     * @return 返回城镇在当前半径下所包含的机场列表
     */
    public static List<AirportInCity> lianxudongtai(List<City> cityList, List<Airport> airportList, int radius){
        List<Airport> airportContent = new ArrayList<>();
        List<City> cityContent = new ArrayList<>();

        //复制
        for (int i=0;i<cityList.size();i++){
            City city = cityList.get(i);
            cityContent.add(city);
        }
        for (int j=0;j<airportList.size();j++){
            Airport airport = airportList.get(j);
            airportContent.add(airport);
        }

        List<AirportInCity> fangan = new ArrayList<>();
        while(airportContent.size()>0){
            City tempCity = new City();
            List<Airport> tempAirportList = new ArrayList<Airport>();
            //寻找当前半径中哪个城市所包含的机场数量最多
            List<AirportInCity> templist = Algorithm.GetAirportCount(cityContent,airportContent,radius);
            //将所选择的城市和机场加入数据结构中
            if(templist.size()>0){
                fangan.add(templist.get(0));
                 tempCity = templist.get(0).getCity();
                tempAirportList = templist.get(0).getAirportList();
            }

            //将已经选择的城市和机场从列表中抹除
            if (cityContent.contains(tempCity)){
                cityContent.remove(tempCity);
            }

            for (Airport airport1:tempAirportList) {
                if (airportContent.contains(airport1)){
                    airportContent.remove(airport1);
                }
            }

        }
        return fangan;
    }

    /**
     *
     * @param cityList
     * @param airportList
     * @param radius
     * @return
     */
    public static List<AirportInCity> jinjixiangying(List<City> cityList, List<Airport> airportList, int radius){
        List<Airport> airportContent = new ArrayList<>();
        List<City> cityContent = new ArrayList<>();

        //复制
        for (int i=0;i<cityList.size();i++){
            City city = cityList.get(i);
            cityContent.add(city);
        }
        for (int j=0;j<airportList.size();j++){
            Airport airport = airportList.get(j);
            airportContent.add(airport);
        }

        List<AirportInCity> fangan = new ArrayList<>();
        int i=0;
        while(airportContent.size()>0){
            City tempCity = new City();
            List<Airport> tempAirportList = new ArrayList<Airport>();
            //寻找当前半径中哪个城市所包含的机场数量最多
            List<AirportInCity> templist = Algorithm.GetAirportCount(cityContent,airportContent,radius);
            //将所选择的城市和机场加入数据结构中

            if(templist.size()>0){
                fangan.add(templist.get(0));
                tempCity = templist.get(0).getCity();
                tempAirportList = templist.get(0).getAirportList();
            }

            //将已经选择的城市和机场从列表中抹除
            if (cityContent.contains(tempCity)&&tempCity.getCityName()!="zigong"&&tempCity.getCityName()!="guangyuan"){
                cityContent.remove(tempCity);
            }

            for (Airport airport1:tempAirportList) {
                if (airportContent.contains(airport1)){
                    airportContent.remove(airport1);
                }
            }

        }
        return fangan;
    }
}


