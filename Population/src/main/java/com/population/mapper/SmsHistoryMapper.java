package com.population.mapper;

import com.population.vo.SmsHistoryVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsHistoryMapper {
    public void insertSmsHistoryInfo(SmsHistoryVO vo);
}
