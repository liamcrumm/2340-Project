package model;


import java.util.ArrayList;

/**
 * Created by Sakhi on 10/3/16.
 */
public class User {

    private String _username;
    private String _password;
    private Profile _profile;
    private ArrayList<Report> userReports;

    /**
     * Instantiates an instance of User
     * @param username The username of the user
     * @param password The password of the user
     * @param profile  The profile of the user
     */
    public User(String username, String password,Profile profile) {
        _username = username;
        _password = password;
        _profile = profile;
    }

    /**
     * Returns the username of the user
     * @return The username of the user
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Sets the username of the user
     * @param username The username of the user
     */
    public void setUsername(String username) {
        _username = username;
    }

    /**
     * Returns the password of the user
     * @return The password of the user
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Sets the password of the user
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        _password = password;
    }

    /**
     * Returns the profile of this user
     * @return The profile of this user
     */
    public Profile getProfile() {
        return _profile;
    }

    /**
     * Sets the profile of this user
     * @param prof The profile of the user
     */
    public void setProfile(Profile prof) {
        _profile = prof;
    }

    /**
     * Adds a report created by the user to the list of reports that the user has submited.
     * @param r The report that the user submitted.
     */
    public void addReport(Report r) {
        if(userReports == null) {
            userReports = new ArrayList<>();
        }
        userReports.add(r);
    }

    /**
     * Returns the list of reports that the user has submitted.
     * @return The list of reprots that the user has submitted.
     */
    public ArrayList<Report> getUserReports() { return userReports; }

    /**
     * Removes a report from the list of reports.
     * @param r The report to be removed.
     */
    public void deleteReport(Report r) {
        userReports.remove(r);
    }
}

