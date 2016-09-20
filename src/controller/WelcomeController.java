package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Kimberly Burke on 9/19/2016.
 *
 * Some code reused from M3 assignment.
 */
public class WelcomeController {

    /** a link back to the main application class */
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
     * Button handler for add course
     * Currently not implemented
     */
    @FXML
    public void registerPressed() {
        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        alert.setTitle("Unimplemented");
        alert.setHeaderText("Unimplemented Feature");
        alert.setContentText("Sorry, Registering is not yet implemented!");

        alert.showAndWait();

    }

    /**
     * Button handler for add student
     */
    @FXML
    public void loginPressed() {

        boolean okClicked = mainApplication.showLoginScreen();
        if (okClicked) {
            // refer to M3 on how to implement; creates an alert that login was
            // unsuccessful.
        }
    }
}
