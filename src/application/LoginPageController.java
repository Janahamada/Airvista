package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class LoginPageController implements Initializable{
	
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
	private PasswordField passwordInput;
	@FXML
	private Label loginErrorlabel; 
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
		loginErrorlabel.setVisible(false);	
		video.setOpacity(0.6);
		waitingPane.setVisible(false);
	}
	
	public void login() {
		loginErrorlabel.setVisible(false);
		String username= usernameInput.getText();
		String password= passwordInput.getText();
		System.out.println("username: "+ username+" password: "+password);
		DBController.connectToDatabase();
		User user = Account.login(username, password);
		if(user != null) {
			System.out.println("name: "+ user.getName()+" email: "+user.getEmail());
			if(user.getUserAccount().isApproved()){
				
				if(user.getAccountType().equals("Manager")) {
					 Manager newUser = new Manager(user.getName(), user.getPhoneNum(), user.getEmail(), user.getAccountType());
					 //switch to Manager scene and pass this object
					 try {
			            // Load the SignUpPage.fxml
			        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/managerView.fxml"));
			        	ManagerController managerController = loader.getController();
			            Parent signUpRoot = loader.load();
			            // Get the current stage (window) and set the new scene
			            Stage currentStage = (Stage) signupButton.getScene().getWindow();
			            Scene managerScene = new Scene(signUpRoot);
			            managerScene.getStylesheets().add(getClass().getResource("/application/manager.css").toExternalForm());
				        
			            currentStage.setScene(managerScene);
			            currentStage.show();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }

					 
				}
				else if(user.getAccountType().equals("Receptionist")) {
					Receptionist newUser = new Receptionist(user.getName(), user.getPhoneNum(), user.getEmail(), user.getAccountType());
					 //switch to Booking scene and pass this object
					 try {
				            // Load the SignUpPage.fxml
				        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/booking.fxml"));
				        	BookingPageController cont = loader.getController();
				            Parent signUpRoot = loader.load();
				            // Get the current stage (window) and set the new scene
				            Stage currentStage = (Stage) signupButton.getScene().getWindow();
				            Scene scene = new Scene(signUpRoot);
				            //scene.getStylesheets().add(getClass().getResource("/application/welcomepage.css").toExternalForm());
					        scene.getStylesheets().add(getClass().getResource("/application/loginpage.css").toExternalForm());
				            currentStage.setScene(scene);
				            currentStage.show();
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
					 
				}
				else if(user.getAccountType().equals("BoardingStaff")) {
				 	BoardingStaff newUser = new BoardingStaff(user.getName(), user.getPhoneNum(), user.getEmail(), user.getAccountType());
					//switch to Boarding scene and pass this object
				 	try {
			            // Load the SignUpPage.fxml
			        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/boarding_intro.fxml"));
			        	BoardingController boardingController = loader.getController();
			            Parent signUpRoot = loader.load();
			            // Get the current stage (window) and set the new scene
			            Stage currentStage = (Stage) signupButton.getScene().getWindow();
			            Scene boardingScene = new Scene(signUpRoot);
			            boardingScene.getStylesheets().add(getClass().getResource("/application/buttons.css").toExternalForm());
			            currentStage.setScene(boardingScene);
			            currentStage.show();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				 	
				}
				else if(user.getAccountType().equals("AirTrafficController")) {
					//AirTrafficController newUser = new AirTrafficController(user.getName(), user.getPhoneNum(), user.getEmail(), user.getAccountType());
					//switch to air traffic control scene and pass this object
					try {
			            // Load the SignUpPage.fxml
			        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Weather.fxml"));
			        	BookingPageController cont = loader.getController();
			            Parent signUpRoot = loader.load();
			            // Get the current stage (window) and set the new scene
			            Stage currentStage = (Stage) signupButton.getScene().getWindow();
			            Scene scene = new Scene(signUpRoot);
			            //scene.getStylesheets().add(getClass().getResource("/application/welcomepage.css").toExternalForm());
				        scene.getStylesheets().add(getClass().getResource("/application/loginpage.css").toExternalForm());
			            currentStage.setScene(scene);
			            currentStage.show();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				 
				}
				else if(user.getAccountType().equals("ProblemManager")) {
					//ProblemManager newUser = new ProblemManager(user.getName(), user.getPhoneNum(), user.getEmail(), user.getAccountType());
					//switch to problem management scene and pass this object
					try {
			            // Load the SignUpPage.fxml
			        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Problemmanager.fxml"));
			        	BookingPageController cont = loader.getController();
			            Parent signUpRoot = loader.load();
			            // Get the current stage (window) and set the new scene
			            Stage currentStage = (Stage) signupButton.getScene().getWindow();
			            Scene scene = new Scene(signUpRoot);
			            //scene.getStylesheets().add(getClass().getResource("/application/welcomepage.css").toExternalForm());
				        scene.getStylesheets().add(getClass().getResource("/application/loginpage.css").toExternalForm());
			            currentStage.setScene(scene);
			            currentStage.show();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				 
					
				}
			}
			else {
				//switch to wait for approval scene
				waitingPane.setVisible(true);
				loginPane.setVisible(false);
			}
		}
		else {
			loginErrorlabel.setVisible(true);
		}
			
		DBController.closeConnection();
			
	}
	
	public void back() {
		waitingPane.setVisible(false);
		loginPane.setVisible(true);
	}
	public void gotoSignUp() {
		 try {
	            // Load the SignUpPage.fxml
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SignUpController.fxml"));
	        	SignUpController signUpController = loader.getController();
	            Parent signUpRoot = loader.load();

	            // Get the current stage (window) and set the new scene
	            Stage currentStage = (Stage) signupButton.getScene().getWindow();
	            Scene signUpScene = new Scene(signUpRoot);
	            currentStage.setScene(signUpScene);
	            currentStage.show();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
