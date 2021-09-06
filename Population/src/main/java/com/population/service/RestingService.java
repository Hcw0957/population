package com.population.service;

import java.util.List;

import com.population.mapper.RestingMapper;
import com.population.vo.RestingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestingService {
    @Autowired
    RestingMapper mapper;
    public void insertRestingInfo(RestingVO vo){
        mapper.insertRestingInfo(vo);
    }
    public List<RestingVO> selectRestingList(){
        return mapper.selectRestingList();
    }
    public List<RestingVO> selectRestingPlace(String region){
        return mapper.selectRestingPlace(region);
    }
}
