package com.chuwa.learn.Factory.AbstractFactory;

public class AmericanDessertFactory implements DesertFactory {
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new MachaMousse();
    }
}
