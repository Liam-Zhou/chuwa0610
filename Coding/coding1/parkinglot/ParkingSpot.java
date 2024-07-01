package parkinglot;

public class ParkingSpot {
    private VehicleSize size;
    private Vehicle currentVehicle;

    public ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (currentVehicle == null && vehicle.getSize() == size) {
            currentVehicle = vehicle;
            return true;
        }
        return false;
    }

    public void removeVehicle() {
        currentVehicle = null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public VehicleSize getSize() {
        return size;
    }
}