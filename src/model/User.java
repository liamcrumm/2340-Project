package model;



import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sakhi on 10/3/16.
 */
public class User {

    private String _username;
    private String _password;
    private Profile _profile;
    private String _accountType;
    private ArrayList<WaterReport> userWaterReports;
    private ArrayList<QualityReport> userQualityReports;

    /**
     * Instantiates an instance of User
     * @param username The username of the user
     * @param password The password of the user
     * @param profile  The profile of the user
     */
    public User(String username, String password, Profile profile, String accountType) {
        _username = username;
        _password = password;
        _profile = profile;
        _accountType = accountType;
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
     * Returns the account type of this user
     * @return The account type of this uses
     */
    public String getAccountType() {return _accountType; }

    /**
     * sets the account type of this user
     * @param type The account type of the user
     */
    public void setAccountType(String type) {_accountType = type; }

    /**
     * Sets the profile of this user
     * @param prof The profile of the user
     */
    public void setProfile(Profile prof) {
        _profile = prof;
    }

    /**
     * Adds a water report created by the user to the list of water reports that the user has submitted.
     * @param r The water report that the user submitted.
     */
    public void addWaterReport(WaterReport r) {
        if(userWaterReports == null) {
            userWaterReports = new ArrayList<>();
        }
        userWaterReports.add(r);
    }

    /**
     * Returns the list of water reports that the user has submitted.
     * @return The list of water reports that the user has submitted.
     */
    public ArrayList<WaterReport> getUserWaterReports() { return userWaterReports; }

    /**
     * Removes a water report from the list of water reports.
     * @param r The water report to be removed.
     */
    public void deleteWaterReport(WaterReport r) {
        userWaterReports.remove(r);
    }

    /**
     * Adds a quality report created by the user to the list of reports that the user has submited.
     * @param r The quality report that the user submitted.
     */
    public void addQualityReport(QualityReport r) {
        if(userQualityReports == null) {
            userQualityReports = new ArrayList<>();
        }
        userQualityReports.add(r);
    }

    /**
     * Returns the list of quality reports that the user has submitted.
     * @return The list of quality reports that the user has submitted.
     */
    public ArrayList<QualityReport> getUserQualityReports() { return userQualityReports; }

    /**
     * Removes a quality report from the list of quality reports.
     * @param r The quality report to be removed.
     */
    public void deleteQualityReport(QualityReport r) {
        userQualityReports.remove(r);
    }

    public Document toDoc() {
        Document user = new Document();
        user.put("username",_username);
        user.put("password",_password);
        user.put("profile",_profile.toDocument());
        user.put("accountType",_accountType);
        /*Document WaterReports = new Document();
        int i = 0;
        while(i++ < userWaterReports.size()) {
            WaterReports.put();
        }*/
        return user;
    }
}

