package airvista;

import java.io.FileWriter;
import java.io.IOException;

public class Passenger {
    private String passengerName;
    private int passengerID;
    private boolean boardingStatus;
    private Boarding board;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }
    public boolean isBoardingStatus() {
        return boardingStatus;
    }

    public void setBoardingStatus(boolean boardingStatus) {
        this.boardingStatus = boardingStatus;
    }

    public Boarding getBoard() {
        return board;
    }

    public void setBoard(Boarding board) {
        this.board = board;
    }
    
}
