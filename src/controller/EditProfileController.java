package controller;

import fxapp.MainFXApplication;
import model.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by Kimberly Burke on 9/29/2016.
 */
public class EditProfileController {

    /*  **********************
       References to the FXML widgets in the .fxml file
    */
    @FXML
    private TextField nameField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextArea bioField;

    /** a link back to the main application class */
    @FXML
    private MainFXApplication mainApplication;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication  a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Button handler for "Save" button
     */
    @FXML
    public void handleSavePressed() {
        // TODO

        String name = nameField.getText();
        String title  = titleField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String bio = bioField.getText();

        Profile profile = new Profile(name, title, email, phone, address, bio);

        //mainApplication.showAccountScreen(); change to profile screen
        // TODO Change mainApplication to return to previous screen.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Edit Profile Successful");
        alert.setHeaderText("Saving Changes");
        alert.setContentText("You have been successfully saved the changes to your profile.");

        alert.showAndWait();
    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML
    public void handleCancelPressed() {
        //mainApplication.showAccountScreen(); Change to profile screen
        // TODO Change mainApplication to return to previous screen.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancelling Edit Profile");
        alert.setHeaderText("Cancelling Profile Changes");
        alert.setContentText("You have been successfully cancelled the changes made to your profile.");

        alert.showAndWait();
    }
}
