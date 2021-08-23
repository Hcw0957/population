package com.population.service;

import java.util.List;

import com.population.mapper.ExitMapper;
import com.population.vo.ExitInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExitInfoService {
    @Autowired
    ExitMapper mapper;
    public void insertExitInfo(ExitInfoVO vo){
        mapper.insertExitInfo(vo);
    }
    public List<ExitInfoVO> selectExitByDate(){
        return mapper.selectExitByDate();
    }
}
