package com.population.mapper;

import java.util.List;

import com.population.vo.TrafficVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrafficMapper {
    public void insertTrafficInfo(TrafficVO vo);
    public List<TrafficVO> selectTrafficSector();
    public List<TrafficVO> selectTrafficGong();
}
