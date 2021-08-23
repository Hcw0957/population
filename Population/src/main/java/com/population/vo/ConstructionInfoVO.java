package com.population.vo;

import lombok.Data;

@Data
public class ConstructionInfoVO {
    private String cmcnCstrClss;    //준공시공구분
    private String cnstnStpntAddr;  //공사시점주소
    private String cnstnEnpntAddr;  //공사종점주소
    private String cnstnTerm;       //공사기간
    private String cmcnDate;        //준공날짜
    private String routeName;       //노선명
    private String sectionName;     //구간명
}
