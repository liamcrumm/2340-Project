package model;


/**
 * Created by Sakhi on 10/3/16.
 */
public class User {

    private String _username;
    private String _password;
    private Profile _profile;

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
}
