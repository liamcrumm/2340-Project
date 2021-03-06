package controller;


import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.MainFXApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;
import com.lynden.gmapsfx.GoogleMapView;


import model.AccountsManager;
import model.WaterReport;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Sakhi on 10/15/16.
 * This controller works with the map screen to initialize the map and put the pins on the map.
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML private GoogleMapView mapView;
    private GoogleMap map;
    private Window mainStage;
    private MainFXApplication theApp;
    private int zoomLevel = 9;
    boolean centerSet = false;

    @Override
    @FXML public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    public void setCallbacks(Window stage, MainFXApplication app) {
        mainStage = stage;
        theApp = app;
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        // now we communicate with the model to get all the locations for markers
        AccountsManager ac = LoginController.accounts;

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

        options.center(center)
                .zoom(zoomLevel)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        ArrayList<WaterReport> reports = ac.getWaterReportsList();

        //LatLongBounds bounds = new LatLongBounds();

        for (WaterReport r: reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(r.getLat(), r.getLong());
            center = new LatLong(r.getLat(),r.getLong());
            //bounds.getJSObject().call("extend",center);
            map.setCenter(center);
            String title = "Report " + r.getReportNumber();
            String description = "Water Type: " + r.getType() + "\nWater Condition: " + r.getCondition();

            markerOptions.position(loc)
                    .title(title);

            Marker marker = new Marker(markerOptions);

            map.addMarker(marker);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(description);
                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

        }
        //map.fitBounds(bounds);
    }

    /**
     * Button handler for "Back" button
     */
    @FXML public void handleBackPressed() {
        theApp.showAccountScreen();
    }

    /**
     * Button handler for zoom in button
     */
    @FXML public void zoomIn() {
        if(map != null) {
            map.setZoom(++zoomLevel);
        }
    }

    /**
     * Button handler for zoom out button
     */
    @FXML public void zoomOut() {
        if(map != null) {
            map.setZoom(--zoomLevel);
        }
    }
}

