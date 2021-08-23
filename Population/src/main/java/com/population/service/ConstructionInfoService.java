package com.population.service;

import java.util.List;

import com.population.mapper.ConstructionInfoMapper;
import com.population.vo.ConstructionInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionInfoService {
    @Autowired // 이거 빠짐
    ConstructionInfoMapper mapper;
    public void insertConstructionInfo(ConstructionInfoVO vo){
        mapper.insertConstructionInfo(vo);
    }
        public List<ConstructionInfoVO> selectConstructionInfoByDate(String sigong) {
            return mapper.selectConstructionInfoByDate(sigong);
        }
}
