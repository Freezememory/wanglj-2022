package com.wanglj.wanglj2022.ffmpeg;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;

import java.io.File;

/**
 * @author Wanglj
 * @date 2023/6/9 9:49
 * @desc VideoTranscode
 */
public class VideoTranscode {


    private static final String command = "ffmpeg -re -i F:\\ffmpeg-study\\TroubleMaker.mp4 " +
            "-i F:\\ffmpeg-study\\wecaht.png " +
            "-i F:\\ffmpeg-study\\icon.png " +
            "-stream_loop -1 -i F:\\ffmpeg-study\\qingfengxulai.mp3 " +
            "-filter_complex \"[0:v][1:v]overlay=w:(1/8*H)[v1];[v1][2:v]overlay=enable='mod(t,10)':x='W*(1-(mod(t,10)/10))':y='4/5*H'[video]\" " +
            "-shortest -map \"[video]\" " +
            "-map 3:a:0 " +
            "-c:v h264 -c:a aac " +
            "-s 640*480 -crf 20 -fflags nobuffer -max_delay 0 -analyzeduration 1  " +
            "F:\\ffmpeg-study\\new18.mp4";

    private static final String photoToVideo = "ffmpeg -i F:\\ffmpeg-study\\xihuanni.mp3  -framerate 1  -f image2 -loop 1 -i  F:\\myself\\video\\%d.jpg " +
            " -vcodec libx264 -r 25 -shortest -absf aac_adtstoasc -y  F:\\myself\\video\\video7.mp4";

    private static String transcode(String targetFileName) {

        String s = RuntimeUtil.execForStr(command);
        System.out.println(s);

        return "";

    }


    public static void main(String[] args) {
        //transcode("/");
        //System.out.println(command);
        //System.out.println(s);
        renameFile("F:\\myself\\video");
        //String s = RuntimeUtil.execForStr(photoToVideo);

    }


    private static void renameFile(String filePath) {
        File file = new File(filePath);
        String[] list = file.list();
        for (int i = 0; i < list.length; i++) {
            File photoFile = new File(file, list[i]);
            photoFile.renameTo(new File(file, i + ".jpg"));
        }
    }


}
