package com.wanglj.wanglj2022.factory.FactoryMethod;

import com.wanglj.wanglj2022.factory.Cappuccino;
import com.wanglj.wanglj2022.factory.Coffee;
import com.wanglj.wanglj2022.factory.simpleFactory.CoffeeFactory;
import com.wanglj.wanglj2022.factory.Latte;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:36
 * @Description :
 */
public class ChinaCoffeeFactory  extends CoffeeFactory {

    @Override
    public Coffee[] createCoffee() {
        return new Coffee[]{new Cappuccino(),new Latte()};
    }
}
