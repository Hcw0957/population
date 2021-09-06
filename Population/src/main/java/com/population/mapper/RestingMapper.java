package com.population.mapper;

import java.util.List;

import com.population.vo.RestingVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestingMapper {
    public void insertRestingInfo(RestingVO vo);
    public List<RestingVO> selectRestingList();
    public List<RestingVO> selectRestingPlace(String region);
}
