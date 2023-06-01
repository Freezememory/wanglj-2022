package com.wanglj.wanglj2022.study.algorithm;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DayCycle {


    private static final int[] videoDurationList = new int[]{60 * 100, 80 * 9, 20 * 60, 54 * 40, 60 * 45};
    //private static final int[] videoDurationList = new int[]{60 * 60};
    private static final String format = "第{}次循环，播放视频{},触发时间:{}";

    public static void main(String[] args) {

    }


    public static void cycle3() {
        LocalDateTime now = LocalDateTime.now();
        int currentDaySecond = (now.getHour() * 60 * 60) + (now.getMinute() * 60) + now.getSecond();
        log.info("当天当前秒: {}", currentDaySecond);
        int dayDuration = (24 * 60 * 60) - currentDaySecond;
        log.info("当天剩余时长: {}", dayDuration);
        int cycleTime = 0;//循环次数
        int length = videoDurationList.length;//循环视频个数
        int useDuration = 0;//所算入的时间

        List<String> task = new ArrayList<>();
        while (dayDuration > useDuration) {
            for (int i = 0; i < length; i++) {
                task.add(StrUtil.format(format, cycleTime / length + 1, i, useDuration));
                useDuration = useDuration + videoDurationList[i];
                //surplusDuration = dayDuration - useDuration;//剩余时间
                if (dayDuration < useDuration) {
                    break;
                }
                cycleTime += 1;
            }
        }
        task.forEach(System.out::println);
    }


    public static void cycle2() {
        LocalDateTime now = LocalDateTime.now();
        int currentDaySecond = (now.getHour() * 60 * 60) + (now.getMinute() * 60) + now.getSecond();
        log.info("当天当前秒: {}", currentDaySecond);
        int dayDuration = (24 * 60 * 60) - currentDaySecond;
        log.info("当天剩余时长: {}", dayDuration);
        int sumDuration = Arrays.stream(videoDurationList).sum();
        log.info("所有循环视频总长: {}", sumDuration);
        int cycleTime = 0;//循环次数

        int useDuration = 0;//所算入的时间

        int surplusDuration = dayDuration - useDuration;
        List<String> task = new ArrayList<>();
        while (surplusDuration > 0) {
            for (int i = 0; i < videoDurationList.length; i++) {
                if (i == 0) {
                    cycleTime += 1;
                    if (cycleTime > 1) {
                        useDuration = useDuration + videoDurationList[videoDurationList.length - 1];//上一次循环的最后一次时长
                    }
                } else {
                    useDuration = useDuration + videoDurationList[i - 1];//上一个视频时长
                    if (surplusDuration < videoDurationList[i - 1]) {
                        surplusDuration = -1;//退出标记
                        break;
                    }
                }
                task.add(StrUtil.format(format, cycleTime, i, useDuration));
                surplusDuration = dayDuration - useDuration;//剩余时间
            }
        }
        task.forEach(System.out::println);
    }

    public static void cycle1() {
        LocalDateTime now = LocalDateTime.now();
        int currentDaySecond = (now.getHour() * 60 * 60) + (now.getMinute() * 60) + now.getSecond();
        log.info("当天当前秒: {}", currentDaySecond);
        int dayDuration =( 24 * 60 * 60) -currentDaySecond ;
        //int dayDuration = (24 * 60 * 60);
        log.info("当天剩余时长: {}", dayDuration);
        int sumDuration = Arrays.stream(videoDurationList).sum();
        log.info("视频总长: {}", sumDuration);
        int cycleTime = dayDuration / sumDuration;
        log.info("循环次数: {}", cycleTime);

        List<String> task = new ArrayList<>();
        int useDuration = 0;
        for (int i = 0; i < cycleTime; i++) {
            for (int i1 = 0; i1 < videoDurationList.length; i1++) {
                if (i1 == 0) {
                    if (useDuration > 0) {
                        useDuration = useDuration + videoDurationList[videoDurationList.length - 1];
                    }
                } else {
                    useDuration = useDuration + videoDurationList[i1 - 1];
                }
                task.add(StrUtil.format(format, i + 1, i1, useDuration));
            }
        }

        int surplusDuration = dayDuration - (sumDuration * cycleTime);
        if (surplusDuration > 0) {
            for (int i = 0; i < videoDurationList.length; i++) {
                if (i == 0) {
                    if (useDuration > 0) {
                        useDuration = useDuration + videoDurationList[videoDurationList.length - 1];
                    }
                } else {
                    if (surplusDuration - videoDurationList[i - 1] > 0) {
                        surplusDuration = surplusDuration - videoDurationList[i - 1];
                        useDuration = useDuration + videoDurationList[i - 1];
                    } else {
                        break;
                    }
                }
                task.add(StrUtil.format(format, cycleTime + 1, i, useDuration));
            }
        }
        task.forEach(System.out::println);
    }
}
