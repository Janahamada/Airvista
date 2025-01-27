package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import javafx.scene.Node;


public class BoardingStaffController {

    @FXML
    private Button boardButton;

    @FXML
    private ImageView img;

    @FXML
    private Label welcomemsg;

    
    @FXML
    void switchToBoardingScene(ActionEvent event) throws IOException {
        try {
            // Load the SignUpPage.fxml
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Boarding.fxml"));
        	BoardingController boardingController = loader.getController();
            Parent signUpRoot = loader.load();
            // Get the current stage (window) and set the new scene
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene boardingScene = new Scene(signUpRoot);
            boardingScene.getStylesheets().add(getClass().getResource("/application/buttons.css").toExternalForm());
            currentStage.setScene(boardingScene);
            currentStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
 

