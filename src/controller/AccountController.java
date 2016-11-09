package controller;

import fxapp.MainFXApplication;
import model.AccountsManager;
import model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * Created by Sakhi on 9/21/16.
 */
public class AccountController {

    @FXML
    private Button submitQualityButton;

    @FXML
    private Button viewQualityButton;

    @FXML
    private Button viewHistoryGraphButton;

    private boolean qualityAccess;

    private MainFXApplication mainApplication;

    private AccountsManager accounts;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        /** now we communicate with the model to get the user*/
        accounts = LoginController.accounts;
        User user = accounts.getUser();
        String userType = user.getAccountType();
        if (userType.compareTo("Manager") == 0) {
            viewHistoryGraphButton.setDisable(false);
        } else {
            viewHistoryGraphButton.setDisable(true);
        }
        if (userType.compareTo("Worker") == 0 || userType.compareTo("Manager") == 0) {
            submitQualityButton.setDisable(false);
            viewQualityButton.setDisable(false);
        } else {
            submitQualityButton.setDisable(true);
            viewQualityButton.setDisable(true);
        }
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
    public void viewWaterPressed() { mainApplication.showMapScreen();
    }

    /**
     * Button handler for Submit Water Report button
     */
    @FXML
    public void reportWaterPressed() {
        mainApplication.showSubmitWaterReportScreen();
    }

    /**
     * Button handler for View Water Reports button
     */
    @FXML
    public void submittedReportsPressed() { mainApplication.showViewWaterReportScreen(); }

    /**
     * Button handler for view profile button
     */
    @FXML
    public void viewProfilePressed() {
        mainApplication.showViewProfileScreen();
    }

    /**
     * Button handler for Submit Quality Report button
     */
    @FXML
    public void submitQualityPressed() {
        if (submitQualityButton.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unauthorized");
            alert.setHeaderText("Unauthorized Access");
            alert.setContentText("You must have Worker or Manager privileges" +
                    " in order to access Submit Quality Report features.");

            alert.showAndWait();
        } else {
            mainApplication.showSubmitQualityScreen();
        }
    }

    /**
     * Button handler for View Quality Reports button
     */
    @FXML
    public void viewQualityPressed() {
        if (viewQualityButton.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unauthorized");
            alert.setHeaderText("Unauthorized Access");
            alert.setContentText("You must have Worker or Manager privileges" +
                    " in order to access View Quality Reports features.");

            alert.showAndWait();
        } else {
            mainApplication.showViewQualityScreen();
        }
    }
    /**
     * Button handler for View History Graph button
     */
    @FXML
    public void viewHistoryPressed() {
        if (viewHistoryGraphButton.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unauthorized");
            alert.setHeaderText("Unauthorized Access");
            alert.setContentText("You must have Manager privileges" +
                    " in order to access View History Graph.");

            alert.showAndWait();
        } else {
            mainApplication.showHistoryGraphScreen();
        }
    }
}

