package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountsManager;
import model.QualityReport;
import model.Report;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Kimberly Burke on 10/25/2016.
 */
public class ViewQualityReportController {

    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    /** FXML widgets from the screen table view*/
    @FXML private TableView<QualityReport> qualityReportTable;
    @FXML private TableColumn<QualityReport, Integer> reportNumCol;
    @FXML private TableColumn<QualityReport, String> usernameCol;
    @FXML private TableColumn<QualityReport, LocalDate> dateCol;
    @FXML private TableColumn<QualityReport, String> timeCol;
    @FXML private TableColumn<QualityReport, Double> latCol;
    @FXML private TableColumn<QualityReport, Double> longCol;
    @FXML private TableColumn<QualityReport, String> conditionCol;
    @FXML private TableColumn<QualityReport, Integer> virusCol;
    @FXML private TableColumn<QualityReport, Integer> contaminantCol;

    private AccountsManager accounts = LoginController.accounts;
    private Alert alert;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML private void initialize() {
        reportNumCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("ReportNumber"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("ReportUsername"));
        dateCol.setCellValueFactory(new PropertyValueFactory<QualityReport, LocalDate>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("Time"));
        latCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Double>("Lat"));
        longCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Double>("Long"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("Condition"));
        virusCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("Virus"));
        contaminantCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("Contaminant"));
        refresh();
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
     * Refreshes and displays the report table
     */
    public void refresh() {
        qualityReportTable.getItems().clear();
        accounts.getQualityReportsList().forEach(r -> {
                if (!qualityReportTable.getItems().contains(r)) {
                    qualityReportTable.getItems().add(r);
                }
        });
    }

    /**
     * Button handler for "Back" button
     */
    @FXML public void handleBackPressed() {
        mainApplication.showAccountScreen();
    }

    /**
     * Button handler for "Delete Selected Report" button
     */
    public void handleDeleteSelectedReport() {
        QualityReport selectedReport = qualityReportTable.getSelectionModel().getSelectedItem();
        if (selectedReport == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Report Selected");
            alert.setContentText("You must first select a report in order to delete it.");
            alert.showAndWait();
        } else if (!accounts.getCurrentUsername().equals(selectedReport.getReportUsername())
                &&!accounts.getUser().getAccountType().equals("Manager")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You do not have permission to modify that report");
            alert.setContentText("You can only delete your own reports.");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRM DELETE REPORT");
            alert.setHeaderText("Are you sure you want to delete the selected report?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    accounts.removeQualityReport(selectedReport);
                    refresh();
                }
            });
        }
    }
}
