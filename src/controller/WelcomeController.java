package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Kimberly Burke on 9/19/2016.
 *
 * Controller for the welcome screen. Handles the button clicks for registration and login.
 *
 * Some code reused from M3 assignment.
 */
public class WelcomeController {

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    private Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML private void initialize() {
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
     * Button handler for register
     * Currently not implemented
     */@FXML
    public void registerPressed() {
        boolean submitClicked = mainApplication.showRegistrationScreen();
        // Show the error message if bad data
        if (!submitClicked) {
            alert.initOwner(mainApplication.getMainScreen());
            alert.setTitle("Registration Failed!");
            alert.setHeaderText("Registration was unsuccessful");
            alert.setContentText("The registration dialogue could not load.");

            alert.showAndWait();
        }

    }

    /**
     * Button handler for add student
     */
    @FXML public void loginPressed() {

        boolean okClicked = mainApplication.showLoginScreen();
        if (!okClicked) {
            alert.initOwner(mainApplication.getMainScreen());
            alert.setTitle("Login Failed!");
            alert.setHeaderText("Login was unsuccessful");
            alert.setContentText("The login dialogue could not load.");

            alert.showAndWait();
        }
    }
}
