package airvista;  
import java.util.HashMap;
public class Booking {

    private String bookingId;      // Unique identifier for each booking
    private String flightId;       // Flight ID
    int passengerID;
    // Static Maps to manage data
    private static int bookingCounter = 0;
    private static HashMap<String, Integer> passengerCountMap = new HashMap<>();

    // Constructor
    public Booking(Passenger passenger, String flightId) {
        try {
            Flight flight = Flight.getFlightById(flightId);
            if (flight == null) {
                throw new Exception("Flight with ID " + flightId + " not found.");
            }
            if (isPassengerAlreadyBooked(passenger)) {
                throw new Exception("Passenger already booked on flight " + flightId);
            } 
            // Generate Booking ID 
            this.bookingId = generateBookingId(flightId, passenger.getPassengerID());
            passengerID = passenger.getPassengerID();
            
            this.flightId = flightId;

            // Update flight and passenger status
            flight.addPassenger(passenger);
            passenger.setBookingStatus(true);
            incrementPassengerCount(flightId);
            System.out.println("Booking successful: " + bookingId);
        } catch (Exception e) {
            System.err.println("Error during booking: " + e.getMessage());
        }
    }

    // Generate a unique booking ID
    private static String generateBookingId(String flightId, int passengerID) {
        bookingCounter++;
        return "B" + bookingCounter + "-" + flightId + "-" + passengerID;
    }

    // Check if passenger is already booked
    static boolean isPassengerAlreadyBooked(Passenger passenger) {
        if(passenger.getBookingStatus()==true)
            return true;
        else 
            return false;       
}

    // Increment passenger count
    private static void incrementPassengerCount(String flightId) {
        passengerCountMap.put(flightId, passengerCountMap.getOrDefault(flightId, 0) + 1);
    }

    // Cancel a single booking
    public void cancelBooking(Passenger passenger ) {
        if (passenger.getBookingStatus()==false) {
            System.err.println("Booking already cancelled: " + bookingId);
            return;
        }
        Flight flight = Flight.getFlightById(this.flightId);
        if (flight != null) {
            flight.removePassenger(this.passengerID); // Remove passenger from the flight
        }
        System.out.println("Booking " + bookingId + " cancelled.");
    }

    // Getters
    public String getBookingId() {
        return bookingId;
    }
} 

