public class ParkingLot {
    private final ParkingSpot[] spots;

    public ParkingLot(int capacity) {
        spots = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            spots[i] = new ParkingSpot();
        }
    }

    public boolean park(int spotNumber) {
        if (spotNumber < 0 || spotNumber >= spots.length) {
            System.out.println("Invalid spot number.");
            return false;
        }
        ParkingSpot spot = spots[spotNumber];
        if (spot.isOccupied()) {
            System.out.println("Spot already occupied.");
            return false;
        }
        spot.park();
        System.out.println("Parked at spot " + spotNumber);
        return true;
    }

    public boolean leave(int spotNumber) {
        if (spotNumber < 0 || spotNumber >= spots.length) {
            System.out.println("Invalid spot number.");
            return false;
        }
        ParkingSpot spot = spots[spotNumber];
        if (!spot.isOccupied()) {
            System.out.println("Spot is already empty.");
            return false;
        }
        spot.leave();
        System.out.println("Left spot " + spotNumber);
        return true;
    }

    public void printStatus() {
        for (int i = 0; i < spots.length; i++) {
            System.out.println("Spot " + i + " is " + (spots[i].isOccupied() ? "occupied" : "empty"));
        }
    }
}
class ParkingSpot {
    private boolean occupied;

    public ParkingSpot() {
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void park() {
        occupied = true;
    }

    public void leave() {
        occupied = false;
    }
}
class Main{
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.park(0);
        parkingLot.park(1);
        parkingLot.leave(0);
        parkingLot.printStatus();
    }
}