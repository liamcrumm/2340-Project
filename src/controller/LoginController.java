package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.AccountsManager;

import java.util.HashMap;

/**
 * Created by Kimberly Burke on 9/19/2016.
 *
 * Some code reused from M3 assignment.
 */
public class LoginController {

    /*  **********************
       References to the FXML widgets in the .fxml file
    */
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

    /** variable to store usernames/passwords of users **/
    public HashMap<String,String> usernames = new HashMap<>();

    /** a link back to the main application class */
    @FXML
    private MainFXApplication mainApplication;

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        //Initial hardcoded username/password to be changed in future milestones
        usernames.put("user","pass");
    }

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication  a reference (link) to our main class
     */
    @FXML
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return  true if the user clicked the OK button
     */
    public boolean isOkClicked() {
        return _okClicked;
    }

    /**
     * Called when the user clicks "login".
     */
    @FXML
    private void handleLoginPressed() {
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            //checks to make sure username and password are valid
            String username = usernameField.getCharacters().toString();
            String password = passwordField.getCharacters().toString();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Login Failed!");
            alert.setHeaderText("Incorrect Login Credentials");
            alert.setContentText("The username/password you entered was incorrect.");
            boolean successful = false;
            if(usernames.containsKey(username)) {
                if(usernames.get(username).equals(password)) {
                    mainApplication.showAccountScreen();
                    successful = true;
                    AccountsManager accounts = new AccountsManager();
                    accounts.setCurrentUser(username);
                } else {
                    alert.showAndWait();
                }
            } else {
                alert.showAndWait();
            }


            _okClicked = true;
            if(successful) {
                _dialogStage.close();
            }
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancelPressed() {
        _dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */

    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid username entered!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password entered!\n";
        }


        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
