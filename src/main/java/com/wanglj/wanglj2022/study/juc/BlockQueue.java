package com.wanglj.wanglj2022.study.juc;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @Author: Wanglj
 * @Date: 2022/8/29 23:50
 * @Description :
 */
public class BlockQueue {
    private static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);

    private static final LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<Runnable>(5);

    PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<String>(10);
    //DelayQueue<String> delayQueue =  new DelayQueue<String>(10);
    //SynchronousQueue<String> blockingQueue =  new SynchronousQueue<String>(10);
    //LinkedTransferQueue<String> linkedTransferQueue =  new LinkedTransferQueue<String>(10);
    //SynchronousQueue<String> synchronousQueue =  new LinkedTransferQueue<String>(10);
    LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<String>(10);

    public static void main(String[] args) throws InterruptedException {
        arrayBlockingQueue.put("w");
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("n");
        //arrayBlockingQueue.put("g");
        //System.out.println(arrayBlockingQueue.offer("a"));
        //System.out.println(arrayBlockingQueue.offer("n"));
        //System.out.println(arrayBlockingQueue.offer("g"));
        //arrayBlockingQueue.add("g");
        //System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.take());
        //System.out.println(arrayBlockingQueue.take());
        //System.out.println(arrayBlockingQueue.remove());
    }

}
