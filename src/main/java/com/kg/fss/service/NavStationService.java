package com.kg.fss.service;

import com.kg.fss.dao.NavStationMapper;
import com.kg.fss.entity.Airport;
import com.kg.fss.entity.NavStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavStationService {
    @Autowired
    private NavStationMapper navStationMapper;
    public List<NavStation> getAllNavStation(){
        return navStationMapper.selectAllNavStation();
    }
    public int insert(NavStation navStation){
        return navStationMapper.insert(navStation);
    }
    public int update(NavStation navStation){
        return navStationMapper.updateByPrimaryKey(navStation);
    }
}
