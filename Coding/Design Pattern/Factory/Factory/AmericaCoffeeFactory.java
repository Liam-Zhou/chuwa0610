package com.chuwa.learn.Factory.Factory;

public class AmericaCoffeeFactory implements CoffeeFactory{
    public Coffee createCoffee() {
        return new AmericaCoffee();
    }
}
