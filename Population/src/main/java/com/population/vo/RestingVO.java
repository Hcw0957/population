package com.population.vo;

import lombok.Data;

@Data
public class RestingVO {
    private Integer seq;
    private String batchMenu; //대표음식
    private String brand;     //브랜드
    private String convenience; //편의시설
    private String direction;   //방행
    private String routeName;   //노선명
    private String serviceAreaName; //휴게소.주유소 명
    private String svarAddr;    //주소
    private String telNo;   //전화번호
}
