package model;

import java.util.Date;

/**
 * Created by Andrew on 10/11/2016.
 */
public interface Report {

    /**
     * Getter for the user who submitted the report
     * @return Name of user who submitted the report
     */
    String getReportUsername();

    /**
     * Setter for ownership of report
     * @param username New ownership of report
     */
    void setReportUsername(String username);

    /**
     * Getter for report date
     * @return Date of report
     */
    Date getDate();

    /**
     * Setter for report date
     * @param date New date of report
     */
    void setDate(Date date);

    /**
     * Getter for report time
     * @return Time of report
     */
    String getTime();
    /**
     * Setter for report time
     * @param time New time of report
     */
    void setTime(String time);

    /**
     * Getter for the latitude of the location of the water
     * @return The latitude of the location where the water is
     */
    double getLat();

    /**
     * Setter for the latitude of the location of the water
     * @param lat The latitude of the location of the water
     */
    void setLat(double lat);

    /**
     * Getter for the longitude of the location of the water
     * @return The longitude of the location of the water
     */
    double getLong();

    /**
     * Setter for the location of the water
     * @param longitude The longitude of the location of the water
     */
    void setLong(double longitude);

    /**
     * Getter for water condition
     * @return Condition of water
     */
    String getCondition();

    /**
     * Setter for water condition
     * @param condition New Condition of water
     */
    void setCondition(String condition);

    /**
     * Getter for the report number
     * @return The report number
     */
    int getReportNumber();

    /**
     * Setter for the report number
     * @param num The number of the report
     */
    void setReportNumber(int num);
}
