package com.kg.fss.dao;

import com.kg.fss.entity.ServiceStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ServiceStationMapper {
    int deleteByPrimaryKey(Integer idserviceStation);

    int insert(ServiceStation record);

    int insertSelective(ServiceStation record);

    ServiceStation selectByPrimaryKey(Integer idserviceStation);

    int updateByPrimaryKeySelective(ServiceStation record);

    int updateByPrimaryKey(ServiceStation record);
    List<ServiceStation> selectAllServiceStation();
}