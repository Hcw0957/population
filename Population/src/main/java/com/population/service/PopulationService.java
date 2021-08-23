package com.population.service;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.population.mapper.PopulationInfoMapper;
import com.population.vo.CongestInfoVO;
import com.population.vo.ConstructionInfoVO;
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
    public List<PopulationInfoVO> selectPopulationByDate(String date) {

        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 10);
        standard.set(Calendar.MINUTE, 30);
        standard.set(Calendar.SECOND, 00);

        if(now.getTimeInMillis() < standard.getTimeInMillis()) {
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());

        List<PopulationInfoVO> data = mapper.selectPopulationByDate(date);

        return data;
    }
    public void insertCongestInfo(CongestInfoVO vo) {
        mapper.insertCongestInfo(vo);
    }
    public List<CongestInfoVO> selectCongestInfoByDate() {
        return mapper.selectCongestInfoByDate();
    }
        }

