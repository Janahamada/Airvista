package airvista;

import java.util.ArrayList;

public class Receptionist extends User {

    public Receptionist (String name, String phoneNum, String email, String accountType) {
        super(name,phoneNum,email,accountType);
    }
    // Method to search for flights
    public ArrayList<Flight> searchFlight(String searchTerm) {
        System.out.println("Searching for flights matching: " + searchTerm);

        // DATABASE: SELECT * FROM flights WHERE flightId LIKE ? OR flightName LIKE ?;
        ArrayList<Flight> foundFlights = Flight.searchFlights(searchTerm);

        if (foundFlights.isEmpty()) {
            System.out.println("No flights found matching: " + searchTerm);
        } else {
            System.out.println("Flights found: " + foundFlights.size());
        }
        return foundFlights;
    }

    // Method to book a flight for a passenger
    public void bookFlight( String flightId,String passengerName) {
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Create the booking
            Passenger passenger = new Passenger(passengerName);
            Booking booking = new Booking(passenger, flightId);

            // DATABASE: INSERT INTO bookings (bookingId, passengerId, flightId, bookingStatus) VALUES (?, ?, ?, ?);
            System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
        } catch (Exception e) {
            System.err.println("Error during booking: " + e.getMessage());
        }
    }

    // Method to remove a passenger from a flight
    public void removePassenger(String flightId, int passengerId) {
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Remove the passenger
            flight.removePassenger(passengerId);

            // DATABASE: DELETE FROM flight_passengers WHERE flightId = ? AND passengerId = ?;
            // DATABASE: UPDATE passengers SET status = 'not booked' WHERE passengerId = ?;

            System.out.println("Passenger " + passengerId + " successfully removed from flight " + flightId);
        } catch (Exception e) {
            System.err.println("Error removing passenger: " + e.getMessage());
        }
    }

    // Method to cancel a flight and all associated bookings
    public void cancelFlight(String flightId,Passenger passenger) {
        try {
            // Fetch the flight from the database
            Flight flight = Flight.getFlightById(flightId);

            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            // Cancel the flight
            flight.cancelFlight();

            // DATABASE: UPDATE flights SET status = 'cancelled' WHERE flightId = ?;
            // DATABASE: DELETE FROM flight_passengers WHERE flightId = ?;

            System.out.println("Flight " + flightId + " has been cancelled.");

            // DATABASE: UPDATE bookings SET bookingStatus = false WHERE flightId = ?;

            System.out.println("All bookings for flight " + flightId + " have been cancelled.");
        } catch (Exception e) {
            System.err.println("Error cancelling flight: " + e.getMessage());
        }
    }
}
