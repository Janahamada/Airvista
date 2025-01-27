package application;


import java.util.ArrayList;

public class Receptionist extends User {

    public Receptionist (String name, String phoneNum, String email, String accountType) {
        super(name,phoneNum,email,accountType);
    }
    // Method to search for flights
    public ArrayList<Flight> searchFlight(String searchTerm) { //done
        System.out.println("Searching for flights matching: " + searchTerm);
        return Flight.searchFlights(searchTerm);
    }

    // Method to book a flight for a passenger
    public String bookFlight( String flightId,int passengerId) { //done
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return "";
            }

            //System.out.println("no errrrrrroor");
            // Create the booking
            Passenger passenger = DBController.getPassengerInfo(passengerId);
            passenger.setPassengerID(passengerId);
            
            Booking booking = new Booking(passenger, flightId);
            return booking.getBookingId();

            //System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
        } catch (Exception e) {
            System.err.println("Error during booking: " + e.getMessage());
        }
        return "";
    }

    // Method to remove a passenger from a flight
    public void removePassenger(String flightId, int passengerId) { //done
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }
            // Remove the passenger
            flight.removePassenger(passengerId);

            System.out.println("Passenger " + passengerId + " successfully removed from flight " + flightId);
        } catch (Exception e) {
            System.err.println("Error removing passenger: " + e.getMessage());
        }
    }

    // Method to cancel a flight and all associated bookings
    public void cancelFlight(String flightId,Passenger passenger) { //done
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Cancel the flight
            flight.cancelFlight();

            System.out.println("Flight " + flightId + " has been cancelled.");

            System.out.println("All bookings for flight " + flightId + " have been cancelled.");
        } catch (Exception e) {
            System.err.println("Error cancelling flight: " + e.getMessage());
        }
    }
}