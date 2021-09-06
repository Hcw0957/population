package com.population.mapper;

import java.util.List;

import com.population.vo.TimeSmsVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeSmsMapper {
    public void insertTimeSmsInfo(TimeSmsVO vo);
    public List<TimeSmsVO> selectTimeSmsInfoByDate(String start);
    public List<TimeSmsVO> selectTimeSmsInfoList(String roadNM);
}
