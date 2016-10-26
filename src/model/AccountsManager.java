package model;


import java.util.ArrayList;

public class AccountsManager {

    private ArrayList<User> users;
    private String currentUsername;
    private User currentUser;
    private ArrayList<WaterReport> waterReports;
    private ArrayList<QualityReport> qualityReports;

    /**
     * Instantiates the AccountsManager class
     */
    public AccountsManager() {

        users = new ArrayList<>();
        waterReports = new ArrayList<>();
        qualityReports = new ArrayList<>();
    }

    /**
     * Sets the current username that is using the app
     * @param username    The username of the user that is in a session right now
     */
    public void setCurrentUser(String username) {
        currentUsername = username;
    }

    /**
     * Returns the user that is using the app right now
     * @return A user variable containing the details of the user that is using the application.
     */
    public User getUser() {
        for(User u: users) {
            if(u.getUsername().equals(currentUsername)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Returns a list of users that are registered in the application.
     * @return a array list of all the users registered for this application
     */
    public ArrayList<User> getUserList() {
        return users;
    }

    /**
     * returns the username of the current user that is logged in to the application
     * @return The username of the user that is logged into the application.
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * returns the list of water reports that are in the system.
     * @return The list of water reports in the system
     */
    public ArrayList<WaterReport> getWaterReportsList() { return waterReports; }

    /**
     * Adds a water report to the system.
     * @param r The water report to be added to the system.
     */
    public void addWaterReport(WaterReport r) {
        waterReports.add(r);
    }

    /**
     * Removes a water report from the system.
     * @param r The water report that needs to be removed from the system.
     */
    public void removeWaterReport(WaterReport r) {
        waterReports.remove(r);
    }

    /**
     * returns the list of quality reports that are in the system.
     * @return The list of quality reports in the system
     */
    public ArrayList<QualityReport> getQualityReportsList() { return qualityReports; }

    /**
     * Adds a quality report to the system.
     * @param r The quality report to be added to the system.
     */
    public void addQualityReport(QualityReport r) {
        qualityReports.add(r);
    }

    /**
     * Removes a quality report from the system.
     * @param r The quality report that needs to be removed from the system.
     */
    public void removeQualityReport(QualityReport r) {
        qualityReports.remove(r);
    }


}
