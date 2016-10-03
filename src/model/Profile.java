package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Sakhi on 9/29/16.
 */
public class Profile {

    private  StringProperty _name = new SimpleStringProperty();
    private  StringProperty _title = new SimpleStringProperty();
    private  StringProperty _email = new SimpleStringProperty();
    private  StringProperty _phone = new SimpleStringProperty();
    private  StringProperty _address = new SimpleStringProperty();
    private  StringProperty _bio = new SimpleStringProperty();

    /**
     * Constructor for this class. Instantiates a profile object.
     * @param name     The name of the user
     * @param title    The title of the user
     * @param email    The email of the user
     * @param phone    The phone number of the user
     * @param address  The address of the user
     * @param bio      The biography of the user
     */
    public Profile(String name, String title, String email, String phone, String address, String bio) {
        _name.set(name);
        _title.set(title);
        _email.set(email);
        _phone.set(phone);
        _address.set(address);
        _bio.set(bio);
    }

    /**
     * Used for the GUI when the user edits his or her profile.
     */
    public Profile() {
        _name.set("Enter name");
        _title.set("Enter title");
        _email.set("Enter email");
        _phone.set("Enter phone number");
        _address.set("Enter address");
        _bio.set("Enter biography");
    }

    public StringProperty getName() {
        return _name;
    }

    public void setName(StringProperty name) {
        _name = name;
    }

    public StringProperty getTitle() {
        return _title;
    }

    public void setTitle(StringProperty title) {
        _title = title;
    }

    public StringProperty getEmail() {
        return _email;
    }

    public void setEmail(StringProperty email) {
        _email = email;
    }

    public StringProperty getPhone() {
        return _phone;
    }

    public void setPhone(StringProperty phone) {
        _phone = phone;
    }

    public StringProperty getAddress() {
        return _address;
    }

    public void setAddress(StringProperty address) {
        _address = address;
    }

    public StringProperty getBio() {
        return _bio;
    }

    public void setBio(StringProperty bio) {
        _bio = bio;
    }
}
