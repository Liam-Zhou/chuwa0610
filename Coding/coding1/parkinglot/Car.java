package parkinglot;

public class Car extends Vehicle {
    public Car(String LicensePlate) {
        super(licensePlate);
    }
    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}

