package model;


import java.util.ArrayList;

/**
 * Created by Sakhi on 10/2/16.
 */
public class AccountsManager {

    private ArrayList<User> users;
    private String currentUsername;
    private User currentUser;

    public AccountsManager() {
        users = new ArrayList<User>();
    }

    public void setCurrentUser(String username) {
        currentUsername = username;
    }

    public User getUser() {
        for(User u: users) {
            if(u.getUsername().equals(currentUsername)) {
                currentUser = u;
            }
        }
        return currentUser;
    }

    public ArrayList<User> getUserList() {
        return users;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }


}
