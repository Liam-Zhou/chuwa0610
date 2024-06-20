package com.chuwa.learn.Factory.AbstractFactory;


import com.chuwa.learn.Factory.AbstractFactory.AmericanDessertFactory;

public class Client {
    public static void main(String[] args) {
        AmericanDessertFactory americaCoffeeFactory = new AmericanDessertFactory();
        Coffee coffee = americaCoffeeFactory.createCoffee();
        Dessert dessert = americaCoffeeFactory.createDessert();

        System.out.println(coffee.getName());
        dessert.show();
        ItalyDessertFactory italyDessertFactory = new ItalyDessertFactory();

        Coffee coffee1 = italyDessertFactory.createCoffee();
        Dessert dessert1 = italyDessertFactory.createDessert();
        System.out.println(coffee1.getName());
        dessert1.show();
    }
}
