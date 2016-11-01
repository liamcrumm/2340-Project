package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AccountsManager;
import model.QualityReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    @FXML private NumberAxis numAxis;
    @FXML private CategoryAxis monthAxis;
    @FXML private ScatterChart<CategoryAxis,NumberAxis> historyGraph;

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

        //add the locations and years as dropdown options in the corresponding combo boxes
        ObservableList<String> locations = FXCollections.observableArrayList();
        ObservableList<Integer> years = FXCollections.observableArrayList();
        ArrayList<QualityReport> qreps = account.getQualityReportsList();
        for(QualityReport r: qreps) {
            String loc = r.getLat() + "," + r.getLong();
            locations.add(loc);
            int year = r.getDate().getYear();
            years.add(year);
        }
        locationComboBox.setItems(locations);
        yearComboBox.setItems(years);
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
        String loc = locationComboBox.getSelectionModel().getSelectedItem().toString();
        String year = yearComboBox.getSelectionModel().getSelectedItem().toString();
        if((loc == null && year == null) || (loc == null) || (year == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Graph cannot be created");
            alert.setHeaderText("There is not enough information");
            alert.setContentText("Both a year and a location must be entered in order for the graph to be created.");
            alert.showAndWait();
        } else {
            //change the selected strings to int and doubles for the year and lat,long.
            int yearSelected = Integer.parseInt(year);
            String[] parts = loc.split(",");
            double lat = Double.parseDouble(parts[0]);
            double longitude = Double.parseDouble(parts[1]);

            ObservableList<String> months = FXCollections.observableArrayList();
            months.addAll("January","February","March","April","May","June","July","August","September","October"
                    ,"November","December");
            monthAxis.setCategories(months);

            //Get the selected ppm value(virus or contaminant)
            RadioButton selectedPPM = (RadioButton) type.getSelectedToggle();
            String ppm = selectedPPM.getText();

            //Get the reports that match the year and location given
            //Map<String,Integer> graphPoints = new HashMap<String,Integer>();
            ObservableList<XYChart.Data<String,Integer>> data = FXCollections.observableArrayList();
            ArrayList<QualityReport> allReports = account.getQualityReportsList();
            //Make the graph according to the ppm that is given
            if(ppm.equals("Virus")) {
                for(QualityReport r: allReports) {
                    double repLat = r.getLat();
                    double repLong = r.getLong();
                    int repYear = r.getDate().getYear();
                    if(repLat == lat && repLong == longitude && repYear == yearSelected) {
                        //graphPoints.put(r.getDate().getMonth().toString(),r.getVirus());
                        String month = r.getDate().getMonth().toString();
                        int virus = r.getVirus();
                        XYChart.Data<String,Integer> point = new XYChart.Data<String,Integer>(month,virus);
                        data.add(point);

                    }
                }
                XYChart.Series series = new XYChart.Series(data);
                historyGraph.getData().addAll(series);

            } else {
                for(QualityReport r: allReports) {
                    double repLat = r.getLat();
                    double repLong = r.getLong();
                    int repYear = r.getDate().getYear();
                    if(repLat == lat && repLong == longitude && repYear == yearSelected) {
                        //graphPoints.put(r.getDate().getMonth().toString(),r.getVirus());
                        String month = r.getDate().getMonth().toString();
                        int cont = r.getContaminant();
                        XYChart.Data<String,Integer> point = new XYChart.Data<String,Integer>(month,cont);
                        data.add(point);

                    }
                }
                XYChart.Series series = new XYChart.Series(data);
                historyGraph.getData().addAll(series);

            }



        }
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
