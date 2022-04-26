package com.kg.fss.dao;

import com.kg.fss.entity.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirportMapper {
    int deleteByPrimaryKey(Integer airportsId);

    int insert(Airport record);

    int insertSelective(Airport record);

    Airport selectByPrimaryKey(Integer airportsId);

    int updateByPrimaryKeySelective(Airport record);

    int updateByPrimaryKey(Airport record);
    List<Airport> selectAllAirport();
}