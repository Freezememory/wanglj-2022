package com.wanglj.wanglj2022.juc;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @Author: Wanglj
 * @Date: 2022/8/29 23:50
 * @Description :
 */
public class BlockQueue {
    ArrayBlockingQueue<String> arrayBlockingQueue =  new ArrayBlockingQueue<String>(10);

   private static final LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<Runnable>(5);


    PriorityBlockingQueue<String> priorityBlockingQueue =  new PriorityBlockingQueue<String>(10);
    //DelayQueue<String> delayQueue =  new DelayQueue<String>(10);
    //SynchronousQueue<String> blockingQueue =  new SynchronousQueue<String>(10);
    //LinkedTransferQueue<String> linkedTransferQueue =  new LinkedTransferQueue<String>(10);
    //SynchronousQueue<String> synchronousQueue =  new LinkedTransferQueue<String>(10);
    LinkedBlockingDeque<String> linkedBlockingDeque =  new LinkedBlockingDeque<String>(10);
}
