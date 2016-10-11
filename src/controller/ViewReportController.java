package controller;

import fxapp.MainFXApplication;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        ArrayList<Report> userReports = user.getUserReports();
        if(userReports != null) {
            reportTable.getItems().setAll(userReports);
        }
    }

    /**
     * Setter for main application so we can change values there
     * @param mainFXApplication Link to main application class
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Button handler for "Delete Selected Report" button
     */
    @FXML
    public void handleDeleteSelectedReport() {

    }

    /**
     * Button handler for "Back" button
     */
    @FXML
    public void handleBackPressed() {
        mainApplication.showAccountScreen();
    }
}
