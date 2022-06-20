package com.kg.fss.service;

import com.kg.fss.dao.RecRestrictAreaMapper;
import com.kg.fss.entity.RecRestrictArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecRestrictAreaService {
    @Autowired
    private RecRestrictAreaMapper recRestrictAreaMapper;

    public List<RecRestrictArea> selectAllRecRestrictArea(){
        return recRestrictAreaMapper.selectAllRecRestrictArea();
    }
}
