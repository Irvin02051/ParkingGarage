
package parkinggarage;


public class ParkingGarage {

    public static void main(String[] args) {
       ParkingLot<String> parkingLot = new ParkingLot<>();

        // Simulating traffic and parking cars
        parkingLot.analyzeTraffic(10);

        // Listing parked cars
        System.out.println("Parked Cars:");
        parkingLot.listCars();

        // Car leaves the parking lot
        parkingLot.leaveParkingLot(3);

        // Listing cars after leaving
        System.out.println("\nParked Cars after leaving:");
        parkingLot.listCars();
    }
    
}
