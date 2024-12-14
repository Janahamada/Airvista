package airvista;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BoardingStaff {
    private int StaffId;
    private String name;

//constructor
    public BoardingStaff(String staffId, String name) {
        this.StaffId = StaffId;
        this.name = name;
    }

//setters and getters
    public int getBoardingStaffID() {
        return StaffId;
    }

    public void setBoardingStaffID(int boardingID) {
        this.StaffId = StaffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public void printBoardingPassToFile(Passenger passenger) {
        String fileName = passenger.getPassengerID() + "_BoardingPass.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("==============================================\n");
            writer.write("                  BOARDING PASS               \n");
            writer.write("==============================================\n");
            writer.write(String.format("Passenger Name   : %s%n", passenger.getPassengerName()));
            writer.write(String.format("Passenger ID     : %s%n", passenger.getPassengerID()));
            writer.write(String.format("Flight Number    : %s%n", passenger.getFlightDetails())); //mehtaga class flight
            writer.write(String.format("Boarding Status  : %s%n", passenger.isBoardingStatus()));
            writer.write("----------------------------------------------\n");
            writer.write("  Please arrive at the gate at least 30 mins  \n");
            writer.write("        before the scheduled departure.       \n");
            writer.write("==============================================\n");

            System.out.println("Boarding pass saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the boarding pass.");
            e.printStackTrace();
        }
    }



public void boardPassenger(Passenger passenger, Flight flight) { //mehtaga flight class
    // input the gate number
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter gate number for passenger " + passenger.getPassengerName() + ": ");
    int gateNumber = scanner.nextInt();

    // Create a new Boarding object
    Boarding boarding = new Boarding(passenger.getPassengerID(), gateNumber);

    // Assign the Boarding object to the passenger
    passenger.setBoard(boarding);

    // Update the passenger's boarding status
    passenger.setBoardingStatus(true);

    // Display boarding confirmation
    System.out.println("Passenger " + passenger.getPassengerName() + " has been successfully boarded.");
    System.out.println("Gate Number: " + boarding.getGateNumber());
    System.out.println("Boarding ID: " + boarding.getBoardingID());
}

}
