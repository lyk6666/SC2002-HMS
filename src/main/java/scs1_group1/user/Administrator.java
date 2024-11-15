package scs1_group1.user;

/**
 * Represents an Administrator in the hospital system.
 * 
 * The Administrator class inherits from the Staff class and includes all the basic
 * information such as hospital ID, password, name, gender, user type, email, and age.
 * Additional functionalities specific to administrators can be added to this class.
 */
public class Administrator extends Staff {
    
    /**
     * Constructs a new Administrator instance.
     * 
     * @param hospitalId The unique ID of the administrator in the hospital system.
     * @param password The password used for the administrator's login.
     * @param name The name of the administrator.
     * @param gender The gender of the administrator.
     * @param userType The user type indicating the role (in this case, "Administrator").
     * @param email The email address of the administrator.
     * @param age The age of the administrator.
     */
    public Administrator(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        int age
    ) {
        super(hospitalId, password, name, gender, userType, email, age);
    }
    
    // Add any additional Administrator-specific methods here if needed
}
