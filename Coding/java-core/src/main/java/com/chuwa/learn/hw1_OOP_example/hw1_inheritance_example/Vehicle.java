package com.chuwa.learn.hw1_OOP_example.hw1_inheritance_example;

import java.util.Objects;

public class Vehicle {

    private String make;
    private String model;
    private int year;


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void startEngine() {
        System.out.println("Engine started.");
    }

    // Overloaded method to start the engine with a key type
    public String startEngine(String keyType) {
        if (Objects.equals(keyType, "abc")) {
            return "Engine started with " + keyType + " key.";
        } else {
            return "Invalid key type.";
        }
    }



}
