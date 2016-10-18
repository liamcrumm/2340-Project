package controller;


import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.oracle.tools.packager.Log;
import fxapp.MainFXApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import com.lynden.gmapsfx.GoogleMapView;


import model.AccountsManager;
import model.Report;
import netscape.javascript.JSObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Sakhi on 10/15/16.
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private Window mainStage;

    private MainFXApplication theApp;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    public void setCallbacks(Window stage, MainFXApplication app) {
        mainStage = stage;
        theApp = app;
    }


    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        /** now we communciate with the model to get all the locations for markers */
        AccountsManager ac = LoginController.accounts;
        ArrayList<Report> reports = ac.getReportsList();

        for (Report r: reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(r.getLat(), r.getLong());
            String title = "Report " + r.getReportNumber();
            String description = "Water Type: " + r.getType() + "\nWater Condition: " + r.getCondition();

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(title);

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(description);

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }
    }

    /**
     * Button handler for "Back" button
     */
    @FXML
    public void handleBackPressed() {
        theApp.showAccountScreen();
    }
}
