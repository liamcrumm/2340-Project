package controller;

import fxapp.MainFXApplication;
import model.AccountsManager;
import model.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.User;

/**
 * Created by Kimberly Burke on 9/29/2016.
 * This controller handles the functionality of the edit profile screen. When a user edits their profile, the controller
 * makes sure that the data is saved properly.
 */
public class EditProfileController {

    /*  **********************
       References to the FXML widgets in the .fxml file
    */
    @FXML private TextField nameField;
    @FXML private TextField titleField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea bioField;

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    private AccountsManager accounts = LoginController.accounts;
    private User user = accounts.getUser();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML private void initialize() {
        Profile prof = user.getProfile();
        if(prof != null) {
            if(prof.getName() != null) {
                nameField.setText(prof.getName());
            }
            if(prof.getTitle() != null) {
                titleField.setText(prof.getTitle());
            }
            if(prof.getEmail() != null) {
                emailField.setText(prof.getEmail());
            }
            if(prof.getPhone() != null) {
                phoneField.setText(prof.getPhone());
            }
            if(prof.getAddress() != null) {
                addressField.setText(prof.getAddress());
            }
            if(prof.getBio() != null) {
                bioField.setText(prof.getBio());
            }

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
     * Button handler for "Save" button
     */
    @FXML public void handleSavePressed() {

        String name = nameField.getText();
        String title  = titleField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String bio = bioField.getText();


        Profile profile = new Profile(name, title, email, phone, address, bio);
        user.setProfile(profile);
        accounts.updateUser(user);
        mainApplication.showViewProfileScreen();
        alert.setTitle("Edit Profile Successful");
        alert.setHeaderText("Saving Changes");
        alert.setContentText("You have successfully saved the changes to your profile.");

        alert.showAndWait();
    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML public void handleCancelPressed() {
        mainApplication.showViewProfileScreen();

        alert.setTitle("Cancelling Edit Profile");
        alert.setHeaderText("Cancelling Profile Changes");
        alert.setContentText("You have successfully cancelled the changes made to your profile.");

        alert.showAndWait();
    }
}
