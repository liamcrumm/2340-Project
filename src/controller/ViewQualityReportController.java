package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Report;

import java.time.LocalDate;

/**
 * Created by Kimberly Burke on 10/25/2016.
 */
public class ViewQualityReportController {

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    //FXML widgets
    @FXML private TableView<Report> qualityReportTable;
    @FXML private TableColumn<Report, Integer> reportNumCol;
    @FXML private TableColumn<Report, String> usernameCol;
    @FXML private TableColumn<Report, LocalDate> dateCol;
    @FXML private TableColumn<Report, String> timeCol;
    @FXML private TableColumn<Report, Double> latCol;
    @FXML private TableColumn<Report, Double> longCol;
    @FXML private TableColumn<Report, String> typeCol;
    @FXML private TableColumn<Report, String> virusCol;
    @FXML private TableColumn<Report, String> containmentCol;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML
    private void initialize() {}

    /**
     * Setup the main application link so we can call methods there
     *
     * @param mainFXApplication  a reference (link) to our main class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Button handler for "Back" button
     */
    @FXML
    public void handleBackPressed() {
        mainApplication.showAccountScreen();
    }

    /**
     * Button handler for "Delete Selected Report" button
     */
    public void handleDeleteSelectedReport() {
        // TODO
    }
}
