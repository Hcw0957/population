package com.population.vo;

import lombok.Data;

@Data
public class EmergencyVO {
    private Integer seq;
    private String bgwdIc;//후방IC
    private String drveDrctNm; //방향
    private String frwdIc;    //전방IC
    private String routeNm;   //노선
    private String shift;    //이정(km)
    private String bypsRoad;    //우회도로
}