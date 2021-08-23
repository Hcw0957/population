package com.population.mapper;

import java.util.List;

import com.population.vo.ConstructionInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConstructionInfoMapper {
    public void insertConstructionInfo(ConstructionInfoVO vo);
    public List<ConstructionInfoVO> selectConstructionInfoByDate(String sigong);
}
