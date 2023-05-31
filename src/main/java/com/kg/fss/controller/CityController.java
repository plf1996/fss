package com.kg.fss.controller;

import com.kg.fss.entity.Airport;
import com.kg.fss.entity.AirportInCity;
import com.kg.fss.entity.City;
import com.kg.fss.service.AirportService;
import com.kg.fss.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kg.fss.util.Algorithm;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<City> getAllRecRestrictArea(Model model) {
        List<City> circularRestrictArea = cityService.selectAllCity();
        model.addAttribute("circularRestrictArea", circularRestrictArea);
        return circularRestrictArea;
    }
    @RequestMapping(value = "/lianxudongtai", method = RequestMethod.GET)
    @ResponseBody
    public List<AirportInCity> getAirportInCity(Model model, int radius) {
        List<Airport> airportList = airportService.getAllAirport();
        List<City> cityList = cityService.selectAllCity();
        List<AirportInCity> airportInCityList = Algorithm.lianxudongtai(cityList,airportList,radius);
        model.addAttribute("airportInCityList", airportInCityList);
        return airportInCityList;
    }

    @RequestMapping(value = "/jinjixiangying", method = RequestMethod.GET)
    @ResponseBody
    public List<AirportInCity> getAirportInCity1(Model model, int radius) {
        List<Airport> airportList = airportService.getAllAirport();
        List<City> cityList = cityService.selectAllCity();
        List<AirportInCity> airportInCityList = Algorithm.jinjixiangying(cityList,airportList,radius);
        model.addAttribute("airportInCityList", airportInCityList);
        return airportInCityList;
    }

    @RequestMapping(value = "/xuqiujuji", method = RequestMethod.GET)
    @ResponseBody
    public List<AirportInCity> getAirportInCity2(Model model, int radius) {
        List<Airport> airportList = airportService.getAllAirport();
        List<City> cityList = cityService.selectAllCity();
        List<AirportInCity> airportInCityList = Algorithm.lianxudongtai(cityList,airportList,radius);
        model.addAttribute("airportInCityList", airportInCityList);
        return airportInCityList;
    }
}
