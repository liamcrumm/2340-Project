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
    @FXML private TableColumn<Report, String> nameCol;
    @FXML private TableColumn<Report, LocalDate> dateCol;
    @FXML private TableColumn<Report, String> timeCol;
    @FXML private TableColumn<Report, String> locationCol;
    @FXML private TableColumn<Report, String> typeCol;
    @FXML private TableColumn<Report, String> conditionCol;

    ArrayList<User> userList;
    ArrayList<Report> userReportsMaster;
    ArrayList<Report> userReports;

    /**
     * Initializes controller class. This method is automatically called
     * after the constructor and fxml file have been loaded.
     */
    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Report, LocalDate>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Time"));
        locationCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Type"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<Report, String>("Condition"));
        AccountsManager account = LoginController.accounts;
        User user = account.getUser();
        userReports = user.getUserReports();
        userList = account.getUserList();
        for (User u: userList) {
            if (u.getUserReports() != null) {
                if (userReportsMaster == null) {
                    userReportsMaster = u.getUserReports();
                } else {
                    userReportsMaster.addAll(u.getUserReports());
                }
            }
        }
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
     * Refreshes the report table
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
        if (!userReports.contains(selectedReport)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot Delete Report");
            alert.setContentText("You lack the user privileges to delete reports of other users.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete Report");
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
