package com.population.mapper;

import java.util.List;

import com.population.vo.EmergencyVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmergencyMapper {
    public void insertEmergencyinfo(EmergencyVO vo);
    public List<EmergencyVO> selectEmergencyList(String routeNm);
    public List<EmergencyVO> selectEmer(String frwdIc);
}
