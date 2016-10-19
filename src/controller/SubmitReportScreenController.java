package controller;

import fxapp.MainFXApplication;
import javafx.beans.binding.BooleanBinding;
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
    @FXML private TextField hourField; // time field
    @FXML private RadioButton amButton;
    @FXML private RadioButton pmButton;
    @FXML private TextField latitudeField;
    @FXML private TextField longitudeField;
    @FXML private Button saveButton;

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

    AccountsManager account = LoginController.accounts;

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML
    private void initialize() {
        dateField.setValue(LocalDate.now()); // set date to current date
        nameField.setText(account.getCurrentUsername()); // set name to current user

        amButton.setToggleGroup(timeGroup);
        pmButton.setToggleGroup(timeGroup);
        amButton.setSelected(true); // set default selection

        bottledButton.setToggleGroup(typeGroup);
        wellButton.setToggleGroup(typeGroup);
        streamButton.setToggleGroup(typeGroup);
        lakeButton.setToggleGroup(typeGroup);
        springButton.setToggleGroup(typeGroup);
        otherButton.setToggleGroup(typeGroup);
        bottledButton.setSelected(true); // set default selection

        wasteButton.setToggleGroup(conditionGroup);
        treatClearButton.setToggleGroup(conditionGroup);
        treatMuddyButton.setToggleGroup(conditionGroup);
        potableButton.setToggleGroup(conditionGroup);
        wasteButton.setSelected(true); // set default selection

        BooleanBinding booleanBind = nameField.textProperty().isEmpty()
                .or(hourField.textProperty().isEmpty())
                .or(latitudeField.textProperty().isEmpty())
                .or(longitudeField.textProperty().isEmpty());
        saveButton.disableProperty().bind(booleanBind);
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
        int repNum = account.getReportsList().size();

        String username = account.getCurrentUsername();
        LocalDate date = dateField.getValue();

        RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
        String time = hourField.getText() + " " + selectedTime.getText();

        //String location = latitudeField.getText() + " " + longitudeField.getText();
        String lat = latitudeField.getText();
        String longitude = longitudeField.getText();

        String location = lat + ", " + longitude;

        double latitude = Double.parseDouble(lat);
        double longitudeD = Double.parseDouble(longitude);

        RadioButton selectedType = (RadioButton) typeGroup.getSelectedToggle();
        String type = selectedType.getText();

        RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
        String condition = selectedCondition.getText();

        Report report = new Report(repNum, username, date, time, location, latitude, longitudeD, type, condition);
        account.addReport(report);
        User current = account.getUser();
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
