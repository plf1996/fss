package com.kg.fss.controller;

import com.kg.fss.entity.ServiceStation;
import com.kg.fss.service.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@RequestMapping("serviceStation")
public class ServiceStationController {
    @Autowired
    private ServiceStationService serviceStationService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<ServiceStation> getAllServiceStation(Model model){
        List<ServiceStation> recRestrictAreaList = serviceStationService.selectAllServiceStation();
        model.addAttribute("recRestrictAreaList",recRestrictAreaList);
        return recRestrictAreaList;
    }
}
