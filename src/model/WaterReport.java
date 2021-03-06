package model;

import org.bson.Document;

import java.util.Date;

/**
 * Created by Andrew on 10/11/2016.
 * This class represents a water report and holds all the information for a water report.
 */
public class WaterReport implements Report {

    private int _reportNumber;
    private String _username;
    private Date _date;
    private String _time;
    private double _lat;
    private double _long;
    private String _type;
    private String _condition;

    /**
     * Constructor for a this class. Instantiates a WaterReport object.
     * @param repNum  Number of report
     * @param username      Name of user who submitted the report
     * @param date          Date of report
     * @param time          Time of report
     * @param lat           Latitude of the location
     * @param longitude     Longitude of the location;
     * @param type          Type of water
     * @param condition     Condition of water
     */
    public WaterReport(int repNum, String username, Date date, String time, double lat, double longitude, String type, String condition){
        _reportNumber = repNum;
        _username = username;
        _date = date;
        _time = time;
        _lat = lat;
        _long = longitude;
        _type = type;
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
    public Date getDate() { return _date; }

    /**
     * Setter for report date
     * @param date New date of report
     */
    public void setDate(Date date) { _date = date; }

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
     * Getter for water type
     * @return Type of water
     */
    public String getType() { return _type; }

    /**
     * Setter for water type
     * @param type New type of water
     */
    public void setType(String type) { _type = type; }

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
     * Method to store persistent data for a water report
     * @return  Document with relevant data for Water report
     */
    public Document toDoc() {
        Document quality = new Document();
        quality.put("username",_username);
        quality.put("repNum",_reportNumber);
        quality.put("date",_date);
        quality.put("time",_time);
        quality.put("lat",_lat);
        quality.put("long",_long);
        quality.put("type",_type);
        quality.put("condition",_condition);
        return quality;
    }

}
