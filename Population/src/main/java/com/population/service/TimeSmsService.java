package com.population.service;

import java.util.List;

import com.population.mapper.TimeSmsMapper;
import com.population.vo.TimeSmsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSmsService {
    @Autowired
    TimeSmsMapper mapper;
    public void insertTimeSmsInfo(TimeSmsVO vo){
        mapper.insertTimeSmsInfo(vo);
    }
    public List<TimeSmsVO> selectTimeSmsInfoByDate(String start){
        return mapper.selectTimeSmsInfoByDate(start);
    }
    public List<TimeSmsVO> selectTimeSmsInfoList(String roadNM){
        return mapper.selectTimeSmsInfoList(roadNM);
    }
}
