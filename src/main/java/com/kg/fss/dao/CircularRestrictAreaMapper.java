package com.kg.fss.dao;

import com.kg.fss.entity.CircularRestrictArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CircularRestrictAreaMapper {
    int deleteByPrimaryKey(Integer idcircularRestrictArea);

    int insert(CircularRestrictArea record);

    int insertSelective(CircularRestrictArea record);

    CircularRestrictArea selectByPrimaryKey(Integer idcircularRestrictArea);

    int updateByPrimaryKeySelective(CircularRestrictArea record);

    int updateByPrimaryKey(CircularRestrictArea record);
    List<CircularRestrictArea> selectAllCircularRestrictArea();
}