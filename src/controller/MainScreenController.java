package controller;

import fxapp.MainFXApplication;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

/**
 * Created by Team 0xF on 9/18/16.
 *
 * Some code reused from M3 assignment.
 *
 * The controller for the root/main window
 *
 */
public class MainScreenController {

    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);

    }

    /**
     * About menu item event handler
     */
    @FXML
    private void handleAboutMenu() {
        alert.setTitle("Clean Water Crowdsourcing");
        alert.setHeaderText("About");
        alert.setContentText("CS 2340 Fall 2016 project build by Team 0xF.");

        alert.showAndWait();

    }

}
