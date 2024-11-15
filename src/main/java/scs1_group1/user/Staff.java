package scs1_group1.user;

/**
 * Represents a staff member in the hospital system.
 * 
 * The Staff class extends the User class and adds additional attributes and functionality specific to hospital staff.
 */
public class Staff extends User {
    private int age;

    /**
     * Constructs a new Staff instance.
     * 
     * @param hospitalId  The unique ID of the staff member in the hospital system.
     * @param password    The password used for the staff member's login.
     * @param name        The name of the staff member.
     * @param gender      The gender of the staff member.
     * @param userType    The user type indicating the role of the staff member.
     * @param email       The email address of the staff member.
     * @param age         The age of the staff member.
     */
    public Staff(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        int age) 
    {
        super(hospitalId, password, name, gender, userType, email);
        this.age = age;
    }

    /**
     * Gets the age of the staff member.
     * 
     * @return The age of the staff member.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the staff member.
     * 
     * @param age The new age of the staff member.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
