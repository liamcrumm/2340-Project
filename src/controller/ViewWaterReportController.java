package controller;

import fxapp.MainFXApplication;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountsManager;
import model.WaterReport;
import model.User;

/**
 * Created by Andrew on 10/11/2016.
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

    /** Arraylists for users and reports*/
    private ArrayList<User> userList;
    private ArrayList<WaterReport> userReportsMaster;
    private ArrayList<WaterReport> userReports;

    AccountsManager account = LoginController.accounts;
    User user = account.getUser();

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML
    private void initialize() {
        /*Populates the table, String inside of PropertyValueFactory argument refers to the method name of the Setter class
          within WaterReport, e.g. "ReportNumber" must match getReportNumber in model.WaterReport. "ReportNumber" is not
          the name of the column. That has already been defined manually in its corresponding .fxml file.
        */
        reportNumCol.setCellValueFactory(new PropertyValueFactory<WaterReport, Integer>("ReportNumber"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("ReportUsername"));
        dateCol.setCellValueFactory(new PropertyValueFactory<WaterReport, LocalDate>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("Time"));
        latCol.setCellValueFactory(new PropertyValueFactory<WaterReport, Double>("Lat"));
        longCol.setCellValueFactory(new PropertyValueFactory<WaterReport, Double>("Long"));
        typeCol.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("Type"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("Condition"));
        userReports = user.getUserWaterReports();
        userList = account.getUserList();
        userList.forEach(u -> {
            if (u.getUserWaterReports() != null) {
                if (userReportsMaster == null) {
                    userReportsMaster = u.getUserWaterReports();
                } else {
                    u.getUserWaterReports().forEach(r -> {
                        if (!userReportsMaster.contains(r)) {
                            userReportsMaster.add(r);
                        }
                    });
                }
            }
        });
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
    public void refresh() {
        if(userReportsMaster != null) {
            reportTable.getItems().setAll(userReportsMaster);
        }
    }

    /**
     * Button handler for "Delete Selected Report" button
     */
    @FXML
    public void handleDeleteSelectedReport() {
        WaterReport selectedReport = reportTable.getSelectionModel().getSelectedItem();
        if (selectedReport == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Report Selected");
            alert.setContentText("You must first a report in order to delete it.");
            alert.showAndWait();
        } else if (userReports == null || !userReports.contains(selectedReport)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot Delete Report");
            alert.setContentText("You lack the user privileges to delete reports of other users.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRM DELETE REPORT");
            alert.setHeaderText("Are you sure you want to delete the selected report?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    userReports.remove(selectedReport);
                    userReportsMaster.remove(selectedReport);
                    user.deleteWaterReport(selectedReport);
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
