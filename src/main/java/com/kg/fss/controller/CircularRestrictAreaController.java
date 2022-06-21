package com.kg.fss.controller;

import com.kg.fss.entity.CircularRestrictArea;
import com.kg.fss.service.CircularRestrictAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/circularRestrictArea")
public class CircularRestrictAreaController {
    @Autowired
    private CircularRestrictAreaService circularRestrictAreaService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<CircularRestrictArea> getAllRecRestrictArea(Model model){
        List<CircularRestrictArea> circularRestrictArea = circularRestrictAreaService.selectAllCircularRestrictArea();
        model.addAttribute("circularRestrictArea",circularRestrictArea);
        return circularRestrictArea;
    }


}
