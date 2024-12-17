package airvista;
public class Passenger {
    private int passengerID;
    private boolean boardingStatus;
    private boolean bookingStatus;
    private Boarding board;
    private String name;
    private static int passengerCounter;

    public Passenger(String name) {
        passengerCounter++;
        this.passengerID=passengerCounter;
        this.name = name;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId='" + passengerID + '\'' +
                ", name='" + name + '\'' +
                ", status='" + boardingStatus + '\'' +
                '}';
    }
    
}
