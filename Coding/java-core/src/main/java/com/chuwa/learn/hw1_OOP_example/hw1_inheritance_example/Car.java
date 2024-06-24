package com.chuwa.learn.hw1_OOP_example.hw1_inheritance_example;

public class Car extends Vehicle {
    private int DoorNumber;

    public int getDoorNumber() {
        return DoorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        DoorNumber = doorNumber;
    }

    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }


}
