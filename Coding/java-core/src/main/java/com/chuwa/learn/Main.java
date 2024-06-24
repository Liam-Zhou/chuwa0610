package com.chuwa.learn;

import com.chuwa.learn.hw1_OOP_example.hw1_eceapsulation_example.BankAccount;
import com.chuwa.learn.hw1_OOP_example.hw1_inheritance_example.Car;
import com.chuwa.learn.hw1_OOP_example.hw1_polymorphism_example.Shape;
import com.chuwa.learn.hw1_OOP_example.hw1_polymorphism_example.Circle;
import com.chuwa.learn.hw1_OOP_example.hw1_polymorphism_example.Rectangle;

public class Main {
    public static void main(String[] args) {
    // testing for the OOP_example

            // encapsulation testing
        BankAccount howard_account = new BankAccount();
        howard_account.setAccountNumber("123-456-789-789");
        howard_account.setBalance(1000);

        //test deposit
        howard_account.deposit(25.54);
        System.out.println("New balance: " + howard_account.getBalance());

        //test withdraw
        howard_account.withdraw(20);
        System.out.println("Balance after attempting overdraft: " + howard_account.getBalance());

        System.out.println("\n");
        System.out.println("\n");


        // inheritance testing
        Car car = new Car();
        car.setMake("Tesla");
        car.setModel("Model3");
        car.setYear(2024);
        car.setDoorNumber(4);
        car.startEngine();
        System.out.println(car.startEngine("abc"));
        System.out.println(car.startEngine("xyz"));


        System.out.println("\n");
        System.out.println("\n");


        //polymorphism testing
        Shape circle = new Circle(5);
        System.out.println("Circle Area: " + circle.calculateArea());

        Shape rectangle = new Rectangle(4, 6);
        System.out.println("Rectangle Area: " + rectangle.calculateArea());






    }
}