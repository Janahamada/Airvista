package airvista;
public class Passenger {
    private int passengerID;
    private boolean boardingStatus;
    private Boarding board;

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
