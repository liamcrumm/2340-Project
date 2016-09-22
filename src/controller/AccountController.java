package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by Sakhi on 9/21/16.
 */
public class AccountController {

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
     * Button handler for log out butotn
     */
    @FXML
    public void logoutPressed() {
        Stage main = mainApplication.getMainScreen();
        mainApplication.showWelcome(main);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout Successful");
        alert.setHeaderText("Logout Successful");
        alert.setContentText("You have been successfully logged out");

        alert.showAndWait();

    }

    /**
     * Button handler for account settings button
     */
    @FXML
    public void accountSettingsPressed() {

    }
}

