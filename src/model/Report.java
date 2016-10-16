package model;

import java.time.LocalDate;

/**
 * Created by Andrew on 10/11/2016.
 */
public class Report {

    private String _username;
    private LocalDate _date;
    private String _time;
    private String _location;
    private String _type;
    private String _condition;
    private int _reportNumber;

    /**
     * Constructor for a this class. Instantiates a Report object.
     * @param username  Name of user who submitted the report
     * @param date      Date of report
     * @param time      Time of report
     * @param location  Location of report
     * @param type      Type of water
     * @param condition Condition of water
     */
    public Report(String username, LocalDate date, String time, String location, String type, String condition){
        _username = username;
        _date = date;
        _time = time;
        _location = location;
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
     * Getter for report location
     * @return Location of report
     */
    public String getLocation() { return _location; }

    /**
     * Setter for report location
     * @param location New location of report
     */
    public void setLocation(String location) { _location = location; }

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
}
