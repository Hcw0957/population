package com.population.service;

import java.util.List;

import com.population.mapper.TollgateInfoMapper;
import com.population.vo.TollgateInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TollgateInfoService {
    @Autowired
    TollgateInfoMapper mapper;
    public void insertTollgateInfo(TollgateInfoVO vo){
        mapper.insertTollgateInfo(vo);
    }
    public List<TollgateInfoVO> selectTollgateInfoByDate(){
        return mapper.selectTollgateInfoByDate();
    }
    public List<TollgateInfoVO> selectIcNames(String route){
        return mapper.selectIcNames(route);
    }
    public List<TollgateInfoVO> selectRouteNames(){
        return mapper.selectRouteNames();
    }
    public List<TollgateInfoVO> selectRouteAndIC(String routeName, String icName){
        return mapper.selectRouteAndIC(routeName, icName);
    }
}
