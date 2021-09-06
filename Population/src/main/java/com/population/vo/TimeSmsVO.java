package com.population.vo;

import lombok.Data;

@Data
public class TimeSmsVO {
    private String seq;
    private String accDate;
    private String accHour;
    private String accType;
    private String roadNM;
    private String smsText;
    private String startEndTypeCode;
}
