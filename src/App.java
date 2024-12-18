import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load the FXML file
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AirVista"); // Set the title of the stage
            primaryStage.show(); // Display the stage
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
    }
    public static void main(String[] args) {
        DBController.connectToDatabase();
        launch(args);
        //User.createAccount("Mohammad Tarek", "01094281972", "mohtha@gmail.com" , "mohtarek9", "Mm12345", "Manager");
        // User user = User.login("mohtarek9", "Mm12345");
        // Manager.approveAccount(user);
        //  System.out.println("User approved: " + user.getUserAccount().isApproved());
        //  Flight flight = new Flight("MS123", "flight1", "Paris", "12/12/2024");
        //  Passenger passenger = new Passenger("Jana Hamada", flight);
        //  passenger.setFlight(flight);
    }
}

