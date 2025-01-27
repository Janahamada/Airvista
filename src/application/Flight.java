package application;
import java.util.ArrayList;

public class Flight {

    private String flightId;
    private String flightName;
    private String flightSchedule;
    private String destination;
    private int passengersCount;
    private ArrayList<Passenger> passengers = new ArrayList<>(); // List of passengers on the flight

    // Constructor
    public Flight(String flightId, String flightName, String destination, String flightSchedule) { //done
        this.flightId = flightId;
        this.flightName = flightName;
        this.destination = destination;
        this.flightSchedule = flightSchedule;
        //DBController.storeFlightInfo(this);

    }

    // Add passenger to the flight
    public void addPassenger(Passenger passenger) { //done
        if (isPassengerAlreadyOnFlight(passenger)) {
            System.out.println("Passenger " + passenger.getPassengerID() + " is already on flight " + flightId);
        }

        passengers.add(passenger);
        passenger.setBookingStatus(true);
        incrementPassengerCount();
        DBController.changeBookingStatus(passenger.getPassengerID());

        System.out.println("Passenger " + passenger.getPassengerID() + " added to flight " + flightId);
    }

    // Remove a passenger from the flight
    public void removePassenger(int passengerID) { //done
        Passenger passenger = DBController.getPassengerInfo(passengerID);
        if (passenger != null) {
            DBController.removePassenger(passengerID);
            System.out.println("Passenger " + passengerID + " removed from flight " + flightId);
        } else {
            System.out.println("Passenger with ID " + passengerID + " not found.");
        }
    }

    // Get a flight by its ID
    public static Flight getFlightById(String flightId) { //done
        Flight flight = DBController.searchFlight(flightId).get(0);
        System.out.println("Fetching flight with ID: " + flightId);
        if(flight==null) {
        System.out.println("Flight with ID " + flightId + " not found.");
        }
        return flight;
    }

    // Search for flights based on ID, name, schedule, or status
    public static ArrayList<Flight> searchFlights(String searchTerm) { //done
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        matchingFlights = DBController.searchFlight(searchTerm);

        System.out.println("Searching flights with search term: " + searchTerm);

        // Replace this simulated logic with actual database query results
        return matchingFlights;
    }

    // Cancel the flight
    public void cancelFlight() { //done
        passengers.clear(); // Remove all passengers when cancelling
        DBController.cancelFlight(flightId);

        System.out.println("Flight " + flightId + " has been cancelled and all passengers removed.");
    }

    // Check if passenger is already on the flight
    private boolean isPassengerAlreadyOnFlight(Passenger passenger) { //done
        return DBController.passengerOnFlight(passenger.getPassengerID(), flightId);
    }

    // Get flight details (useful for display)
    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightSchedule='" + flightSchedule + '\'' +
                ", passengersCount=" + passengers.size() +
                '}';
    }

    // Getters
    public String getFlightId() {
        return flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightSchedule() {
        return flightSchedule;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public void incrementPassengerCount() { //done
        this.passengersCount++;
        DBController.increasePassengersCount(flightId);
    }

    public void decrementPassengerCount() { //done
        this.passengersCount--;
        DBController.decreasePassengersCount(flightId);
    }
}