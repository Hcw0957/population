package com.population.vo;

import lombok.Data;

@Data
public class SpinRouteListVO {
private Integer seq;
private String edpntDstnc;       //종점이정
private String routeNm;          //노선이름
private String routeNo;          //노선번호
private String stptDstnc;        //기점이정
private String totExtnsDstne;    //총 연장거리
private String useYn;            //사용여부
}
