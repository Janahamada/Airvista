package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateBookingController {

    @FXML
    private VBox BookingID;

    @FXML
    private Button backtohome;

    @FXML
    private TextField flightIdField;

    @FXML
    private TextField passengerNameField;

    @FXML
    private Button submitButton;
    @FXML
    private Label idLabel;

    @FXML
    void createNewBooking(ActionEvent event) {

  
    	try {
    	    // Read passengerId and flightId
    	    int passengerId = Integer.parseInt(passengerNameField.getText());
    	    System.out.println("Passenger ID: " + passengerId);
    	    
    	    String flightId = flightIdField.getText();
    	    
    	    // Create a Receptionist object
    	    Receptionist recp = new Receptionist(null, null, null, null);
    	    
    	    // Book the flight
    	    DBController.connectToDatabase();
    	    String bookingId= recp.bookFlight(flightId, passengerId);
    	    DBController.closeConnection();
    	    idLabel.setText(bookingId);
    	    
    	} catch (NumberFormatException e) {
    	    // Handle invalid input for passengerId
    	    System.err.println("Invalid input. Please enter a valid integer for Passenger ID.");
    	} catch (Exception e) {
    	    // Handle other exceptions during booking
    	    System.err.println("Error during booking: " + e.getMessage());}
    	}
    	
    	
    	
    	
    

    @FXML
    void gotohome(ActionEvent event) {
   	 try {
         // Load CancelBooking.fxml
         FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
         Parent root = loader.load();

         // Get the current stage
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

         // Set the new scene
         Scene scene = new Scene(root);
         stage.setScene(scene);
        
         stage.show();
     } catch (Exception e) {
         e.printStackTrace();
     }
 
    }

}
