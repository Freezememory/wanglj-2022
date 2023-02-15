package com.wanglj.wanglj2022.factory.AbstractFactory;

import com.wanglj.wanglj2022.factory.*;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:44
 * @Description :
 */
public class AmericaDrinksFactory implements AbstractDrinkFactory {

    @Override
    public Coffee createCoffee() {
        return new Americano();
    }

    @Override
    public Tea createTea() {
        return null;
    }

    @Override
    public Sodas createSodas() {
        return new CocaCola();
    }
}
