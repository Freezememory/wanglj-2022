package com.wanglj.wanglj2022.study.juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wanglj
 * @Date: 2022/8/29 23:11
 * @Description :
 */
public class ThreadPoll1 {
    private static int a = 1;

    public static class ThreadPoolTest implements Runnable {

        private final int threadNum;

        public ThreadPoolTest(int threadNum) {
            this.threadNum = threadNum;
        }

        @Override
        public void run() {
            try {
                ++a;
                System.out.println(Thread.currentThread().getName());
                System.out.println("a ==" + a);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue, handler);


        ThreadPoolExecutor nameThreadPool =
                new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue,
                        new NameThreadFactory("wanglj线程池"), handler);

        //threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            nameThreadPool.execute(
                    new Thread(new ThreadPoolTest(i), "Thread".concat(i + "")));
            //System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0) {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }


}
