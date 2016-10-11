package model;


import java.util.ArrayList;

public class AccountsManager {

    private ArrayList<User> users;
    private String currentUsername;
    private User currentUser;
    private ArrayList<Report> reports;

    /**
     * Instantiates the AccountsManager class
     */
    public AccountsManager() {

        users = new ArrayList<>();
        reports = new ArrayList<>();
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
     * returns the list of reports that are in the system.
     * @return The list of reprots in the system
     */
    public ArrayList<Report> getReportsList() { return reports; }

    /**
     * Adds a report to the system.
     * @param r The report to be added to the system.
     */
    public void addReport(Report r) {
        reports.add(r);
    }

    /**
     * Removes a report from the system.
     * @param r The report that needs to be removed from the system.
     */
    public void removeReport(Report r) {
        reports.remove(r);
    }


}
