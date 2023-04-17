package com.wanglj.wanglj2022.study.designPattern.singleton;

/**
 * @author Wanglj
 * @date 2023/4/10 11:46
 * @desc SingleTon
 */
public class Singleton {

    private Singleton() {}

    /*private static  Singleton instance;
    static {
        instance = new Singleton();
    }*/

   /*private static final Singleton instance = new Singleton();
      public static Singleton getInstance() {
        return instance;
      }*/


    /*private static class SingletonInstance{
        private final static Singleton INSTANCE = new Singleton();
      }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }*/

    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
