package com.kg.fss.controller;

import com.kg.fss.entity.NavStation;
import com.kg.fss.service.NavStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller()
@RequestMapping("/navStation")
public class NavStationController {
    @Autowired
    private NavStationService navStationService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<NavStation> getAllNavStation(Model model){
        List<NavStation> navStationList = navStationService.getAllNavStation();
        model.addAttribute("navStationList",navStationList);
        return navStationList;
    }
}
