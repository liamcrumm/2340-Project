package controller;/**
 * Created by Catherine on 10/1/2016.
 */

import fxapp.MainFXApplication;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    /** a link back to the main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

    @FXML
    private void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("User");
        types.add("Worker");
        types.add("Admin");
        types.add("Manager");
        accountType.setItems(types);
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
    public void start(Stage primaryStage) {

    }
    @FXML
    public void handleSubmitPressed() {
        AccountsManager c = new AccountsManager();
        c.getUserList().add(new User(usernameField.getText(),passwordField.getText(),new Profile(nameField.getText(),"","","","","")));
        _dialogStage.close();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApplication.getMainScreen());
        _okClicked = true;
    }
    @FXML
    public void handleCancelPressed() {
        _dialogStage.close();
        _okClicked = true;
    }
}
