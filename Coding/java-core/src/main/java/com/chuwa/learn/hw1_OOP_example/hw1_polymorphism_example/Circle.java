package com.chuwa.learn.hw1_OOP_example.hw1_polymorphism_example;

public class Circle implements Shape {

    private double radius;

    //create a constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
