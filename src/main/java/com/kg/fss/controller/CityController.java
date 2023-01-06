package com.kg.fss.controller;

import com.kg.fss.entity.City;
import com.kg.fss.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<City> getAllRecRestrictArea(Model model) {
        List<City> circularRestrictArea = cityService.selectAllCity();
        model.addAttribute("circularRestrictArea", circularRestrictArea);
        return circularRestrictArea;
    }
}
