package com.wanglj.wanglj2022.factory.AbstractFactory;

import com.wanglj.wanglj2022.factory.*;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:44
 * @Description :
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {


        ChinaDrinksFactory chinaDrinksFactory = new ChinaDrinksFactory();

        Coffee coffee = chinaDrinksFactory.createCoffee();
        Tea tea = chinaDrinksFactory.createTea();
        Sodas sodas = chinaDrinksFactory.createSodas();
        System.out.println("中国饮料工厂生产的产品种类：   ");
        print(coffee);
        print(tea);
        print(sodas);


        AmericaDrinksFactory americaDrinksFactory = new AmericaDrinksFactory();

        Coffee aCoffee = americaDrinksFactory.createCoffee();
        Tea aTea = americaDrinksFactory.createTea();
        Sodas aSodas = americaDrinksFactory.createSodas();
        System.out.println("美国饮料工厂生产的产品种类：   ");
        print(aCoffee);
        print(aTea);
        print(aSodas);

    }



    static void print(Drink drink ){

        if(drink ==null){
            System.out.println("产品：xxxx");
        }else {
            System.out.println("产品：===== "+drink.getName());
        }
    }
}
