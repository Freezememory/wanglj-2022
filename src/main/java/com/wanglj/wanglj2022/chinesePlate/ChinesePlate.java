package com.wanglj.wanglj2022.chinesePlate;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wanglj
 * @date 2023/6/16 10:55
 * @desc ChinesePlate
 */
@Data
public class ChinesePlate {


    private static String[] allWord = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    private static String[] simpleChinese = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    //private static String[] colors = {"红", "黑"};


    //private String color;

    private String word;

    public static List<ChinesePlate> getChinesePlate() {
        List<ChinesePlate> plate = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<ChinesePlate> complexList = Arrays.stream(allWord).map(item -> {
                ChinesePlate chinesePlate = new ChinesePlate();
                chinesePlate.setWord(item);
                return chinesePlate;
            }).collect(Collectors.toList());
            plate.addAll(complexList);
        }
        return plate;
    }

    public static void main(String[] args) {
        List<ChinesePlate> chinesePlate = getChinesePlate();
        System.out.println(chinesePlate.size());
        System.out.println(chinesePlate);

        Collections.shuffle(chinesePlate);
        System.out.println(chinesePlate);
    }


    //public void fapai() {
    //    //发给三个玩家
    //    for (int i = 0; i < allPokers.size(); i++) {
    //        //最后三张留给地主牌
    //        if (i >= 51) {
    //            lordPokers.add(allPokers.get(i));
    //        } else {
    //            //依次分发给三个玩家
    //            if (i % 3 == 0)
    //                players.get(0).getPokers().add(allPokers.get(i));
    //            else if (i % 3 == 1)
    //                players.get(1).getPokers().add(allPokers.get(i));
    //            else
    //                players.get(2).getPokers().add(allPokers.get(i));
    //        }
    //    }
    //
    //    for (int i = 0; i < players.size(); i++) {
    //        try {
    //            DataOutputStream dataOutputStream = new DataOutputStream(players.get(i).getSocket().getOutputStream());
    //
    //            String jsonString = JSON.toJSONString(players);
    //
    //            dataOutputStream.writeUTF(jsonString);
    //
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //
    //    }
    //
    //}

}
