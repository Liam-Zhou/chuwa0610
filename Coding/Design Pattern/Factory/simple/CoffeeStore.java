package com.chuwa.learn.Factory.simple;

import com.chuwa.learn.Factory.Factory.CoffeeFactory;

public class CoffeeStore {
    public Coffee orderCoffee(String type) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Coffee coffee = simpleFactory.createCoffee(type);

        return coffee;
    }


}
