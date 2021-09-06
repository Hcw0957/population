package com.population.vo;

import lombok.Data;

@Data
public class SmsHistoryVO {
    private Integer seq;        
    private String burstStateNm;         //진행상태            
    private String burstTypeCd;          //돌발유형
    private String content;              //내용
    private String route;                //노선번호
    private String section;              //구간    
    private String upDownType;           //방향
    private String oDate;                //발생날짜
    private String oTime;                //발생시간
}
