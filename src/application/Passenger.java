package application;


public class Passenger {
    private int passengerID;
    private boolean boardingStatus;
    private boolean bookingStatus;
    private Boarding board;
    private String name;
    private String phonenumber;// add phone number 
    private Flight flight;
    private static int passengerCounter;
// function to save the passngers' info 
    public Passenger(String name, String phone) { //done
        //passengerCounter++;
        //this.passengerID=passengerCounter;
        this.name = name;
        this.boardingStatus = false;
        this.bookingStatus = false;
        //this.flight = flight;
        this.phonenumber= phone;
    }

    public void createPassenger(){
        DBController.storePassengerInfo(this);
    }
    public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
