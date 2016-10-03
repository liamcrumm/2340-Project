package model;


import java.util.ArrayList;

/**
 * Created by Sakhi on 10/2/16.
 */
public class AccountsManager {

    private ArrayList<User> users;
    private String currentUsername;
    private User currentUser;

    /**
     * Instantiates the AccountsManager class
     */
    public AccountsManager() {
        users = new ArrayList<User>();
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
                currentUser = u;
            }
        }
        return currentUser;
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


}
