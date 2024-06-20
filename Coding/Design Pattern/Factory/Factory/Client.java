package com.chuwa.learn.Factory.Factory;

import com.chuwa.learn.Factory.Factory.AmericaCoffee;
import com.chuwa.learn.Factory.Factory.CoffeStore;

public class Client {
    public static void main(String[] args) {
        CoffeStore store = new CoffeStore();

        CoffeeFactory factory = new AmericaCoffeeFactory();
        store.setFactory(factory);

        Coffee coffee =  store.orderCoffee();
        System.out.println(coffee.getName());


        CoffeeFactory factory1 = new LatteeCoffeeFactory();
        store.setFactory(factory1);

        Coffee coffee1 =  store.orderCoffee();
        System.out.println(coffee1.getName());
    }
}
