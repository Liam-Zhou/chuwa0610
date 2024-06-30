package parkinglot;

public abstract class Vehicle {
    private String licensePlate;

    public Vehicle (String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract VehicleSize getSize();
}
enum VehicleSize {
    SMALL, MEDIUM, LARGE
}