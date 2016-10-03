package controller;/**
 * Created by Catherine on 10/1/2016.
 */

import fxapp.MainFXApplication;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController extends Application {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    /** a link back to the main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

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

    @Override
    public void start(Stage primaryStage) {

    }
    @FXML
    public void handleSubmitPressed() {
        LoginController.usernames.put(usernameField.getText(),passwordField.getText());
    }
    @FXML
    public void handleCancelPressed() {
        _dialogStage.close();
    }
}
