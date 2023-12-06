
package parkinggarage;

import java.util.Arrays;
import java.util.Random;


class ParkingLot<T> {
    //Represents the number of floors in the parking lot(which is 3)
    private static final int FLOORS = 3;
    //an array specifying the capacity for each floor
    private static final int[] FLOOR_CAPACITIES = {25, 25, 25};
    //an array to keep track of avaiable parking spots on each floor
    private int[] availableSpots = Arrays.copyOf(FLOOR_CAPACITIES, FLOORS);
    //A 2d array to store the parked cars. The first dimension represents floors, and the second dimension represents individual parking spots on each floor
    private Car<T>[][] cars = new Car[FLOORS][];
    //An array to keep track of the current number of parked cars on each floor
    private int[] currentSize = new int[FLOORS];

    public ParkingLot() {
        for (int i = 0; i < FLOORS; i++) {
            cars[i] = new Car[FLOOR_CAPACITIES[i]];
        }
    }

    public void parkCar(Car<T> car, int duration) {
        int floor;
        switch (duration) {
            case 1:
            case 2:
            case 3:
                floor = 0; // First floor for less than 3 hours
                break;
            case 4:
            case 5:
            case 6:
                floor = 1; // Second floor for less than 6 hours
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                floor = 2; // Third floor for less than 12 hours
                break;
            default:
                System.out.println("Invalid parking duration");
                return;
    }

        if (availableSpots[floor] > 0 && currentSize[floor] < FLOOR_CAPACITIES[floor]) {
            cars[floor][currentSize[floor]++] = car;
            availableSpots[floor]--;
            System.out.println("Car with ticket number " + car.getTicket().getTicketNumber() +
                    " parked on floor " + (floor + 1) + " successfully for " + duration + " hours.");
        } else {
            System.out.println("Floor " + (floor + 1) + " is full. Unable to park the car for " + duration + " hours.");
        }
    }

    public void leaveParkingLot(int ticketNumber) {
        for (int i = 0; i < currentSize.length; i++) {
            for (int j = 0; j < currentSize[i]; j++) {
                if (cars[i][j].getTicket().getTicketNumber() == ticketNumber) {
                    System.out.println("Car with ticket number " + ticketNumber + " left the parking lot.");
                    // Remove the car by shifting elements
                    System.arraycopy(cars[i], j + 1, cars[i], j, currentSize[i] - j - 1);
                    // After shifting the elements, the last element in the array is set to null
                    // which removes the reference to the last car in the array
                    cars[i][--currentSize[i]] = null;
                    availableSpots[i]++;
                    return;
                }
            }
        }
        System.out.println("Car with ticket number " + ticketNumber + " not found in the parking lot.");
    }

    public void listCars() {
        for (int i = 0; i < FLOORS; i++) {
            System.out.println("Floor " + (i + 1) + " - Parked Cars:");
            for (int j = 0; j < currentSize[i]; j++) {
                Car<T> car = cars[i][j];
                System.out.println("Ticket Number: " + car.getTicket().getTicketNumber() + ", Owner: " + car.getOwner());
            }
        }
    }

    // Sorts the array of cars based on their ticket number
    public void sortCarsByTicketNumber() {
        for (int i = 0; i < FLOORS; i++) {
            Arrays.sort(cars[i], 0, currentSize[i], (car1, car2) -> Integer.compare(car1.getTicket().getTicketNumber(), car2.getTicket().getTicketNumber()));
        }
    }

    public void analyzeTraffic(int numCars) {
        Random random = new Random();
        for (int i = 0; i < numCars; i++) {
            Ticket ticket = new Ticket();
            String owner = "Owner" + random.nextInt(100);
            Car<String> car = new Car<>(ticket, owner);
            parkCar((Car<T>) car, random.nextInt(12) + 1); // Random parking duration from 1 to 12 hours
        }
    }
}