package com.population.vo;

import lombok.Data;

@Data
public class CongestInfoVO {
    private Integer seq;    
    private String conzoneName; //콘존 명
    private String grade;       //소통등급
    private String routeName;   //도로명
    private String routeNo;     //노선번호
    private String shareRatio;  //점유율
    private String speed;       //스피드
    private String stdDate;     //수집일자
    private String stdHour;     //수집시간
    private String timeAvg;     //통행시간
    private String trafficAmount;   //쿄통량

    private String amount;
    private String time;
    private String speedAvg;
}
