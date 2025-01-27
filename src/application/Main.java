package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.MediaView;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/application/WelcomController.fxml"));
			//loader.setLocation(getClass().getResource("/application/booking.fxml"));


	        AnchorPane root = loader.load();  // Loading the FXML layout
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("/application/welcomepage.css").toExternalForm());
	        //scene.getStylesheets().add(getClass().getResource("/application/manager.css").toExternalForm());
	        //scene.getStylesheets().add(getClass().getResource("/application/loginpage.css").toExternalForm());
	        
			primaryStage.setScene(scene);
			primaryStage.setTitle("AirVista");
			// Set the window icon
			Image icon = new Image("file:assets\\airvistalogo.png"); 
	        primaryStage.getIcons().add(icon);

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
