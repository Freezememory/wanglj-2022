package com.wanglj.wanglj2022.study.factory.AbstractFactory;

import com.wanglj.wanglj2022.study.factory.Coffee;
import com.wanglj.wanglj2022.study.factory.Sodas;
import com.wanglj.wanglj2022.study.factory.Tea;

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
