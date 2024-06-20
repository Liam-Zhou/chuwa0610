package com.chuwa.learn.Factory.AbstractFactory;

public class ItalyDessertFactory implements DesertFactory{

    @Override
    public Coffee createCoffee() {
        return new LatteeCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Trimisu();
    }
}
