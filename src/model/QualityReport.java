package model;

import java.time.LocalDate;

/**
 * Created by Andrew on 10/26/2016.
 */
public class QualityReport implements Report {

    private int _reportNumber;
    private String _username;
    private LocalDate _date;
    private String _time;
    private double _lat;
    private double _long;
    private String _condition;
    private int _virus;
    private int _contaminant;

    /**
     * Constructor for a this class. Instantiates a QualityReport object.
     * @param repNum  Number of report
     * @param username      Name of user who submitted the report
     * @param date          Date of report
     * @param time          Time of report
     * @param lat           Latitude of the location
     * @param longitude     Longitude of the location;
     * @param condition     Condition of water
     * @param virus         Virus PPM
     * @param contaminant   Contaminant PPM
     */
    public QualityReport(int repNum, String username, LocalDate date, String time, double lat, double longitude, String condition, int virus, int contaminant){
        _reportNumber = repNum;
        _username = username;
        _date = date;
        _time = time;
        _lat = lat;
        _long = longitude;
        _condition = condition;
        _virus = virus;
        _contaminant = contaminant;
    }

    /**
     * Getter for the user who submitted the report
     * @return Name of user who submitted the report
     */
    public String getReportUsername() { return _username; }

    /**
     * Setter for ownership of report
     * @param username New ownership of report
     */
    public void setReportUsername(String username){ _username = username; }

    /**
     * Getter for report date
     * @return Date of report
     */
    public LocalDate getDate() { return _date; }

    /**
     * Setter for report date
     * @param date New date of report
     */
    public void setDate(LocalDate date) { _date = date; }

    /**
     * Getter for report time
     * @return Time of report
     */
    public String getTime() { return _time; }

    /**
     * Setter for report time
     * @param time New time of report
     */
    public void setTime(String time) { _time = time; }

    /**
     * Getter for the latitude of the location of the water
     * @return The latitude of the location where the water is
     */
    public double getLat() { return _lat; }

    /**
     * Setter for the latitude of the location of the water
     * @param lat The latitude of the location of the water
     */
    public void setLat(double lat) { _lat = lat; }

    /**
     * Getter for the longitude of the location of the water
     * @return The longitude of the location of the water
     */
    public double getLong() { return _long; }

    /**
     * Setter for the location of the water
     * @param longitude The longitude of the location of the water
     */
    public void setLong(double longitude) { _long = longitude; }

    /**
     * Getter for water condition
     * @return Condition of water
     */
    public String getCondition(){ return _condition; }

    /**
     * Setter for water condition
     * @param condition New Condition of water
     */
    public void setCondition(String condition) { _condition = condition; }

    /**
     * Getter for the report number
     * @return The report number
     */
    public int getReportNumber() { return _reportNumber; }

    /**
     * Setter for the report number
     * @param num The number of the report
     */
    public void setReportNumber(int num) { _reportNumber = num; }

    /**
     * Getter virus PPM of water
     * @return Virus PPM of water
     */
    public int getVirus(){ return _virus; }

    /**
     * Setter for virus PPM of water
     * @param virus New virus PPM of water
     */
    public void setVirus(int virus) { _virus = virus; }

    /**
     * Getter for contaminant PPM of water
     * @return Contaminant PPM of water
    public int getContaminant() { return _contaminant; }

    /**
     * Setter for contaminant PPM of water
     * @param contaminant New contaminant PPM of water
     */
    public void setContaminant(int contaminant) { _contaminant = contaminant; }

}
