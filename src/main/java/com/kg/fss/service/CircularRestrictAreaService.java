package com.kg.fss.service;

import com.kg.fss.dao.CircularRestrictAreaMapper;
import com.kg.fss.entity.CircularRestrictArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircularRestrictAreaService {
    @Autowired
    private CircularRestrictAreaMapper circularRestrictAreaMapper;

    public List<CircularRestrictArea> selectAllCircularRestrictArea(){
        return circularRestrictAreaMapper.selectAllCircularRestrictArea();
    }
}
