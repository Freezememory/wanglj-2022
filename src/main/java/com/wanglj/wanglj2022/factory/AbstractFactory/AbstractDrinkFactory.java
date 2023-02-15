package com.wanglj.wanglj2022.factory.AbstractFactory;

import com.wanglj.wanglj2022.factory.Coffee;
import com.wanglj.wanglj2022.factory.Sodas;
import com.wanglj.wanglj2022.factory.Tea;

/**
 * @Author: Wanglj
 * @Date: 2022/12/30 15:42
 * @Description :
 */
public interface AbstractDrinkFactory {

    Coffee createCoffee();
    Tea createTea();
    Sodas createSodas();

}
