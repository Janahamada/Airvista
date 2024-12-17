//package airvista;
public class BoardingStaff extends User {
    
    public BoardingStaff(String name, String phoneNum, String email, String accountType) {
        super(name,phoneNum,email,accountType);
    }

public void boardPassenger(Flight flight, int passengerID, int gateNumber) {
        // Check if flight is active
            Boarding boarding = new Boarding(passengerID, gateNumber);          
            boarding.board(flight, passengerID);
            Passenger passenger = DBController.getPassengerInfo(passengerID);
            passenger.setBoard(boarding); 
    }
}
