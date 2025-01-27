package application;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private ImageView slideImage; // ImageView for slideshow
    @FXML
    private Button loginButton;   // Login button
    @FXML
    private Button signupButton;  // Signup button
    @FXML
    private Text welcomeText;     // Welcome text

    private int currentImageIndex = 0;

    private final String[] imagePaths = {
    		 "/application/airplane1.png",
            "/application/airplane4.jpg",
            "/application/airplane7.jpg",
            "/application/airplane5.png",  
    };

    private Timeline slideshow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add a listener to wait for the scene to be set
        slideImage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Bind the ImageView size to the scene size to make it responsive
                slideImage.fitWidthProperty().bind(newScene.widthProperty());
                slideImage.fitHeightProperty().bind(newScene.heightProperty());
                slideImage.setPreserveRatio(true);

                // Set the initial blurred image
                slideImage.setImage(new Image(getClass().getResource(imagePaths[currentImageIndex]).toExternalForm()));
                applyBlur();

                // Begin the sequence
                startSequence();
            }
        });

        // Set buttons and text initially hidden
        loginButton.setOpacity(0);
        signupButton.setOpacity(0);
        welcomeText.setOpacity(0);
    }

    private void applyBlur() {
        GaussianBlur blur = new GaussianBlur(15);
        slideImage.setEffect(blur);
    }

    private void startSequence() {
        // Start the animation for text
        animateText(() -> {
            // Show buttons after text animation finishes
            showButtons(() -> {
                // Start the slideshow in the background
                startSlideshow();
            });
        });
    }

    private void animateText(Runnable onFinished) {
        String message = "Welcome to AirVista, the World of Flights!";
        Timeline textTimeline = new Timeline();
        final StringBuilder displayedText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int charIndex = i;
            textTimeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(100 * charIndex),
                    e -> {
                        displayedText.append(message.charAt(charIndex));
                        welcomeText.setText(displayedText.toString());
                        welcomeText.setOpacity(1);
                    }
            ));
        }

        textTimeline.setOnFinished(e -> onFinished.run());
        textTimeline.play();
    }

    private void showButtons(Runnable onFinished) {
        // Position buttons below the welcomeText
        double buttonSpacing = 20; // Space between buttons
        double startY = welcomeText.getBoundsInParent().getMaxY() + 40;
        loginButton.setLayoutY(startY);
        signupButton.setLayoutY(startY + loginButton.getHeight() + buttonSpacing);

        // Center buttons horizontally
        loginButton.setLayoutX((slideImage.getScene().getWidth() - loginButton.getWidth()) / 2);
        signupButton.setLayoutX((slideImage.getScene().getWidth() - signupButton.getWidth()) / 2);
        signupButton.setOnAction(e -> switchToSignUpPage());

        // Fade-in animation for both buttons
        FadeTransition fadeInLogin = new FadeTransition(Duration.seconds(1), loginButton);
        fadeInLogin.setFromValue(0);
        fadeInLogin.setToValue(1);

        FadeTransition fadeInSignup = new FadeTransition(Duration.seconds(1), signupButton);
        fadeInSignup.setFromValue(0);
        fadeInSignup.setToValue(1);

        // Play animations together
        ParallelTransition buttonsFadeIn = new ParallelTransition(fadeInLogin, fadeInSignup);
        buttonsFadeIn.setOnFinished(e -> onFinished.run());
        buttonsFadeIn.play();
    }

    private void startSlideshow() {
        slideshow = new Timeline(
                new KeyFrame(Duration.seconds(4), e -> showNextImage())
        );
        slideshow.setCycleCount(Timeline.INDEFINITE);
        slideshow.play();
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        slideImage.setImage(new Image(getClass().getResource(imagePaths[currentImageIndex]).toExternalForm()));
    }
    public void switchToSignUpPage() {
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
    
    public void switchToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LoginController.fxml"));
            LoginPageController loginPageController = loader.getController();
            Parent loginRoot = loader.load();

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            currentStage.setScene(loginScene);
            currentStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
}
