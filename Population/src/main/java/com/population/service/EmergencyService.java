package com.population.service;

import java.util.List;

import com.population.mapper.EmergencyMapper;
import com.population.vo.EmergencyVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyService {
    @Autowired
    EmergencyMapper mapper;
    public void insertEmergencyinfo(EmergencyVO vo){
        mapper.insertEmergencyinfo(vo);
    }
    public List<EmergencyVO> selectEmergencyList(String routeNm){
        return mapper.selectEmergencyList(routeNm);
    }
    public List<EmergencyVO> selectEmer(String frwdIc){
        return mapper. selectEmer(frwdIc);
    }
}
