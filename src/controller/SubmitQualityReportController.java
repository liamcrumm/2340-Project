package controller;

import fxapp.MainFXApplication;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.AccountsManager;
import model.QualityReport;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    private ToggleGroup timeGroup = new ToggleGroup();
    private ToggleGroup conditionGroup = new ToggleGroup();

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
        nameField.setText(accounts.getCurrentUsername());

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
                .or(longitudeField.textProperty().isEmpty())
                .or(virusField.textProperty().isEmpty())
                .or(contaminantField.textProperty().isEmpty());
        saveButton.disableProperty().bind(booleanBind);

        // force the field to be numeric (and colon) only
        timeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d:")) {
                    timeField.setText(newValue.replaceAll("[^\\d:]", ""));
                }
            }
        });

        // force the field to be numeric (and dot) only
        latitudeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d.")) {
                    latitudeField.setText(newValue.replaceAll("[^\\d.-]", ""));
                }
            }
        });

        // force the field to be numeric (and dot) only
        longitudeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d-")) {
                    longitudeField.setText(newValue.replaceAll("[^\\d.-]", ""));
                }
            }
        });

        // force the field to be numeric only
        virusField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    virusField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // force the field to be numeric only
        contaminantField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d")) {
                    contaminantField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
        //First validate the data to insure it is at least reasonable
        if (isInputValid()) {
            int repNum = accounts.getQualityReportsList().size();

            String username = accounts.getCurrentUsername();
            Date date = Date.from(dateField.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            RadioButton selectedTime = (RadioButton) timeGroup.getSelectedToggle();
            String time = timeField.getText() + " " + selectedTime.getText();

            double latitude = Double.parseDouble(latitudeField.getText());
            double longitude = Double.parseDouble(longitudeField.getText());

            RadioButton selectedCondition = (RadioButton) conditionGroup.getSelectedToggle();
            String condition = selectedCondition.getText();

            int virus = Integer.parseInt(virusField.getText());
            int contaminant = Integer.parseInt(contaminantField.getText());

            QualityReport report = new QualityReport(repNum, username, date, time, latitude, longitude, condition, virus, contaminant);
            accounts.addQualityReport(report);
            mainApplication.showAccountScreen();

            alert.setTitle("Submitting Quality Report");
            alert.setHeaderText("Submitting Your Quality Report");
            alert.setContentText("Your report has been successfully submitted.");

            alert.showAndWait();
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (!timeField.getText().matches("^(1[012]|[1-9]):[0-5][0-9](\\\\s)?")) {
            errorMessage += "No valid time entered!\n";
        }
        if (isDouble(latitudeField.getText())) {
            double latitude = Double.parseDouble(latitudeField.getText());
            if (latitude > 90 || latitude < -90) {
                errorMessage += "No valid latitude entered!\n";
            }
        } else {
            errorMessage += "No valid latitude entered!\n";
        }
        if (isDouble(longitudeField.getText())) {
            double longitude = Double.parseDouble(longitudeField.getText());
            if (longitude > 180 || longitude < -180) {
                errorMessage += "No valid longitude entered!\n";
            }
        } else {
            errorMessage += "No valid longitude entered!\n";
        }

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad dataW
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    /**
     * Determines whether the latitude/longitude values are doubles that can
     * be parsed
     *
     * @param str - the string the contains either the lat or longitude
     * @return whether the string is in the format of a double or not
     */
    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Button handler for "Cancel" button
     */
    @FXML public void handleCancelPressed() {
        mainApplication.showAccountScreen();

        alert.setTitle("Cancelling Quality Report");
        alert.setHeaderText("Cancelling Quality Report Submission");
        alert.setContentText("You have been successfully cancelled the quality report submission.");

        alert.showAndWait();
    }

}
