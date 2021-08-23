package com.population.mapper;

import java.util.List;

import com.population.vo.ExitInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExitMapper {
    public void insertExitInfo(ExitInfoVO vo);
    public List<ExitInfoVO> selectExitByDate();
}
