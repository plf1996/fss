package com.kg.fss.service;

import com.kg.fss.dao.CityMapper;
import com.kg.fss.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
        private CityMapper cityMapper;

    public List<City> selectAllCity(){
        return cityMapper.selectAllCity();
    }
    public City selectByPrimaryKey(int id){
        return cityMapper.selectByPrimaryKey(id);
    }
}
