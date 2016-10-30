package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AccountsManager;

/**
 * Created by Kimberly Burke on 10/29/2016.
 */
public class HistoryGraphController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    AccountsManager account = LoginController.accounts;

    @FXML private RadioButton virusButton;
    @FXML private RadioButton contaminantButton;
    @FXML private ComboBox locationComboBox;
    @FXML private ComboBox yearComboBox;
    @FXML private Button makeButton;

    ToggleGroup type = new ToggleGroup();

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML
    private void initialize() {
        virusButton.setToggleGroup(type);
        contaminantButton.setToggleGroup(type);
        virusButton.setSelected(true); // set default selection
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

    /**
     * Button handler for "Make Graph" button
     */
    @FXML
    public void handleMakeGraphPressed() {
        boolean successful = false; // needed for closing dialog box correctly
        // TODO: need a check for when location and year entry empty
        _okClicked = true;
        if(successful) {
            _dialogStage.close();
        }
    }

    /**
     * Button handler for "Return to Home Screen" button
     */
    @FXML
    public void handleHomeButtonPressed() {
        _okClicked = true;
        _dialogStage.close();
    }
}
