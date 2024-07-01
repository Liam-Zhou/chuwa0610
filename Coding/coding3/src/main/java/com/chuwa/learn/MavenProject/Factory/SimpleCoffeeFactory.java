package com.chuwa.learn.MavenProject.Factory;

public class SimpleCoffeeFactory {
    public Coffee createCoffee(String type) {
        Coffee coffee= null;
        if(type.equals("american")){
            coffee = new AmericanCoffee();
        }else if(type.equals("latte")){
            coffee = new LatteCoffee();
        }else{
            throw new RuntimeException("Unknown coffee type");
        }

        return coffee;

    }
}