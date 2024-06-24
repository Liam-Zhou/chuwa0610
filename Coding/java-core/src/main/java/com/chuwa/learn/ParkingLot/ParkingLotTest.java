package com.chuwa.learn.ParkingLot;
import java.util.Scanner;

public class ParkingLotTest {

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5); // A parking lot with 5 spots

        Car car1 = new Car("ABC123");
        Car car2 = new Car("XYZ789");

        parkingLot.parkCar(car1);
        System.out.println("parking car: number:" + car1.getLicensePlate());
        parkingLot.parkCar(car2);
        System.out.println("parking car: number:" + car2.getLicensePlate());

        System.out.println("\nParkingLot Overview:");
        parkingLot.displayParkingLot();

        System.out.println("\n");
        parkingLot.retrieveCar("ABC123");
        System.out.println("success exit: car num:" + car1.getLicensePlate() );


        System.out.println("\nParkingLot Overview:");
        parkingLot.displayParkingLot();
    }
}


