package com.population.mapper;

import java.util.List;

import com.population.vo.SpinRouteListVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpinRouteListMapper {
    public void insertSpinRouteList(SpinRouteListVO vo);
    public List<SpinRouteListVO> selectSpinRouteListbyData(String list);
    public List<String> selectRouteAllName();
}
