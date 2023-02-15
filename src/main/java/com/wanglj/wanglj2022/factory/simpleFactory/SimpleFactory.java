package com.wanglj.wanglj2022.factory.simpleFactory;

import com.wanglj.wanglj2022.factory.Americano;
import com.wanglj.wanglj2022.factory.Cappuccino;
import com.wanglj.wanglj2022.factory.Coffee;
import com.wanglj.wanglj2022.factory.Latte;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:32
 * @Description :
 */
public class SimpleFactory {


    public static Coffee createInstance(String type) {

        if ("Americano".equals(type)) {
            return new Americano();
        } else if ("Cappuccino".equals(type)) {
            return new Cappuccino();
        } else if ("Latte".equals(type)) {
            return new Latte();
        } else {
            throw new RuntimeException("无此" + type + "实例对象");
        }
    }

    public static void main(String[] args) {
        Coffee latte = SimpleFactory.createInstance("Latte");
        System.out.println(latte.getName());


        Coffee americano = SimpleFactory.createInstance("Americano");
        System.out.println(americano.getName());
    }
}
