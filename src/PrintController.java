package airvista;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import javafx.scene.Node;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
public class PrintController {

    @FXML
    private Label DestinationLabel;
    @FXML
    private Label flightnamelabel;
    @FXML
    private Label passengerlabel;
    @FXML
    private Label schedulelabel;
    @FXML
    private Label boardingIDlabel;
    @FXML
    private Label flightnamelabel2;
    @FXML
    private Label passengerlabel2;
    @FXML
    private Label schedulelabel2;
    @FXML
    private Label gatelabel;

    /**
     * Set the destination label text.
     * @param destination The destination to display.
     */
    //int boardingID
    public void setboardingdetails(String destination,String flightname,String passenger,String schedule) {
        if (destination == null || destination.isEmpty()) {
            DestinationLabel.setText("No Destination provided");
        } else {
            DestinationLabel.setText(destination);
        }

        if (flightname == null || flightname.isEmpty()) {
            flightnamelabel.setText("No flight name provided");
        } else {
            flightnamelabel.setText(flightname);
        }

        if (passenger == null || passenger.isEmpty()) {
            passengerlabel.setText("No passenger name provided");
        } else {
            passengerlabel.setText(passenger);
        }

        if (schedule == null || schedule.isEmpty()) {
            schedulelabel.setText("No schedule name provided");
        } else {
            schedulelabel.setText(schedule);
        }
        if (flightname == null || flightname.isEmpty()) {
            flightnamelabel2.setText("No flight name provided");
        } else {
            flightnamelabel2.setText(flightname);
        }
        if (passenger == null || passenger.isEmpty()) {
            passengerlabel2.setText("No passenger name provided");
        } else {
            passengerlabel2.setText(passenger);
        }
        if (schedule == null || schedule.isEmpty()) {
            schedulelabel2.setText("No schedule name provided");
        } else {
            schedulelabel2.setText(schedule);
        }
        //boardingIDlabel.setText(String.valueOf(boardingID));

        }
@FXML
private AnchorPane boardingPane;

public void captureBoardingPass(Node node, String outputPath) {
    try {
        // Capture the node (pane or scene) as an image
        WritableImage image = node.snapshot(null, null);
        
        // Save the image to a file
        File file = new File(outputPath);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
public void generatePDFAlternative(String pdfPath, String imagePath) {
    try {
        // Verify if the image file exists
        File imageFile = new File(imagePath);
        if (!imageFile.exists() || !imageFile.isFile()) {
            System.out.println("Error: Image file not found at " + imagePath);
            return;
        }

        // Read the image as a BufferedImage
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (bufferedImage == null) {
            System.out.println("Error: Unable to read image or unsupported format.");
            return;
        }

        // Convert the BufferedImage to byte array
        ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", imageOutputStream); // Assuming PNG format
        byte[] imageBytes = imageOutputStream.toByteArray();

        // Create a PdfDocument and add the image
        PdfWriter writer = new PdfWriter(pdfPath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add the image to the PDF
        Image pdfImage = new Image(com.itextpdf.io.image.ImageDataFactory.create(imageBytes));
        document.add(pdfImage);

        // Close the document
        document.close();

        System.out.println("PDF created successfully at " + pdfPath);
    } catch (IOException e) {
        System.out.println("Error reading the image: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        System.out.println("Error generating the PDF: " + e.getMessage());
        e.printStackTrace();
    }
}

        @FXML
private void saveBoardingPassAsPDF(ActionEvent event) {
    try {
        // Path to save the image and PDF
        String imagePath = "C:\\Users\\user\\Documents\\NetBeansProjects\\Airvista\\src\\airvista\\image.png";
        String pdfPath = "C:\\Users\\user\\Documents\\NetBeansProjects\\Airvista\\src\\airvista\\image.pdf";

        // Capture the boarding pass pane
        captureBoardingPass(boardingPane, imagePath);

        // Generate PDF from the captured image
        generatePDFAlternative(pdfPath, imagePath);

        System.out.println("Boarding pass saved as PDF at: " + pdfPath);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}



