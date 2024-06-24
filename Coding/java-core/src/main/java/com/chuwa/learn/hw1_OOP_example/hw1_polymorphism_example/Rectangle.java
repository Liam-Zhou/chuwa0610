package com.chuwa.learn.hw1_OOP_example.hw1_polymorphism_example;

public class Rectangle implements Shape{
    private double len;
    private double wid;

    public Rectangle(double len, double wid) {
        this.len = len;
        this.wid = wid;
    }

    @Override
    public double calculateArea() {
        return len * wid;
    }
}
