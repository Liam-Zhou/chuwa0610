package com.chuwa.learn.Factory.Factory;

public class LatteeCoffeeFactory implements CoffeeFactory{
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
