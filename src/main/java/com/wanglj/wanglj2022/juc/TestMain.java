package com.wanglj.wanglj2022.juc;

/**
 * @Author: Wanglj
 * @Date: 2022/8/31 23:35
 * @Description :
 */
public class TestMain {

    public static void main(String[] args) {
        //RunnableDemo runnableDemo = new RunnableDemo();
        //Thread thread = new Thread(runnableDemo);
        //thread.start();

        /*ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();*/

        Thread thread1 = new Thread(() ->        {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });

        thread1.start();
    }
}
