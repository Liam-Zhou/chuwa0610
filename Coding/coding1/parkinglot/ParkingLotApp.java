package parkinglot;

public class ParkingLotApp {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle car1 = new Car("ABC123");
        Vehicle car2 = bew Car("XYZ789");

        System.out.println("Parking car1: " + ParkingLot.parkVehicle(car1));
        System.out.println("Parking car2: " + ParkingLot.parkVehicle(car2));

        parkingLot.removeVehichle(car1);
        System.out.println("removed car1");

        System.out.println("Parking car1 again: " + parkingLot.parkVehicle(car1));
    }

}