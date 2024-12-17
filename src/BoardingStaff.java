
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BoardingStaff extends User  {
    private int StaffId;

//constructor
    public BoardingStaff(String name, String phoneNum, String email, String accountType, String staffId) {
        super(name, phoneNum, email, accountType);
        this.StaffId = StaffId;
     
    }

//setters and getters
    public int getBoardingStaffID() {
        return StaffId;
    }

    public void setBoardingStaffID(int boardingID) {
        this.StaffId = StaffId;
    }
 
    public void displayBoardingPass(Passenger passenger) {
        System.out.println("==============================================");
        System.out.println("                  BOARDING PASS               ");
        System.out.println("==============================================");
        System.out.printf("Passenger Name   : %s%n", passenger.getPassengerName());
        System.out.printf("Passenger ID     : %s%n", passenger.getPassengerID());
        //System.out.printf("Flight Number    : %s%n", Flight.getFlightId());  //nezawedha?
        System.out.println("----------------------------------------------");
        System.out.println("  Please arrive at the gate at least 30 mins  ");
        System.out.println("        before the scheduled departure.       ");
        System.out.println("==============================================");
    }
    

public void boardPassenger(Flight flight, int passengerID, int gateNumber) { //creates object boarding for the flight and passenger and calls it 
    // Check if flight is active
    if (flight.getStatus().equals("active")) {
        Boarding boarding = new Boarding(passengerID, gateNumber);
        
        boarding.board(flight, passengerID);
        for (Passenger passenger : flight.getPassengers()) {
            if (passenger.getPassengerID() == passengerID) {
                passenger.setBoard(boarding);  // Set the boarding information for the passenger
                break;
            }
        }
    } else {
        System.out.println("Flight " + flight.getFlightId() + " is cancelled. Passenger cannot board.");
    }
}
}
