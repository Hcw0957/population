package com.population.service;

import java.util.List;

import com.population.mapper.SpinRouteListMapper;
import com.population.vo.SpinRouteListVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpinRouteListService {
    @Autowired
    SpinRouteListMapper mapper;
    public void insertSpinRouteList(SpinRouteListVO vo){
        mapper.insertSpinRouteList(vo);
    }
    public List<SpinRouteListVO> selectSpinRouteListbyData(String list){
        return mapper.selectSpinRouteListbyData(list);
    }


    public List<String> selectRouteAllName(){
        return mapper.selectRouteAllName();
    }
    
}
