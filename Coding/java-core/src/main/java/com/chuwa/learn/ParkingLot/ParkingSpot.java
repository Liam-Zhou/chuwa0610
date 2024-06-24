package com.chuwa.learn.ParkingLot;

public class ParkingSpot {
    private Car parkedCar;

    public boolean isAvailable() {
        return parkedCar == null;
    }

    public void parkCar(Car car) {
        if (isAvailable()) {
            this.parkedCar = car;
        } else {
            System.out.println("Spot is already occupied.");
        }
    }

    public Car retrieveCar() {
        Car car = this.parkedCar;
        this.parkedCar = null;
        return car;
    }

    public Car getCar() {
        return this.parkedCar;
    }
}
