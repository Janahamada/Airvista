package AirVista;

import java.util.ArrayList;
import java.util.HashMap;

public class Flight {

    private String flightId;
    private String flightName;
    private String flightSchedule;
    private String status; // "active" or "cancelled"
    private ArrayList<Passenger> passengers = new ArrayList<>();

    // Static map to store flights
   // private static HashMap<String, Flight> flights = new HashMap<>();

    public Flight(String flightId, String flightName, String flightSchedule) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.flightSchedule = flightSchedule;
        this.status = "active"; // Default status
        flights.put(flightId, this);
    }

    // Add a passenger to the flight
    public void addPassenger(Passenger passenger) {
        if (status.equals("active")) {
            passengers.add(passenger);
            System.out.println("Passenger " + passenger.getPassengerId() + " added to flight " + flightId);
        } else {
            System.out.println("Cannot add passenger. Flight " + flightId + " is cancelled.");
        }
    }

    // Remove a passenger from the flight
    public void removePassenger(String passengerId) {
        if (status.equals("cancelled")) {
            System.out.println("Cannot remove passenger. Flight " + flightId + " is cancelled.");
            return;
        }

        Passenger passengerToRemove = null;
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerId().equals(passengerId)) {
                passengerToRemove = passenger;
                break;
            }
        }

        if (passengerToRemove != null) {
            passengers.remove(passengerToRemove);
            System.out.println("Passenger " + passengerId + " removed from flight " + flightId);
        } else {
            System.out.println("Passenger with ID " + passengerId + " not found.");
        }
    }

    // Cancel the flight and remove all passengers
    public void cancelFlight() {
        if (!status.equals("cancelled")) {
            status = "cancelled";
            passengers.clear(); // Remove all passengers
            System.out.println("Flight " + flightId + " has been cancelled.");
        } else {
            System.out.println("Flight " + flightId + " is already cancelled.");
        }
    }

    // Static method to get a flight by ID
    public static Flight getFlightById(String flightId) {
        return flights.get(flightId);
    }

    // Static method to search flights by flightId or flightName
    public static ArrayList<Flight> searchFlight(String searchTerm) {
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (flight.flightId.contains(searchTerm) || flight.flightName.contains(searchTerm)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }

    // Get the flight schedule
    public String getFlightSchedule() {
        return flightSchedule;
    }

    // Static method to get all flights
    public static HashMap<String, Flight> getAllFlights() {
        return flights;
    }

    // Getters
    public String getFlightId() {
        return flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
