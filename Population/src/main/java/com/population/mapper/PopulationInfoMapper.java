package com.population.mapper;

import java.util.List;

import com.population.vo.CongestInfoVO;
import com.population.vo.PopulationInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PopulationInfoMapper {
    public void insertPopulationInfo(PopulationInfoVO vo);
    public List<PopulationInfoVO> selectTotleTrafficAmount();

    public void insertCongestInfo(CongestInfoVO vo);
    public List<CongestInfoVO> selectCongestInfoByDate();
}
