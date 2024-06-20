package com.chuwa.learn.Factory.simple;

public class SimpleFactory {
    public Coffee createCoffee(String type) {
        if (type.equals("america"))
            return new AmericaCoffee();
        else if (type.equals("latte"))
            return new LatteCoffee();
        else
            throw new RuntimeException("no such coffee");
    }
}
