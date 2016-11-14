package controller;

import fxapp.MainFXApplication;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountsManager;
import model.WaterReport;

/**
 * Created by Andrew on 10/11/2016.
 * Controller for the view water reports screen. This class pulls the data from water reports and displays it
 * on the screen. This class also handles the process of deleting a report.
 */
public class ViewWaterReportController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** FXML Widgets, column entries on the table view*/
    @FXML private TableView<WaterReport> reportTable;
    @FXML private TableColumn<WaterReport, Integer> reportNumCol;
    @FXML private TableColumn<WaterReport, String> usernameCol;
    @FXML private TableColumn<WaterReport, LocalDate> dateCol;
    @FXML private TableColumn<WaterReport, String> timeCol;
    @FXML private TableColumn<WaterReport, Double> latCol;
    @FXML private TableColumn<WaterReport, Double> longCol;
    @FXML private TableColumn<WaterReport, String> typeCol;
    @FXML private TableColumn<WaterReport, String> conditionCol;

    private AccountsManager accounts = LoginController.accounts;

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML private void initialize() {
        /*Populates the table, String inside of PropertyValueFactory argument refers to the method name of the Setter class
          within WaterReport, e.g. "ReportNumber" must match getReportNumber in model.WaterReport. "ReportNumber" is not
          the name of the column. That has already been defined manually in its corresponding .fxml file.
        */
        reportNumCol.setCellValueFactory(new PropertyValueFactory<>("ReportNumber"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("ReportUsername"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        latCol.setCellValueFactory(new PropertyValueFactory<>("Lat"));
        longCol.setCellValueFactory(new PropertyValueFactory<>("Long"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<>("Condition"));
        refresh();
    }

    /**
     * Setter for main application so we can change values there
     * @param mainFXApplication Link to main application class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Refreshes and displays the report table
     */
    private void refresh() {
        reportTable.getItems().clear();
        accounts.getWaterReportsList().forEach(r -> {
                if (!reportTable.getItems().contains(r)) {
                    reportTable.getItems().add(r);
                }

        });
    }

    /**
     * Button handler for "Delete Selected Report" button
     */
    @FXML public void handleDeleteSelectedReport() {
        WaterReport selectedReport = reportTable.getSelectionModel().getSelectedItem();
        Alert alert;
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
                    accounts.removeWaterReport(selectedReport);
                    refresh();
                }
            });
        }
    }

    /**
     * Button handler for "Back" button
     */
    @FXML
    public void handleBackPressed() {
        mainApplication.showAccountScreen();
    }
}
