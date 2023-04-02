package com.wanglj.wanglj2022.study.java8;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author: Wanglj
 * @Date: 2022/12/29 15:52
 * @Description :
 */
public class TimeDemo {


    public static void main(String[] args) {
        System.out.println(LocalDateTime.parse("2021-01-26T12:12:22"));
        LocalDateTime dateTime2 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        //System.out.println(dateTime2);

        //System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        //计算相差天数，切记不要使用Period.between()[因为跨月的话天数会重置，还要自己去计算月份，甚至年份]   要用 ChronoUnit.DAYS.between
        LocalDate date = LocalDate.of(2020,06,05);
        //当天日期
        LocalDate nowDate = LocalDate.now();
        //计算2020-06-05 距离今天相差多少天
        long between = ChronoUnit.DAYS.between(date, nowDate);
        System.out.println(between);

        Period period = Period.between(date, nowDate);

        System.out.println("date1 到 date2 相隔："
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "天");

        LocalDate lastMondayOf2021 = LocalDate.parse("2022-12-01").with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
        System.out.println(lastMondayOf2021);


        //newFormat();
    }

    /**
     * java  8
     */
    public static void newFormat(){
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTime.format(dateTimeFormatter);
        System.out.println(String.format("dateTime format : %s", dateTimeStr));


        //LocalDate.now().


        //=============================


        LocalDate date2 = LocalDate.of(2021, 1, 26);
        LocalDate.parse("2021-01-26");

        LocalDateTime dateTime2 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        LocalDateTime.parse("2021-01-26 12:12:22");

        LocalTime time2 = LocalTime.of(12, 12, 22);
        LocalTime.parse("12:12:22");

    }










    public void pushWeek(){
        //一周后的日期
        LocalDate localDate = LocalDate.now();
        //方法1
        LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
        //方法2
        LocalDate after2 = localDate.plusWeeks(1);
        System.out.println("一周后日期：" + after);

        //算两个日期间隔多少天，计算间隔多少年，多少月
        LocalDate date1 = LocalDate.parse("2021-02-26");
        LocalDate date2 = LocalDate.parse("2021-12-23");
        Period period = Period.between(date1, date2);
        System.out.println("date1 到 date2 相隔："
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "天");
        //打印结果是 “date1 到 date2 相隔：0年9月27天”
        //这里period.getDays()得到的天是抛去年月以外的天数，并不是总天数
        //如果要获取纯粹的总天数应该用下面的方法
        long day = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date1 + "和" + date2 + "相差" + day + "天");
        //打印结果：2021-02-26和2021-12-23相差300天
    }





    public void getDayNew() {
        LocalDate today = LocalDate.now();
        //获取当前月第一天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月最后一天
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        //取下一天：
        LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
        //当年最后一天
        LocalDate lastday = today.with(TemporalAdjusters.lastDayOfYear());
        //2021年最后一个周日，如果用Calendar是不得烦死。
        LocalDate lastMondayOf2021 = LocalDate.parse("2021-12-31").with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
    }

}
