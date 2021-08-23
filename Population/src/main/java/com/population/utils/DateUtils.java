package com.population.utils;

import java.util.Calendar;

public class DateUtils {
    public static String makeTodayString(){
        //YYYYMMDD 오늘 날짜를 가져오는 메소드
        Calendar c = Calendar.getInstance();
        String today = c.get(Calendar.YEAR)+leadingZero(c.get(Calendar.MONTH)+1)+leadingZero(c.get(Calendar.DATE));
        return today;
    }
    public static String makeAWeekAgString(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -6);//6일 전 시간으로 만든다.(오늘 날짜에서 6일 뺀 후 내보는 메소드)
        String today = c.get(Calendar.YEAR)+leadingZero(c.get(Calendar.MONTH)+1)+leadingZero(c.get(Calendar.DATE));
        return today;
    }
    public static String leadingZero(int i){
        //파라미터로 전달된 i가 10 보다 작으면 0i 형태로 내보내고,
        //그렇지 않다면 i를 문자열로 변환해서 내보냄
        return i<10 ? "0"+i : ""+i;
    }
}

