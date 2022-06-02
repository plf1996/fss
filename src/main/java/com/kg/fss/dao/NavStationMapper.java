package com.kg.fss.dao;

import com.kg.fss.entity.NavStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NavStationMapper {
    int deleteByPrimaryKey(Integer stationId);

    int insert(NavStation record);

    int insertSelective(NavStation record);

    NavStation selectByPrimaryKey(Integer stationId);

    int updateByPrimaryKeySelective(NavStation record);

    int updateByPrimaryKey(NavStation record);
    List<NavStation> selectAllNavStation();
}