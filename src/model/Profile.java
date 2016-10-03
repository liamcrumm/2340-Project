package model;


/**
 * Created by Sakhi on 9/29/16.
 */
public class Profile {

    private  String _name;
    private  String _title;
    private  String _email;
    private  String _phone;
    private  String _address;
    private  String _bio;

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
        _name = name;
        _title = title;
        _email = email;
        _phone = phone;
        _address = address;
        _bio = bio;
    }


    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getPhone() {
        return _phone;
    }

    public void setPhone(String phone) {
        _phone = phone;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public String getBio() {
        return _bio;
    }

    public void setBio(String bio) {
        _bio = bio;
    }
}
