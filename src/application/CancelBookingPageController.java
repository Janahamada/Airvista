package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CancelBookingPageController {

    @FXML
    private Button backbtn;

    @FXML
    private TextField flightidtxt;

    @FXML
    private TextField passengeridtxt;

    @FXML
    private Button removebtn;

    @FXML
    void removePassenger(ActionEvent event) {


    	  
    	try {
    	    // Read passengerId and flightId
    	    int passengerId = Integer.parseInt(passengeridtxt.getText());
    	    System.out.println("Passenger ID: " + passengerId);
    	    
    	    String flightId = flightidtxt.getText();
    	    
    	    // Create a Receptionist object
    	    Receptionist recp = new Receptionist(null, null, null, null);
    	    
    	    // Book the flight
    	    DBController.connectToDatabase();
    	    recp.removePassenger(flightId, passengerId);
    	    DBController.closeConnection();
    	    
    	} catch (NumberFormatException e) {
    	    // Handle invalid input for passengerId
    	    System.err.println("Invalid input. Please enter a valid integer for Passenger ID.");
    	} catch (Exception e) {
    	    // Handle other exceptions during booking
    	    System.err.println("Error during booking: " + e.getMessage());}
 	   
    	
    }

    @FXML
    void returntohome(ActionEvent event) {
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
