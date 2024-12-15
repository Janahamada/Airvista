package AirVista;

import java.util.ArrayList;

public class Receptionist {

    // Attributes
    private String receptionistId;

    // Constructor
    public Receptionist(String receptionistId) {
        this.receptionistId = receptionistId;
    }

    // Getters and Setters
    public String getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(String receptionistId) {
        this.receptionistId = receptionistId;
    }

    // Method to search for flights
    public ArrayList<Flight> searchFlight(String searchTerm) {
        // Uses the Flight class's static method to search for flights
        ArrayList<Flight> foundFlights = Flight.searchFlight(searchTerm);
        if (foundFlights.isEmpty()) {
            System.out.println("No flights found matching: " + searchTerm);
        }
        return foundFlights;
    }

    // Method to book a flight for a passenger
    public void bookFlight(Passenger passenger, String flightId) {
        try {
            // Get the flight by ID
            Flight flight = Flight.getFlightById(flightId);
            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Check if the flight is cancelled
            if (flight.getStatus().equals("cancelled")) {
                System.out.println("Cannot book passenger. Flight " + flightId + " is cancelled.");
                return;
            }

            // Create the booking
            Booking booking = new Booking(passenger, flightId);
            System.out.println("Booking successful: " + booking.getBookingId());
        } catch (Exception e) {
            System.out.println("Error during booking: " + e.getMessage());
        }
    }

    // Method to remove a passenger from a flight
    public void removePassenger(String flightId, String passengerId) {
        try {
            // Get the flight by ID
            Flight flight = Flight.getFlightById(flightId);
            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Remove the passenger from the flight
            flight.removePassenger(passengerId);
            System.out.println("Passenger " + passengerId + " removed from flight " + flightId);
        } catch (Exception e) {
            System.out.println("Error removing passenger: " + e.getMessage());
        }
    }

    // Method to cancel a flight and all associated bookings
    public void cancelFlight(String flightId) {
        try {
            // Get the flight by ID
            Flight flight = Flight.getFlightById(flightId);
            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Cancel the flight
            flight.cancelFlight();
            System.out.println("Flight " + flightId + " has been cancelled.");

            // Cancel all bookings for the flight
            Booking.cancelBookingsForFlight(flightId);
            System.out.println("All bookings for flight " + flightId + " have been cancelled.");
        } catch (Exception e) {
            System.out.println("Error cancelling flight: " + e.getMessage());
        }
    }
}
