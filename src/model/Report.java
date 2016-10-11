package model;

import java.time.LocalDate;

/**
 * Created by Andrew on 10/11/2016.
 */
public class Report {

    private String _name;
    private LocalDate _date;
    private String _time;
    private String _location;
    private String _type;
    private String _condition;

    /**
     * Constructor for a this class. Instantiates a Report object.
     * @param name      Name of report
     * @param date      Date of report
     * @param time      Time of report
     * @param location  Location of report
     * @param type      Type of water
     * @param condition Condition of water
     */
    public Report(String name, LocalDate date, String time, String location, String type, String condition){
        _name = name;
        _date = date;
        _time = time;
        _location = location;
        _type = type;
        _condition = condition;
    }

    /**
     * Getter for report name
     * @return Name of report
     */
    public String getName() { return _name; }

    /**
     * Setter for report name
     * @param name New name of report
     */
    public void setName(String name){ _name = name; }

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
}
