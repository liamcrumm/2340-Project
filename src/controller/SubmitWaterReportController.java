package controller;

import fxapp.MainFXApplication;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AccountsManager;
import model.WaterReport;
import model.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Sakhi on 10/10/16.
 */
public class SubmitWaterReportController {

    @FXML private TextField nameField;
    @FXML private DatePicker dateField;
    @FXML private TextField timeField; // time field
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

    private AccountsManager accounts = LoginController.accounts;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML private void initialize() {
        dateField.setValue(LocalDate.now()); // set date to current date
        nameField.setText(accounts.getCurrentUsername()); // set name to current user

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
                .or(timeField.textProperty().isEmpty())
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
    @FXML public void handleSavePressed() {
        int repNum = accounts.getWaterReportsList().size();

        String username = accounts.getCurrentUsername();
        Date date = Date.from(dateField.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
        String time = timeField.getText() + " " + selectedTime.getText();

        double latitude = Double.parseDouble(latitudeField.getText());
        double longitudeD = Double.parseDouble(longitudeField.getText());

        RadioButton selectedType = (RadioButton) typeGroup.getSelectedToggle();
        String type = selectedType.getText();

        RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
        String condition = selectedCondition.getText();

        WaterReport report = new WaterReport(repNum, username, date, time, latitude, longitudeD, type, condition);
        accounts.addWaterReport(report);

        mainApplication.showAccountScreen();

        alert.setTitle("Submitting Water Report");
        alert.setHeaderText("Submitting Your Water Report");
        alert.setContentText("Your report has been successfully submitted.");

        alert.showAndWait();

    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML public void handleCancelPressed() {
        mainApplication.showAccountScreen();

        alert.setTitle("Cancelling Water Report");
        alert.setHeaderText("Cancelling Water Report Submission");
        alert.setContentText("You have been successfully cancelled the water report submission.");

        alert.showAndWait();

    }
}
