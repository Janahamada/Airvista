package airvista;

import java.io.FileWriter;
import java.io.IOException;

public class BoardingStaff extends User {
    
    public BoardingStaff(String name, String phoneNum, String email, String accountType) {
        super(name,phoneNum,email,accountType);
    }

    public void printBoardingPassToFile(Passenger passenger)  {
        String fileName = passenger.getPassengerID() + "_BoardingPass.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("==============================================\n");
            writer.write("                  BOARDING PASS               \n");
            writer.write("==============================================\n");
            writer.write(String.format("Passenger Name   : %s%n", passenger.getName()));
            writer.write(String.format("Passenger ID     : %s%n", passenger.getPassengerID()));
            //writer.write(String.format("Flight Number    : %s%n", passenger.getFlightDetails())); //mehtaga class flight
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

public void boardPassenger(Flight flight, int passengerID, int gateNumber) {
        // Check if flight is active
            Boarding boarding = new Boarding(passengerID, gateNumber);          
            boarding.board(flight, passengerID);
            for (Passenger passenger : flight.getPassengers()) {
                if (passenger.getPassengerID() == passengerID) {
                    passenger.setBoard(boarding);  // Set the boarding information for the passenger
                    break;
                }
            }
    }
}
