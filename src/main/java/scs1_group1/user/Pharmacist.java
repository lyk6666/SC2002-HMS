package scs1_group1.user;

/**
 * Represents a pharmacist in the hospital system.
 * 
 * The Pharmacist class extends the Staff class and includes additional functionality for pharmacists.
 */
public class Pharmacist extends Staff {
    
    /**
     * Constructs a new Pharmacist instance.
     * 
     * @param hospitalId  The unique ID of the pharmacist in the hospital system.
     * @param password    The password used for the pharmacist's login.
     * @param name        The name of the pharmacist.
     * @param gender      The gender of the pharmacist.
     * @param userType    The user type indicating the role (in this case, "Pharmacist").
     * @param email       The email address of the pharmacist.
     * @param age         The age of the pharmacist.
     */
    public Pharmacist(
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
    
    // Add any additional Pharmacist-specific methods here if needed
}
