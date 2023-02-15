package com.wanglj.wanglj2022.factory.AbstractFactory;

import com.wanglj.wanglj2022.factory.*;
import com.wanglj.wanglj2022.factory.AbstractFactory.AbstractDrinkFactory;

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
