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
 * This controller contains the functionality of the account screen functions in regards to the button click responses.
 */
public class AccountController {

    @FXML private Button submitQualityButton;
    @FXML private Button viewQualityButton;
    @FXML private Button viewHistoryGraphButton;

    private MainFXApplication mainApplication;

    private AccountsManager accounts;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML private void initialize() {
        // now we communicate with the model to get the user
        accounts = LoginController.accounts;
        User user = accounts.getUser();
        String userType = user.getAccountType();
        if (userType.compareTo("Manager") == 0) {
            viewHistoryGraphButton.setVisible(true);
        } else {
            viewHistoryGraphButton.setVisible(false);
        }
        if (userType.compareTo("Worker") == 0 || userType.compareTo("Manager") == 0) {
            submitQualityButton.setVisible(true);
            viewQualityButton.setVisible(true);
        } else {
            submitQualityButton.setVisible(false);
            viewQualityButton.setVisible(false);
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
    @FXML public void logoutPressed() {
        Stage main = mainApplication.getMainScreen();
        mainApplication.showWelcome(main);
        alert.setTitle("Logout Successful");
        alert.setHeaderText("Logout Successful");
        alert.setContentText("You have been successfully logged out");

        alert.showAndWait();



    }

    /**
     * Button handler for view water sources button
     */
    @FXML public void viewWaterPressed() { mainApplication.showMapScreen();
    }

    /**
     * Button handler for Submit Water Report button
     */
    @FXML public void reportWaterPressed() {
        mainApplication.showSubmitWaterReportScreen();
    }

    /**
     * Button handler for View Water Reports button
     */
    @FXML public void submittedReportsPressed() { mainApplication.showViewWaterReportScreen(); }

    /**
     * Button handler for view profile button
     */
    @FXML public void viewProfilePressed() {
        mainApplication.showViewProfileScreen();
    }

    /**
     * Button handler for Submit Quality Report button
     */
    @FXML public void submitQualityPressed() {
        if (submitQualityButton.isDisabled()) {
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
    @FXML public void viewQualityPressed() {
        if (viewQualityButton.isDisabled()) {
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
    @FXML public void viewHistoryPressed() {
        if (viewHistoryGraphButton.isDisabled()) {
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

