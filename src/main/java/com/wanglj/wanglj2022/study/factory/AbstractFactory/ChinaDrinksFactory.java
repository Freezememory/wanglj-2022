package com.wanglj.wanglj2022.study.factory.AbstractFactory;

import com.wanglj.wanglj2022.study.factory.*;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:44
 * @Description :
 */
public class ChinaDrinksFactory  implements AbstractDrinkFactory {

    @Override
    public Coffee createCoffee() {
        return new Latte();
    }

    @Override
    public Tea createTea() {
        return new MilkTea();
    }

    @Override
    public Sodas createSodas() {
        return null;
    }
}
