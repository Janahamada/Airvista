package application;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        DBController.connectToDatabase();
        //User.createAccount("Aya", "01094281972", "aya@gmail.com" , "aya123", "Mm12345", "Manager");
        User user = User.login("aya123", "Mm12345");
         Manager.approveAccount(user);
         System.out.println("User approved: " + user.getUserAccount().isApproved());
         DBController.closeConnection();
    }
    
}

