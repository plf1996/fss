package com.kg.fss.dao;

import com.kg.fss.entity.RecRestrictArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RecRestrictAreaMapper {
    int deleteByPrimaryKey(Integer idrestrictArea);

    int insert(RecRestrictArea record);

    int insertSelective(RecRestrictArea record);

    RecRestrictArea selectByPrimaryKey(Integer idrestrictArea);

    int updateByPrimaryKeySelective(RecRestrictArea record);

    int updateByPrimaryKey(RecRestrictArea record);
    List<RecRestrictArea> selectAllRecRestrictArea();
}