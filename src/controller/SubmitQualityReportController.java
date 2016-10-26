package controller;

import fxapp.MainFXApplication;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AccountsManager;
import model.User;
import model.QualityReport;

import java.time.LocalDate;

/**
 * Created by Kimberly Burke on 10/25/2016.
 */
public class SubmitQualityReportController {

    @FXML private TextField nameField;
    @FXML private DatePicker dateField;
    @FXML private TextField timeField; // time field
    @FXML private RadioButton amButton;
    @FXML private RadioButton pmButton;
    @FXML private TextField latitudeField;
    @FXML private TextField longitudeField;
    @FXML private TextField virusField;
    @FXML private TextField contaminantField;
    @FXML private Button saveButton;

    @FXML private RadioButton safeButton;
    @FXML private RadioButton treatableButton;
    @FXML private RadioButton unsafeButton;

    ToggleGroup timeGroup = new ToggleGroup();
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
        nameField.setText(account.getCurrentUsername());

        amButton.setToggleGroup(timeGroup);
        pmButton.setToggleGroup(timeGroup);
        amButton.setSelected(true); // set default selection

        safeButton.setToggleGroup(conditionGroup);
        treatableButton.setToggleGroup(conditionGroup);
        unsafeButton.setToggleGroup(conditionGroup);
        safeButton.setSelected(true); // set default selection

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
    @FXML
    public void handleSavePressed() {
        int repNum = account.getWaterReportsList().size();

        String username = account.getCurrentUsername();
        LocalDate date = dateField.getValue();

        RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
        String time = timeField.getText() + " " + selectedTime.getText();

        double latitude = Double.parseDouble(latitudeField.getText());
        double longitudeD = Double.parseDouble(longitudeField.getText());

        RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
        String condition = selectedCondition.getText();

        int virus = Integer.parseInt(virusField.getText());
        int contaminant = Integer.parseInt(contaminantField.getText());

        QualityReport report = new QualityReport(repNum, username, date, time, latitude, longitudeD, condition, virus, contaminant);
        account.addQualityReport(report);
        User current = account.getUser();
        current.addQualityReport(report);
        mainApplication.showAccountScreen();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submitting Quality Report");
        alert.setHeaderText("Submitting Your Quality Report");
        alert.setContentText("Your report has been successfully submitted.");

        alert.showAndWait();
    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML
    public void handleCancelPressed() {
        mainApplication.showAccountScreen();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancelling Quality Report");
        alert.setHeaderText("Cancelling Quality Report Submission");
        alert.setContentText("You have been successfully cancelled the quality report submission.");

        alert.showAndWait();
    }

}
