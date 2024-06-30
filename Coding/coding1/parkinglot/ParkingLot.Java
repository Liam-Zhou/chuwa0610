package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> spots;

    public ParkingLot(int numSpots) {
        spots = new ArrayList<>():
        for (int i = 0; i < numSpots; i++) {
            spots.add(new ParkingSpot(vehicleSize.MEDIUM));
        }
    }
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if(spot.parkVehicle(vehicle)) {
                return ture;
            }
        }
        return false;
    }
    public void removeVehichle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getCurrentVehicle() != null && spot.getCurrentVehicle().equals(vehicle)) {
                spot.removeVehicle();
                break;
            }
        }
    }

}