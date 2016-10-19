package controller;

import fxapp.MainFXApplication;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountsManager;
import model.Report;
import model.User;

/**
 * Created by Andrew on 10/11/2016.
 */
public class ViewReportController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    /** FXML Widgets, column entries on the table view*/
    @FXML private TableView<Report> reportTable;
    @FXML private TableColumn<Report, Integer> reportNumCol;
    @FXML private TableColumn<Report, String> usernameCol;
    @FXML private TableColumn<Report, LocalDate> dateCol;
    @FXML private TableColumn<Report, String> timeCol;
    @FXML private TableColumn<Report, Double> latCol;
    @FXML private TableColumn<Report, Double> longCol;
    @FXML private TableColumn<Report, String> typeCol;
    @FXML private TableColumn<Report, String> conditionCol;

    /** Arraylists for users and reports*/
    private ArrayList<User> userList;
    private ArrayList<Report> userReportsMaster;
    private ArrayList<Report> userReports;

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML
    private void initialize() {
        reportNumCol.setCellValueFactory(new PropertyValueFactory<Report, Integer>("ReportNumber"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Report, String>("ReportUsername"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Report, LocalDate>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Time"));
        latCol.setCellValueFactory(new PropertyValueFactory<Report, Double>("Lat"));
        longCol.setCellValueFactory(new PropertyValueFactory<Report, Double>("Long"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Type"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Condition"));
        AccountsManager account = LoginController.accounts;
        User user = account.getUser();
        userReports = user.getUserReports();
        userList = account.getUserList();
        userList.forEach(u -> {
            if (u.getUserReports() != null) {
                if (userReportsMaster == null) {
                    userReportsMaster = u.getUserReports();
                } else {
                    u.getUserReports().forEach(r -> {
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
        Report selectedReport = reportTable.getSelectionModel().getSelectedItem();
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
