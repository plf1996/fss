package com.kg.fss;

import com.kg.fss.entity.Airport;
import com.kg.fss.entity.AirportInCity;
import com.kg.fss.entity.City;
import com.kg.fss.service.AirportService;
import com.kg.fss.service.CityService;
import com.kg.fss.util.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FssApplicationTests {
    @Autowired
    AirportService airportService;
    @Autowired
    CityService cityService;
    @Test
    void contextLoads() {


    }

}
