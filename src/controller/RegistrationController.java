package controller;

import fxapp.MainFXApplication;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AccountsManager;
import model.Profile;
import model.User;

import java.util.ArrayList;
import java.util.Collections;

public class RegistrationController extends Application {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox accountType;

    @FXML
    private Button submitButton;

    /** a link back to the main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

    /**
     * Called automatically after load; controls disable property of buttons
     * and populates account types combo box
     */
    @FXML
    private void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("User");
        types.add("Worker");
        types.add("Admin");
        types.add("Manager");
        accountType.setItems(types);
        accountType.setValue(types.get(0));
        BooleanBinding booleanBind = usernameField.textProperty().isEmpty()
                .or(passwordField.textProperty().isEmpty())
                .or(nameField.textProperty().isEmpty());
        submitButton.disableProperty().bind(booleanBind);
    }

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
     * Returns true if the user clicked a button, false otherwise.
     *
     * @return  true if the user clicks a button
     */
    public boolean isOkClicked() {
        return _okClicked;
    }

    @Override
    public void start(Stage primaryStage) {}

    @FXML
    /**
     * Handles registration after verification
     */
    public void handleSubmitPressed() {
        AccountsManager accounts = LoginController.accounts;
        if(isInputValid()) {
            if(uniqueUsername(accounts,usernameField.getText())) {
                accounts.addUser(new User(usernameField.getText(),
                        passwordField.getText(), new Profile(nameField.getText(),
                        null, null, null, null, null), accountType.getValue().toString()));
                _dialogStage.close();
                _okClicked = true;
                mainApplication.showAccountScreen();
                accounts.setCurrentUser(usernameField.getText());
            }
        }
    }
    @FXML
    /**
     * If user selects cancel, go back
     */
    public void handleCancelPressed() {
        _dialogStage.close();
        _okClicked = true;
    }

    /**
     * Returns true if the username/password input is valid
     *
     * @return  true if the username and password fields are valid
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

    /**
     * Returns true if the username selected is unique,otherwise show error
     *
     * @return  true if username selected is unique
     */
    private boolean uniqueUsername(AccountsManager m, String username) {
        m.setCurrentUser(username);
        if(m.getUser() == null) {
            //user being null means the username isn't taken
            return true;
        } else {
            //show error message so that user can choose different username
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Username");
            alert.setHeaderText("Username already taken");
            alert.setContentText("That username is already taken, please choose another");

            alert.showAndWait();
            return false;
        }

    }
}
