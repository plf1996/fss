package com.kg.fss.service;

import com.kg.fss.dao.AirportMapper;
import com.kg.fss.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportMapper airportMapper;
    public Airport getAirportById(Integer id){
        return airportMapper.selectByPrimaryKey(id);
    }
    public List<Airport> getAllAirport(){
        return airportMapper.selectAllAirport();
    }
    public int insert(Airport airport){
        return airportMapper.insert(airport);
    }
    public int update(Airport airport){
        return airportMapper.updateByPrimaryKey(airport);
    }

}
