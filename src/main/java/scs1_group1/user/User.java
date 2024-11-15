package scs1_group1.user;

/**
 * Represents an abstract user in the hospital management system.
 * 
 * The User class contains common attributes shared by all types of users, including hospital staff and patients.
 */
public abstract class User{
    private String hospitalId;
    private String password="password";
    private String name;
    private String gender;
    private String userType;
    private String email;

    /**
     * Constructs a new User instance.
     * 
     * @param hospitalId  The unique ID of the user in the hospital system.
     * @param password    The password used for the user's login.
     * @param name        The name of the user.
     * @param gender      The gender of the user.
     * @param userType    The type of user (e.g., "Patient", "Doctor", "Pharmacist", "Administrator").
     * @param email       The email address of the user.
     */
    public User(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email) 
    {
        this.hospitalId = hospitalId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.userType = userType;
        this.email = email;
    }

    /**
     * Returns a string representation of the user.
     * 
     * @return The name of the user.
     */
    public String toString() {
        return name;
    }

    /**
     * Gets the hospital ID of the user.
     * 
     * @return The hospital ID of the user.
     */
    public String getHospitalId() {
        return hospitalId;
    }

    /**
     * Sets the hospital ID of the user.
     * 
     * @param hospitalId The new hospital ID of the user.
     */
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the user.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of the user.
     * 
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the user.
     * 
     * @return The gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     * 
     * @param gender The new gender of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * Gets the user type.
     * 
     * @return The user type of the user (e.g., "Patient", "Doctor").
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     * 
     * @param userType The new user type of the user.
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the email of the user.
     * 
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email The new email address of the user.
     */
    public void setEmail(String email) {
        this.email = email; 
    }

}
