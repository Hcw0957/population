package com.population.service;

import java.util.List;

import com.population.mapper.TrafficMapper;
import com.population.vo.TrafficVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrafficService {
    @Autowired
    TrafficMapper mapper;
    public void insertTrafficInfo(TrafficVO vo){
        mapper.insertTrafficInfo(vo);
    }
    public List<TrafficVO> selectTrafficSector(){
        return mapper.selectTrafficSector();
    }
    public List<TrafficVO> selectTrafficGong(){
        return mapper.selectTrafficGong();
    }
}
