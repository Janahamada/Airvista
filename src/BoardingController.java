//package airvista;
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

    // Flight flight1 = new Flight("12", "ay zeft", "12/11/2024");
    // Passenger passenger = new Passenger("Jana");

    @FXML
    private void handleBoardPassenger(ActionEvent event) {
        // Check if the TextField is empty
        int passengerID = 0;
        try{
            passengerID = (PassengerIDTF.getText().trim().isEmpty()) ? 0 : Integer.parseInt(PassengerIDTF.getText().trim());
        }catch(NumberFormatException e){
            IDemptylabel.setText("Invalid Passenger ID. Please enter a numeric value.");
        }
        if (passengerID == 0) {
            IDemptylabel.setText("No Passenger ID provided");
            return; 
        }
        IDemptylabel.setText("");
        Flight flight = DBController.getFlightFromPassengerID(passengerID);
        //String flightid1 = flight.getFlightId();
        Passenger passenger = DBController.getPassengerInfo(passengerID);
        try {
            //passengerID = Integer.parseInt(PassengerIDTF.getText().trim());
            //passenger.setPassengerID(passengerID);
            BoardingStaff.boardPassenger(flight,passengerID);
            if (passenger.isBoardingStatus()) {
                BoardingissuccLabel.setText("Boarding successful for Passenger: " + passenger.getName() + "!");
            } else {
                BoardingissuccLabel.setText("Boarding failed for Passenger ID: " + passengerID);
            }
        } 
         catch (Exception e) {
            // Handle other unexpected exceptions
            IDemptylabel.setText("Error: " + e.getMessage());
        }
    }

    // public void switchtoprint(ActionEvent event) throws Exception {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("print.fxml"));
    //     Parent root = loader.load();
    //     PrintController printController = loader.getController(); 
    //     //testing
    //     flight.setflightdestination("Paris"); 
    //     // Pass the destination to the print controller
    //     //passenger.getBoard().getBoardingID()
    //     printController.setboardingdetails(flight.getFlightDestination(),flight.getFlightName(),passenger.getName(),flight.getFlightSchedule());   
    //     Stage stage = new Stage();
    //     stage.setTitle("Boarding Pass");
    //     stage.setScene(new Scene(root));
    //     stage.setResizable(false);
    //     stage.show();
    // }
}


