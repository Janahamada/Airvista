package AirVista;

import java.util.HashMap;

public class Booking {

    private String bookingId;      // Unique identifier for each booking
    private String passengerId;    // Passenger ID
    private String flightId;       // Flight ID
    private boolean bookingStatus; // true = active, false = cancelled

    // Static Maps to manage data
    private static int bookingCounter = 0;
    private static HashMap<String, Booking> bookings = new HashMap<>();
    private static HashMap<String, Integer> passengerCountMap = new HashMap<>();

    // Constructor
    public Booking(Passenger passenger, String flightId) {
        try {
            Flight flight = Flight.getFlightById(flightId);
            if (flight == null) {
                throw new Exception("Flight with ID " + flightId + " not found.");
            }

            if (isPassengerAlreadyBooked(passenger.getPassengerId(), flightId)) {
                throw new Exception("Passenger already booked on flight " + flightId);
            }

            // Generate Booking ID
            this.bookingId = generateBookingId(flightId, passenger.getPassengerId());
            this.passengerId = passenger.getPassengerId();
            this.flightId = flightId;
            this.bookingStatus = true;

            // Update flight and passenger status
            flight.addPassenger(passenger);
            passenger.setStatus("booked");
            incrementPassengerCount(flightId);

            bookings.put(bookingId, this);
            System.out.println("Booking successful: " + bookingId);
        } catch (Exception e) {
            System.err.println("Error during booking: " + e.getMessage());
        }
    }

    // Generate a unique booking ID
    private static String generateBookingId(String flightId, String passengerId) {
        bookingCounter++;
        return "B" + bookingCounter + "-" + flightId + "-" + passengerId;
    }

    // Check if passenger is already booked
    private static boolean isPassengerAlreadyBooked(String passengerId, String flightId) {
        return bookings.values().stream()
                .anyMatch(b -> b.passengerId.equals(passengerId) && b.flightId.equals(flightId));
    }

    // Increment passenger count
    private static void incrementPassengerCount(String flightId) {
        passengerCountMap.put(flightId, passengerCountMap.getOrDefault(flightId, 0) + 1);
    }

    // Cancel all bookings for a flight
    public static void cancelBookingsForFlight(String flightId) {
        bookings.values().stream()
                .filter(b -> b.flightId.equals(flightId) && b.bookingStatus)
                .forEach(b -> b.cancelBooking());
    }

    // Cancel a single booking
    public void cancelBooking() {
        if (!bookingStatus) {
            System.err.println("Booking already cancelled: " + bookingId);
            return;
        }
        bookingStatus = false;
        Flight flight = Flight.getFlightById(this.flightId);
        if (flight != null) {
            flight.removePassenger(this.passengerId); // Remove passenger from the flight
        }
        System.out.println("Booking " + bookingId + " cancelled.");
    }

    // Getters
    public String getBookingId() {
        return bookingId;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    // Static method to get all bookings
    public static HashMap<String, Booking> getAllBookings() {
        return bookings;
    }
}
