package AirVista;

import java.util.ArrayList;

public class Flight {

    private String flightId;
    private String flightName;
    private String flightSchedule;
    private String status;  // "active" or "cancelled"
    private ArrayList<Passenger> passengers = new ArrayList<>(); // List of passengers on the flight

    // Constructor
    public Flight(String flightId, String flightName, String flightSchedule) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.flightSchedule = flightSchedule;
        this.status = "active";  // Default flight status

        // DATABASE: INSERT INTO flights (flightId, flightName, flightSchedule, status) VALUES (?, ?, ?, ?);
    }

    // Add passenger to the flight
    public boolean addPassenger(Passenger passenger) {
        if (!status.equals("active")) {
            System.out.println("Cannot add passenger. Flight " + flightId + " is cancelled.");
            return false;
        }
        if (isPassengerAlreadyOnFlight(passenger)) {
            System.out.println("Passenger " + passenger.getPassengerId() + " is already on flight " + flightId);
            return false;
        }

        passengers.add(passenger);
        passenger.setStatus("booked");

        // DATABASE: INSERT INTO flight_passengers (flightId, passengerId) VALUES (?, ?);
        // DATABASE: UPDATE passengers SET status = 'booked' WHERE passengerId = ?;

        System.out.println("Passenger " + passenger.getPassengerId() + " added to flight " + flightId);
        return true;
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
            passengerToRemove.setStatus("not booked");

            // DATABASE: DELETE FROM flight_passengers WHERE flightId = ? AND passengerId = ?;
            // DATABASE: UPDATE passengers SET status = 'not booked' WHERE passengerId = ?;

            System.out.println("Passenger " + passengerId + " removed from flight " + flightId);
        } else {
            System.out.println("Passenger with ID " + passengerId + " not found.");
        }
    }

    // Get a flight by its ID
    public static Flight getFlightById(String flightId) {
        // DATABASE: SELECT * FROM flights WHERE flightId = ?;
        // This method should query the database and return the corresponding flight.

        System.out.println("Fetching flight with ID: " + flightId);
        return null; // Replace with actual database query result
    }

    // Search for flights based on ID, name, schedule, or status
    public static ArrayList<Flight> searchFlights(String searchTerm) {
        ArrayList<Flight> matchingFlights = new ArrayList<>();

        // DATABASE: SELECT * FROM flights WHERE flightId LIKE ? OR flightName LIKE ? OR flightSchedule LIKE ? OR status LIKE ?;
        System.out.println("Searching flights with search term: " + searchTerm);

        // Replace this simulated logic with actual database query results
        return matchingFlights;
    }

    // Cancel the flight
    public void cancelFlight() {
        if (status.equals("cancelled")) {
            System.out.println("Flight " + flightId + " is already cancelled.");
            return;
        }

        status = "cancelled";
        passengers.clear(); // Remove all passengers when cancelling

        // DATABASE: UPDATE flights SET status = 'cancelled' WHERE flightId = ?;
        // DATABASE: DELETE FROM flight_passengers WHERE flightId = ?;

        System.out.println("Flight " + flightId + " has been cancelled and all passengers removed.");
    }

    // Check if passenger is already on the flight
    private boolean isPassengerAlreadyOnFlight(Passenger passenger) {
        return passengers.stream()
                .anyMatch(p -> p.getPassengerId().equals(passenger.getPassengerId()));
    }

    // Get flight details (useful for display)
    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightSchedule='" + flightSchedule + '\'' +
                ", status='" + status + '\'' +
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

    public String getFlightSchedule() {
        return flightSchedule;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
