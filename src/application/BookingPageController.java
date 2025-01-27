package application;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class BookingPageController {

    @FXML
    private Button button1;

    @FXML
    private Button button11;

    @FXML
    private Button button2;

    @FXML
    private Button button21;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img11;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img31;

    @FXML
    private ImageView pane;

    @FXML
    private ImageView pane1;

    @FXML
    private TextField passengername;

    @FXML
    private TextField passengerphonenumber;

    @FXML
    private Button savebtn;

    @FXML
    private TextField search;

    @FXML
    private TextField search1;

    @FXML
    private TableView flights;
    @FXML
    private Label idLabel;

    @FXML
    void savepassengerdata(ActionEvent event) {
    	  String Passengername = passengername.getText();
    	  String Passengerphonenumber = passengerphonenumber.getText();
    	  Passenger passenger= new Passenger(Passengername, Passengerphonenumber);
    	  DBController.connectToDatabase();
    	  passenger.setPassengerID(DBController.storePassengerInfo(passenger)); 
    	  DBController.closeConnection();
    	  idLabel.setText(Integer.toString(passenger.getPassengerID()));
    	

    }
    @FXML
    void gotoAddBooking(ActionEvent event) {
        try {
            // Load CancelBooking.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cancel Booking");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    


    }

    @FXML
    void gotocancelBooking(ActionEvent event) {
    	
    	
        try {
            // Load CancelBooking.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelBooking.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/welcomepage.css").toExternalForm());
	        scene.getStylesheets().add(getClass().getResource("/application/loginpage.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Cancel Booking");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    

    }

    @FXML
    void searchBooking(ActionEvent event) {
    	 
	    String searchTerm = search1.getText();
	    
	    // Create a Receptionist object
	    Receptionist recp = new Receptionist(null, null, null, null);
	    DBController.connectToDatabase();
    	ArrayList<Flight> results = recp.searchFlight(searchTerm);
    	DBController.closeConnection();
 	   
    	// Convert ArrayList to ObservableList
        ObservableList<Flight> flightList = FXCollections.observableArrayList(results);

        // FlightId Column
        TableColumn<Flight, String> flightIdColumn = new TableColumn<>("Flight ID");
        flightIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlightId()));

        // FlightName Column
        TableColumn<Flight, String> flightNameColumn = new TableColumn<>("Flight Name");
        flightNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlightName()));

        // FlightSchedule Column
        TableColumn<Flight, String> flightScheduleColumn = new TableColumn<>("Flight Schedule");
        flightScheduleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlightSchedule()));

        // Destination Column
        TableColumn<Flight, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));

        // Add columns to the table
        flights.getColumns().add(flightIdColumn);
        flights.getColumns().add(flightNameColumn);
        flights.getColumns().add(flightScheduleColumn);
        flights.getColumns().add(destinationColumn);

        // Set the data for the TableView
        flights.setItems(flightList);
        flights.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    	
    	
    	
    }

}
