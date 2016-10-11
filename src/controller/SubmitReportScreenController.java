package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Created by Sakhi on 10/10/16.
 */
public class SubmitReportScreenController {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField hourField;

    @FXML
    private RadioButton amButton;

    @FXML
    private RadioButton pmButton;

    @FXML
    private TextField locationField;

    @FXML
    private RadioButton bottledButton;

    @FXML
    private RadioButton wellButton;

    @FXML
    private RadioButton streamButton;

    @FXML
    private RadioButton lakeButton;

    @FXML
    private RadioButton springButton;

    @FXML
    private RadioButton otherButton;

    @FXML
    private RadioButton wasteButton;

    @FXML
    private RadioButton treatClearButton;

    @FXML
    private RadioButton treatMuddyButton;

    @FXML
    private RadioButton potableButton;

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
        String name = nameField.getText();
        LocalDate date = dateField.getValue();

        RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
        String time = selectedTime.getText();

        String location = locationField.getText();

        RadioButton selectedType = (RadioButton) typeGroup.getSelectedToggle();
        String type = selectedType.getText();

        RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
        String condition = selectedCondition.getText();

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
