package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
		@FXML
		private MediaView video;
		@FXML
		private AnchorPane pane;
		@FXML
		private ImageView logo;
		@FXML
		private AnchorPane loginPane;
		@FXML
		private Button loginButton;
		@FXML
		private Button signupButton;
		@FXML
	    private TextField usernameInput;
	    @FXML
	    private TextField useremailinput;

	    @FXML
	    private PasswordField passwordInput;

	    @FXML
	    private TextField userphoneNumber;
	    @FXML
	    private TextField nameInput;

	    @FXML
	    private ChoiceBox<String> roleBox;

	    @FXML
	    private Label usernameError;

	   @FXML
	   private Label Name;
	    @FXML
	    private Label emailError;

	    @FXML
	    private Label passwordError;

	    @FXML
	    private Label phoneError;
	    @FXML
	    private Label nameError;


	    @FXML
	    private Label roleError;
		@FXML
		private Label username; 
		@FXML
		private Pane bgpane;
		@FXML
		private AnchorPane waitingPane;
		@FXML
		private Button backButton;
		private File file;
		private Media media;
		private MediaPlayer player;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    video.fitWidthProperty().bind((pane.widthProperty()));
	    video.fitHeightProperty().bind(pane.heightProperty());
		file = new File("assets/planes_slideshow.mp4");
		media = new Media(file.toURI().toString());
		player= new MediaPlayer(media);
		video.setMediaPlayer(player);
		player.setAutoPlay(true);
		player.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video	
		video.setOpacity(0.6);
		 ObservableList<String> roles = FXCollections.observableArrayList(
		            "Receptionist", 
		            "BoardingStaff", 
		            "Manager", 
		            "ProblemManager",
		            "AirTrafficController"
		        );

		        // Set the items to the ChoiceBox
		        roleBox.setItems(roles);
		       waitingPane.setVisible(false);
		    }
	
	@FXML
private void signup() {
	    // Clear previous error messages
	    clearErrorMessages();
	
	    boolean isValid = true;
	
	    // Validate Username
	    String username=  nameInput.getText().trim();
	    if (nameInput.getText().trim().isEmpty()) {
	        usernameError.setText("Username cannot be empty");
	        isValid = false;
	    }
	    
	    //Validate Name
	    String name=  usernameInput.getText().trim();
	    if (usernameInput.getText().trim().isEmpty()) {
	    	nameError.setText("Name cannot be empty");
	        isValid = false;
	    }
	
	    // Validate Email
	    String email = useremailinput.getText().trim();
	    if (email.isEmpty()) {
	        emailError.setText("Email cannot be empty");
	        isValid = false;
	    } else if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
	        emailError.setText("Invalid email format");
	        isValid = false;
	    }
	
	    // Validate Password
	    String password = passwordInput.getText().trim();
	    if (password.isEmpty()) {
	        passwordError.setText("Password cannot be empty");
	        isValid = false;
	    } else if (password.length() < 6) {
	        passwordError.setText("Password must be at least 6 characters");
	        isValid = false;
	    }
	
	    // Validate Phone Number
	    String phone = userphoneNumber.getText().trim();
	    if (phone.isEmpty()) {
	        phoneError.setText("Phone number cannot be empty");
	        isValid = false;
	    } else if (!phone.matches("^(\\+\\d{1,3}\\s?)?\\(?\\d{1,4}\\)?[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}$")) {
	        phoneError.setText("Phone number must be 10 digits");
	        isValid = false;
	    }
	
	    // Validate Role
	    if (roleBox.getValue() == null ) {
	        roleError.setText("Role must be selected");
	        isValid = false;
	    }
	    String role= roleBox.getValue();
	
	    // If all fields are valid
	    if (isValid) {
	    	//for debugging
	        System.out.println("Sign-Up Successful");
	        showWaitingPane();
	        DBController.connectToDatabase();
	        Account.createAccount(name, phone, email, username, password, role);
	        DBController.closeConnection();
	        // Add logic to save data or proceed with sign-up
	    }
	    
	}

private void clearErrorMessages() {
    usernameError.setText("");
    emailError.setText("");
    passwordError.setText("");
    phoneError.setText("");
    roleError.setText("");
}

private void showWaitingPane() {
    loginPane.setVisible(false);
    waitingPane.setVisible(true);
}

public void back() {
	waitingPane.setVisible(false);
	 try {
         // Load the welcome.fxml
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/WelcomController.fxml"));
     	WelcomeController welcomeController = loader.getController();
         Parent WelcomeRoot = loader.load();

         // Get the current stage (window) and set the new scene
         Stage currentStage = (Stage) backButton.getScene().getWindow();
         Scene WelcomeScene = new Scene(WelcomeRoot);
         currentStage.setScene(WelcomeScene);
         currentStage.show();
     } catch (Exception ex) {
         ex.printStackTrace();
     }
}
    
	}

