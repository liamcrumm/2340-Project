package model;

import java.time.LocalDate;

/**
 * Created by Andrew on 10/11/2016.
 */
public interface Report {

    /**
     * Getter for the user who submitted the report
     * @return Name of user who submitted the report
     */
    public String getReportUsername();

    /**
     * Setter for ownership of report
     * @param username New ownership of report
     */
    public void setReportUsername(String username);

    /**
     * Getter for report date
     * @return Date of report
     */
    public LocalDate getDate();

    /**
     * Setter for report date
     * @param date New date of report
     */
    public void setDate(LocalDate date);

    /**
     * Getter for report time
     * @return Time of report
     */
    public String getTime();
    /**
     * Setter for report time
     * @param time New time of report
     */
    public void setTime(String time);

    /**
     * Getter for the latitude of the location of the water
     * @return The latitude of the location where the water is
     */
    public double getLat();

    /**
     * Setter for the latitude of the location of the water
     * @param lat The latitude of the location of the water
     */
    public void setLat(double lat);

    /**
     * Getter for the longitude of the location of the water
     * @return The longitude of the location of the water
     */
    public double getLong();

    /**
     * Setter for the location of the water
     * @param longitude The longitude of the location of the water
     */
    public void setLong(double longitude);

    /**
     * Getter for water condition
     * @return Condition of water
     */
    public String getCondition();

    /**
     * Setter for water condition
     * @param condition New Condition of water
     */
    public void setCondition(String condition);

    /**
     * Getter for the report number
     * @return The report number
     */
    public int getReportNumber();

    /**
     * Setter for the report number
     * @param num The number of the report
     */
    public void setReportNumber(int num);
}
