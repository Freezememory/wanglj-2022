package com.wanglj.wanglj2022.study.juc;

/**
 * @Author: Wanglj
 * @Date: 2022/8/31 23:35
 * @Description :
 */
public class TestMain  /*implements FunctionDemo*/{

/*    @Override
    public String buildMessage() {
        return "123";
    }*/

    private static void log(FunctionDemo demo){
        int a = 1;
        int ba = 3;
        System.out.println(demo.buildMessage(a,ba));
    }
    public static void main(String[] args) {
        //RunnableDemo runnableDemo = new RunnableDemo();
        //Thread thread = new Thread(runnableDemo);
        //thread.start();

        /*ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();*/

        /*Thread thread1 = new Thread(() ->        {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });

        thread1.start();*/

        log((a,b)-> String.valueOf(a+b));
    }
}
