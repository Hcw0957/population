package com.population.service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.population.mapper.PopulationInfoMapper;
import com.population.vo.CongestInfoVO;
import com.population.vo.PopulationInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulationService {
    @Autowired
    PopulationInfoMapper mapper;
    public void insertPopulationInfo(PopulationInfoVO vo) {
        mapper.insertPopulationInfo(vo);
    }
    public List<PopulationInfoVO> selectTotleTrafficAmount(){
        return mapper.selectTotleTrafficAmount();
    }
    public void insertCongestInfo(CongestInfoVO vo) {
        mapper.insertCongestInfo(vo);
    }
    public List<CongestInfoVO> selectCongestInfoByDate() {
        return mapper.selectCongestInfoByDate();
    }
        }

