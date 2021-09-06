package com.population.service;

import com.population.mapper.SmsHistoryMapper;
import com.population.vo.SmsHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsHistoryService {
    @Autowired
    SmsHistoryMapper mapper;
    public void insertSmsHistoryInfo(SmsHistoryVO vo){
        mapper.insertSmsHistoryInfo(vo);
    }
}
