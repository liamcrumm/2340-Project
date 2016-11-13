package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.AccountsManager;
import model.Profile;
import model.User;

/**
 * Created by Andrew Hu on 10/2/2016.
 * Controller for the view profile screen. This class pulls the users current profile data and displays it.
 */
public class ViewProfileController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** FXML Widgets*/
    @FXML private TextField nameField;
    @FXML private TextField titleField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea bioField;

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML private void initialize() {
        AccountsManager accounts = LoginController.accounts;
        User user = accounts.getUser();
        if(user.getProfile() != null) {
            Profile prof = user.getProfile();
            nameField.setText(prof.getName());
            titleField.setText(prof.getTitle());
            emailField.setText(prof.getEmail());
            phoneField.setText(prof.getPhone());
            addressField.setText(prof.getAddress());
            bioField.setText(prof.getBio());
        }
    }

    /**
     * Setter for main application so we can change values there
     * @param mainFXApplication Link to main application class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Button handler for "Edit Profile" button
     */
    @FXML public void handleEditProfilePressed() {
        mainApplication.showEditProfileScreen();
    }

    /**
     * Button handler for "Back" button
     */
    @FXML public void handleBackPressed() {
        mainApplication.showAccountScreen();
    }
}
