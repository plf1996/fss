package com.kg.fss.dao;


import com.kg.fss.entity.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    int deleteByPrimaryKey(Integer idcity);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer idcity);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    List<City> selectAllCity();
}