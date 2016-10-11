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


    /**
     * Returns the name of the user in the profile
     * @return The name of the user in the profile
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the name of the user in the profile
     * @param name The name of the user
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Returns the title of the user in the profile
     * @return The title of the user in the profile
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the title of the user in the profile
     * @param title The title of the user
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the email of the user in the profile
     * @return  The email of the user in the profile
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Sets the email of the user in the profile
     * @param email The email of the user
     */
    public void setEmail(String email) {
        _email = email;
    }

    /**
     * Returns the phone number of the user in the profile
     * @return The phone number of the user in the profile
     */
    public String getPhone() {
        return _phone;
    }

    /**
     * Sets the phone number of the user in the profile
     * @param phone The phone number of the user
     */
    public void setPhone(String phone) {
        _phone = phone;
    }

    /**
     * Returns the address of the user in the profile
     * @return The address of the user in the profile
     */
    public String getAddress() {
        return _address;
    }

    /**
     * Sets the address of the user in the profile
     * @param address The address of the user
     */
    public void setAddress(String address) {
        _address = address;
    }

    /**
     * Returns the bio of the user in the profile
     * @return The bio of the user in the profile
     */
    public String getBio() {
        return _bio;
    }

    /**
     * Sets the bio of the user in the profile
     * @param bio The bio of the user
     */
    public void setBio(String bio) {
        _bio = bio;
    }
}
