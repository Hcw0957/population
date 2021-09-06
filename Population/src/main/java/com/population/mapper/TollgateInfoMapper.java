package com.population.mapper;

import java.util.List;

import com.population.vo.TollgateInfoVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface TollgateInfoMapper {
    public void insertTollgateInfo(TollgateInfoVO vo);
    public List<TollgateInfoVO> selectTollgateInfoByDate();
    public List<TollgateInfoVO> selectIcNames(String route);
    public List<TollgateInfoVO> selectRouteNames();
    public List<TollgateInfoVO> selectRouteAndIC(String routeName, String icName);

}
