package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AccountsManager;
import model.Report;
import model.User;

import java.time.LocalDate;

/**
 * Created by Sakhi on 10/10/16.
 */
public class SubmitReportScreenController {

    @FXML private TextField nameField;
    @FXML private DatePicker dateField;
    @FXML private TextField hourField;
    @FXML private RadioButton amButton;
    @FXML private RadioButton pmButton;
    @FXML private TextField latitudeField;
    @FXML private TextField longitudeField;
    @FXML private RadioButton bottledButton;
    @FXML private RadioButton wellButton;
    @FXML private RadioButton streamButton;
    @FXML private RadioButton lakeButton;
    @FXML private RadioButton springButton;
    @FXML private RadioButton otherButton;
    @FXML private RadioButton wasteButton;
    @FXML private RadioButton treatClearButton;
    @FXML private RadioButton treatMuddyButton;
    @FXML private RadioButton potableButton;

    ToggleGroup timeGroup = new ToggleGroup();
    ToggleGroup typeGroup = new ToggleGroup();
    ToggleGroup conditionGroup = new ToggleGroup();

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        amButton.setToggleGroup(timeGroup);
        pmButton.setToggleGroup(timeGroup);

        bottledButton.setToggleGroup(typeGroup);
        wellButton.setToggleGroup(typeGroup);
        streamButton.setToggleGroup(typeGroup);
        lakeButton.setToggleGroup(typeGroup);
        springButton.setToggleGroup(typeGroup);
        otherButton.setToggleGroup(typeGroup);

        wasteButton.setToggleGroup(conditionGroup);
        treatClearButton.setToggleGroup(conditionGroup);
        treatMuddyButton.setToggleGroup(conditionGroup);
        potableButton.setToggleGroup(conditionGroup);

        AccountsManager account = LoginController.accounts;
        nameField.setText(account.getCurrentUsername());
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
    @FXML
    public void handleSavePressed() {
        AccountsManager accounts = LoginController.accounts;

        String username = accounts.getCurrentUsername();
        LocalDate date = dateField.getValue();

        RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
        String time = hourField.getText() + " " + selectedTime.getText();

        String location = latitudeField.getText() + " " + longitudeField.getText();

        RadioButton selectedType = (RadioButton) typeGroup.getSelectedToggle();
        String type = selectedType.getText();

        RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
        String condition = selectedCondition.getText();

        Report report = new Report(username, date, time, location, type, condition);
        accounts.addReport(report);
        int repNum = accounts.getReportsList().size();
        report.setReportNumber(repNum);
        User current = accounts.getUser();
        current.addReport(report);

        mainApplication.showAccountScreen();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submitting Water Report");
        alert.setHeaderText("Submitting Your Water Report");
        alert.setContentText("Your report has been suscessfully submitted.");

        alert.showAndWait();

    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML
    public void handleCancelPressed() {
        mainApplication.showAccountScreen();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancelling Water Report");
        alert.setHeaderText("Cancelling Water Report Submission");
        alert.setContentText("You have been successfully cancelled the water report submission.");

        alert.showAndWait();

    }
}
