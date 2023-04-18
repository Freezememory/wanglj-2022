package com.wanglj.wanglj2022.study.factory.FactoryMethod;

import com.wanglj.wanglj2022.study.factory.Coffee;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:37
 * @Description :
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        ChinaCoffeeFactory chinaCoffeeFactory = new ChinaCoffeeFactory();

        Coffee[] chinaCoffees = chinaCoffeeFactory.createCoffee();
        System.out.println("中国工厂生产的咖啡种类：   ");
        print(chinaCoffees);

        AmericaCoffeeFactory americaCoffeeFactory = new AmericaCoffeeFactory();

        Coffee[] americaCoffees = americaCoffeeFactory.createCoffee();
        System.out.println("美国工厂生产的咖啡种类：   ");
        print(americaCoffees);
    }

/*    static void print(Coffee[] c){
        for (Coffee coffee : c) {
            System.out.println(coffee.getName());
        }
    }*/

}
