package com.chuwa.learn.ParkingLot;

import com.chuwa.learn.ParkingLot.Car;
public class ParkingLot {
    private ParkingSpot[] spots;

    public ParkingLot(int size) {
        spots = new ParkingSpot[size];
        for (int i = 0; i < size; i++) {
            spots[i] = new ParkingSpot();
        }
    }

    public boolean parkCar(Car car) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                spot.parkCar(car);
                return true;
            }
        }
        System.out.println("No available spots.");
        return false;
    }

    public Car retrieveCar(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (!spot.isAvailable() && spot.getCar().getLicensePlate().equals(licensePlate)) {
                return spot.retrieveCar();
            }
        }
        System.out.println("Car not found.");
        return null;
    }

    public void displayParkingLot() {
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (spot.isAvailable()) {
                System.out.println("Spot " + (i + 1) + ": Empty");
            } else {
                System.out.println("Spot " + (i + 1) + ": " + spot.getCar().getLicensePlate());
            }
        }
    }
}
