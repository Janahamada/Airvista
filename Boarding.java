package airvista;

public class Boarding {
    private int boardingID;
    private int GateNumber;
    private int boardingCounter;

    public Boarding(int boardingID, int GateNumber) {
        this.GateNumber = GateNumber;
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
    
    public void board(Flight flight, int passengerID) {       
    boolean passengerFound = false;
    for (Passenger passenger : flight.getPassengers()) {
        if (passenger.getPassengerID() == passengerID) {
            passenger.setBoardingStatus(true);
            passengerFound = true;
            System.out.println("Passenger " + passengerID + " successfully boarded.");
            break;
        }
    }
    if (!passengerFound) {
        System.out.println("Passenger " + passengerID + " not found on flight " + flight.getFlightID() + ".");
    }
}
 
    
    
}
