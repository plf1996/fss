package com.kg.fss.controller;

import com.kg.fss.entity.Airport;
import com.kg.fss.service.AirportService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/airport")
public class AirportContrllor {
    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "/{id}")
    public String getAirportById(@PathVariable Integer id){
        Airport airport = airportService.getAirportById(id);
        return "test";
    }
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public   List<Airport> getAllAirport(Model model){
        List<Airport> airportList = airportService.getAllAirport();
        model.addAttribute("airportList",airportList);
        return airportList;
    }
}
