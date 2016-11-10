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
     * Writes user credentials to a Document to store in the database.
     * @return The Document created from the User information
     */
    public Document toDocUser() {
        Document user = new Document();
        user.put("username",_username);
        user.put("password",_password);
        if (_profile != null) {
            user.put("profile",_profile.toDocument());
        } else {
            user.put("profile",null);
        }
        user.put("accountType",_accountType);
        /*Document WaterReports = new Document();
        int i = 0;
        while(i++ < userWaterReports.size()) {
            WaterReports.put();
        }*/
        return user;
    }
}

