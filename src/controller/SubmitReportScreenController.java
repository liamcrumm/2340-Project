package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Created by Sakhi on 10/10/16.
 */
public class SubmitReportScreenController {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField hourField;

    @FXML
    private RadioButton amButton;

    @FXML
    private RadioButton pmButton;

    @FXML
    private TextField locationField;

    @FXML
    private RadioButton bottledButton;

    @FXML
    private RadioButton wellButton;

    @FXML
    private RadioButton streamButton;

    @FXML
    private RadioButton lakeButton;

    @FXML
    private RadioButton springButton;

    @FXML
    private RadioButton otherButton;

    @FXML
    private RadioButton wasteButton;

    @FXML
    private RadioButton treatClearButton;

    @FXML
    private RadioButton treatMuddyButton;

    @FXML
    private RadioButton potableButton;
}
