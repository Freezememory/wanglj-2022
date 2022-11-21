package com.wanglj.wanglj2022.juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wanglj
 * @Date: 2022/8/31 23:59
 * @Description :
 */
public class SynchronizedDemo {

    static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
    static RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

    static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue, new NameThreadFactory("wanglj线程池"), handler);


    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            AccountSync accountSync = new AccountSync();
            threadPool.execute(accountSync);
        }


        //Thread thread1 = new Thread(accountSync);
        //Thread thread2 = new Thread(accountSync);
        //thread1.start();
        //thread2.start();
    }


    static class AccountSync implements Runnable {

        int i = 0;

        public  void increase() throws InterruptedException {
            i++;
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + "增加了i值，它的值为 " + i);
        }

        @Override
        public void run() {
            for (int j = 0; j < 100; j++) {
                try {
                    increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
