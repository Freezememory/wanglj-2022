package com.wanglj.wanglj2022.study.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
				
      	//System.out.println(queue.offer("aaa"));   //false
        //System.out.println(queue.poll());         //null

        //System.out.println(queue.add("bbb"));      //IllegalStateException: Queue full
      
        new Thread(()->{
            try {
                System.out.println("Thread 1 put a");
                queue.put("a");

                System.out.println("Thread 1 put b");
                queue.put("b");

                System.out.println("Thread 1 put c");
                queue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Thread 2 get:"+queue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("Thread 2 get:"+queue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("Thread 2 get:"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}