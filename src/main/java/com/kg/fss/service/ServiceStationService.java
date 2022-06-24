package com.kg.fss.service;

import com.kg.fss.dao.ServiceStationMapper;
import com.kg.fss.entity.ServiceStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStationService {
    @Autowired
    private ServiceStationMapper serviceStationMapper;
    public List<ServiceStation> selectAllServiceStation(){
        return serviceStationMapper.selectAllServiceStation();
    }
}
