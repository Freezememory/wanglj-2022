package com.wanglj.wanglj2022.study.juc;

/**
 * @Author: Wanglj
 * @Date: 2022/8/31 23:31
 * @Description :
 */
public class ThreadDemo  extends Thread{


    @Override
    public void run() {
        for(int i =0;i<1000;i++){
            System.out.println("thread print "+i);
        }
    }

    public static void main(String[] args) {
        ThreadDemo thread = new ThreadDemo();
        thread.start();
    }
}
