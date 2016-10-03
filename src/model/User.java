package model;


/**
 * Created by Sakhi on 10/3/16.
 */
public class User {

    private String _username;
    private String _password;
    private Profile _profile;

    public User(String username, String password,Profile profile) {
        _username = username;
        _password = password;
        _profile = profile;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public Profile getProfile() {
        return _profile;
    }

    public void setProfile(Profile prof) {
        _profile = prof;
    }
}
