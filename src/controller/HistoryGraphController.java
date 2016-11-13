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

import java.util.*;

/**
 * Created by Kimberly Burke on 10/29/2016.
 * This controller deals with the history graph and makes sure that the right graph is displayed.
 */
public class HistoryGraphController {

    /** Link back to main application class */
    @FXML
    private MainFXApplication mainApplication;

    @FXML private RadioButton virusButton;
    @FXML private RadioButton contaminantButton;
    @FXML private ComboBox locationComboBox;
    @FXML private ComboBox yearComboBox;
    @FXML private Button makeButton;
    @FXML private NumberAxis numAxis;
    @FXML private CategoryAxis monthAxis;
    @FXML private ScatterChart<String,Integer> historyGraph;

    private ToggleGroup type = new ToggleGroup();

    private AccountsManager accounts = LoginController.accounts;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /** the window for this dialog */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private boolean _okClicked = false;

    private String[] Months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     * Sets default values into report.
     */
    @FXML private void initialize() {
        virusButton.setToggleGroup(type);
        contaminantButton.setToggleGroup(type);
        virusButton.setSelected(true); // set default selection
        
        //add the locations and years as dropdown options in the corresponding combo boxes
        ObservableList<String> locations = FXCollections.observableArrayList();
        ObservableList<Integer> years = FXCollections.observableArrayList();

        //sets for the location and year so that it is not repeated in the dropdown menu.
        Set<String> locationSet = new HashSet<>();
        Set<Integer> yearSet = new HashSet<>();

        ArrayList<QualityReport> qReps = accounts.getQualityReportsList();
        if (qReps == null || qReps.size() == 0) {
            alert.setTitle("Graph cannot be created");
            alert.setHeaderText("There are no submitted water quality reports");
            alert.setContentText("There are no quality reports submitted at this time.");
            alert.showAndWait();
        } else {
            for(QualityReport r: qReps) {
                String loc = r.getLat() + "," + r.getLong();
                locationSet.add(loc);
                Calendar cal = Calendar.getInstance();
                cal.setTime(r.getDate());
                int year = cal.get(Calendar.YEAR);
                yearSet.add(year);
            }

            locations.addAll(locationSet);
            years.addAll(yearSet);

            locationComboBox.setItems(locations);
            yearComboBox.setItems(years);

            ObservableList<String> months = FXCollections.observableArrayList();
            months.addAll("January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December");

            monthAxis.setCategories(months);

        }
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
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return  true if the user clicked the OK button
     */
    public boolean isOkClicked() {
        return _okClicked;
    }

    /**
     * Button handler for "Make Graph" button
     */
    @FXML public void handleMakeGraphPressed() {
        boolean successful = false; // needed for closing dialog box correctly
        if((locationComboBox.getSelectionModel().getSelectedItem() == null)
                && (yearComboBox.getSelectionModel().getSelectedItem() == null)) {
            alert.setTitle("Graph cannot be created");
            alert.setHeaderText("There is not enough information");
            alert.setContentText("Both a year and a location must be entered in order for the graph to be created.");
            alert.showAndWait();
        } else if ((locationComboBox.getSelectionModel().getSelectedItem() == null)
                && (yearComboBox.getSelectionModel().getSelectedItem() != null)) {
            alert.setTitle("Graph cannot be created");
            alert.setHeaderText("There is not enough information");
            alert.setContentText("A location must be entered in order for the graph to be created.");
            alert.showAndWait();
        } else if ((locationComboBox.getSelectionModel().getSelectedItem() != null)
                && (yearComboBox.getSelectionModel().getSelectedItem() == null)) {
            alert.setTitle("Graph cannot be created");
            alert.setHeaderText("There is not enough information");
            alert.setContentText("A year must be entered in order for the graph to be created.");
            alert.showAndWait();
        } else {
            String loc = locationComboBox.getSelectionModel().getSelectedItem().toString();
            String year = yearComboBox.getSelectionModel().getSelectedItem().toString();
            //change the selected strings to int and doubles for the year and lat,long.
            int yearSelected = Integer.parseInt(year);
            String[] parts = loc.split(",");
            double lat = Double.parseDouble(parts[0]);
            double longitude = Double.parseDouble(parts[1]);

            //Get the selected ppm value(virus or contaminant)
            RadioButton selectedPPM = (RadioButton) type.getSelectedToggle();
            String ppm = selectedPPM.getText();

            //Get the reports that match the year and location given
            ArrayList<QualityReport> allReports = accounts.getQualityReportsList();
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            //Make the graph according to the ppm that is given
            if (ppm.equals("Virus")) {
                series.setName("Virus At " + loc);
                for (QualityReport r : allReports) {
                    double repLat = r.getLat();
                    double repLong = r.getLong();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(r.getDate());
                    int repYear = cal.get(Calendar.YEAR);
                    if (repLat == lat && repLong == longitude && repYear == yearSelected) {
                        String month = Months[cal.get(Calendar.MONTH)];
                        int virus = r.getVirus();
                        series.getData().add(new XYChart.Data<>(month, virus));

                    }
                }

                historyGraph.getData().add(series);

            } else {
                series.setName("Contaminant At " + loc);
                for (QualityReport r : allReports) {
                    double repLat = r.getLat();
                    double repLong = r.getLong();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(r.getDate());
                    int repYear = cal.get(Calendar.YEAR);
                    if (repLat == lat && repLong == longitude && repYear == yearSelected) {
                        String month = Months[(cal.get(Calendar.MONTH))];
                        int cont = r.getContaminant();
                        series.getData().add(new XYChart.Data<>(month, cont));

                    }
                }
                historyGraph.getData().add(series);

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
    @FXML public void handleHomeButtonPressed() {
        _okClicked = true;
        _dialogStage.close();
    }
}
