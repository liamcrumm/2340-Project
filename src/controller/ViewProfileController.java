package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;

/**
 * Created by Andrew Hu on 10/2/2016.
 */
public class ViewProfileController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Setter for main application so we can change values there
     * @param mainFXApplication Link to main application class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }


}
