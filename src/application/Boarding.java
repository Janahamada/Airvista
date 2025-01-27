package application;

public class Boarding {
    private int boardingID;
    private int GateNumber;
    private static int boardingCounter;

    public Boarding(int boardingID) {
        //this.GateNumber = GateNumber;
        boardingCounter++;
        this.boardingID=boardingCounter;
    }

    public int getBoardingID() {
        return boardingID;
    }

    public void setBoardingID(int boardingID) {
        this.boardingID = boardingID;
    }

    public int getGateNumber() {
        return GateNumber;
    }

    public void setGateNumber(int GateNumber) {
        this.GateNumber = GateNumber;
    }
    
    public void board(Flight flight, int passengerID) {   //done     
    boolean passengerFound = false;
    if(DBController.getBookingStatus(passengerID)){
        Passenger passenger = DBController.getPassengerInfo(passengerID);
        passenger.setBoardingStatus(true);
        passengerFound = true;
        DBController.changeBoardingStatus(passengerID);
    }
    if (!passengerFound) {
        System.out.println("Passenger " + passengerID + " not found on flight " + flight.getFlightId() + ".");
    }
}
 
    
    
}
