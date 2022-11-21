package com.wanglj.wanglj2022.juc;

/**
 * @Author: Wanglj
 * @Date: 2022/8/31 23:34
 * @Description :
 */
public class RunnableDemo implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("runnable "+ i);
        }
    }


}
