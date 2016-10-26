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

    /**
     * Constructor for a this class. Instantiates a Report object.
     * @param repNum  Number of report
     * @param username      Name of user who submitted the report
     * @param date          Date of report
     * @param time          Time of report
     * @param lat           Latitude of the location
     * @param longitude     Longitude of the location;
     * @param condition     Condition of water
     */
    public QualityReport(int repNum, String username, LocalDate date, String time, double lat, double longitude, String condition){
        _reportNumber = repNum;
        _username = username;
        _date = date;
        _time = time;
        _lat = lat;
        _long = longitude;
        _condition = condition;
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

}
