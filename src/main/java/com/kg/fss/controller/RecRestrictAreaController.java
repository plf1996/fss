package com.kg.fss.controller;

import com.kg.fss.entity.NavStation;
import com.kg.fss.entity.RecRestrictArea;
import com.kg.fss.service.RecRestrictAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/recRestrictArea")
public class RecRestrictAreaController {
@Autowired
    private RecRestrictAreaService recRestrictAreaService;
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<RecRestrictArea> getAllRecRestrictArea(Model model){
        List<RecRestrictArea> recRestrictAreaList = recRestrictAreaService.selectAllRecRestrictArea();
        model.addAttribute("recRestrictAreaList",recRestrictAreaList);
        return recRestrictAreaList;
    }
}
