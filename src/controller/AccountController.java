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
     * Button handler for log out button
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
        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        alert.setTitle("Unimplemented");
        alert.setHeaderText("Unimplemented Feature");
        alert.setContentText("Sorry, Account Settings is not yet implemented!");

        alert.showAndWait();
    }

    /**
     * Button handler for view water sources button
     */
    @FXML
    public void viewWaterPressed() {
        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        alert.setTitle("Unimplemented");
        alert.setHeaderText("Unimplemented Feature");
        alert.setContentText("Sorry, View Water Sources is not yet implemented!");

        alert.showAndWait();
    }

    /**
     * Button handler for account settings button
     */
    @FXML
    public void reportWaterPressed() {
        mainApplication.showSubmitReportScreen();
    }

    /**
     * Button handler for account settings button
     */
    @FXML
    public void submittedReportsPressed() {
        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        alert.setTitle("Unimplemented");
        alert.setHeaderText("Unimplemented Feature");
        alert.setContentText("Sorry, Your Submitted Reports is not yet implemented!");

        alert.showAndWait();
    }

    /**
     * Button handler for view profile button
     */
    @FXML
    public void viewProfilePressed() {
        mainApplication.showViewProfileScreen();
    }
}

