package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class BoardingController {

    @FXML
    private Label BoardingissuccLabel;
    @FXML
    private TextField PassengerIDTF;
    @FXML
    private Label IDemptylabel;

     Flight flight;
     int passengerID = 0;
     Passenger passenger;

    @FXML
    private void handleBoardPassenger(ActionEvent event) {
        // Check if the TextField is empty
    	DBController.connectToDatabase();
        passengerID = 0;
        try{
            passengerID = (PassengerIDTF.getText().trim().isEmpty()) ? 0 : Integer.parseInt(PassengerIDTF.getText().trim());
            if(DBController.findPassengerID(passengerID) == false){
                BoardingissuccLabel.setText("Passenger with id " + passengerID + " is not found");
            }
        }catch(NumberFormatException e){
            IDemptylabel.setText("Invalid Passenger ID. Please enter a numeric value.");
        }
        if (passengerID == 0) {
            IDemptylabel.setText("No Passenger ID provided");
            return; 
        }
        IDemptylabel.setText("");
        flight = DBController.getFlightFromPassengerID(passengerID);
        System.out.println("heyyyyy : " + flight.getDestination());
        //String flightid1 = flight.getFlightId();
        passenger = DBController.getPassengerInfo(passengerID);
        try {
            //passengerID = Integer.parseInt(PassengerIDTF.getText().trim());
            //passenger.setPassengerID(passengerID);
            BoardingStaff.boardPassenger(flight,passengerID);
            if (DBController.getBoardingStatus(passengerID)) {
                BoardingissuccLabel.setText("Boarding successful for Passenger: " + passenger.getName() + "!");
            } else {
                BoardingissuccLabel.setText("Boarding failed for Passenger ID: " + passengerID);
            }
        } 
         catch (Exception e) {
            // Handle other unexpected exceptions
            //IDemptylabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
        DBController.closeConnection();
    }

    public void switchtoprint(ActionEvent event) throws Exception {
        if (flight == null) {
            IDemptylabel.setText("Cannot switch to print. Ensure passenger is boarded successfully.");
            return;
        }
        System.out.println(flight.getDestination());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("print.fxml"));
        Parent root = loader.load();
        PrintController printController = loader.getController(); 
        //testing
        
        // Pass the destination to the print controller
        //passenger.getBoard().getBoardingID()
        printController.setboardingdetails(flight.getDestination(),flight.getFlightName(),passenger.getName(),flight.getFlightSchedule());   
        Stage stage = new Stage();
        stage.setTitle("Boarding Pass");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}

