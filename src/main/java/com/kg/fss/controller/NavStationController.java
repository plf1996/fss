package com.kg.fss.controller;

import com.kg.fss.entity.NavStation;
import com.kg.fss.service.NavStationService;
import com.kg.fss.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/save")
    public String saveNavStation(NavStation navStation, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int  totalResult = 0;
        if (navStation.getStationId()==null){
            totalResult = navStationService.insert(navStation);
        }else{
            totalResult = navStationService.update(navStation);
        }
        JSONObject result=new JSONObject();
        if(totalResult>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        model.addAttribute("result",result);
        ResponseUtil.write(response,result);
        return "navigationStation";
    }
}
