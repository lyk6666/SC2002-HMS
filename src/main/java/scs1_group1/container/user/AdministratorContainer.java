package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.Administrator;
import scs1_group1.user.User;

/**
 * Container class for managing Administrator records.
 * Provides functionalities to load, retrieve, and append Administrator data.
 */
public class AdministratorContainer extends StaffContainer {

    /**
     * Constructs an AdministratorContainer and loads administrators from the given CSV file.
     * 
     * @param filePath Path to the CSV file containing Administrator data.
     */
    public AdministratorContainer(String filePath) {
        super(filePath, "Administrator"); // Only import rows with userType "Administrator"
    }

    /**
     * Appends Administrator records to the CSV file by extending StaffContainer's functionality.
     * 
     * @param filePath Path to the CSV file where the Administrator data will be appended.
     */
    public void appendAdministratorToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "administrator");
    }

    /**
     * Retrieves all Administrators in the container.
     * 
     * @return A HashMap containing all Administrator objects, where the key is the hospital ID and the value is the User object.
     */
    public HashMap <String, User> getAllAdministrators(){
        return this.getAllUsersByUserType("Administrator");
    }

    /**
     * Retrieves an Administrator by their hospital ID.
     * 
     * @param hospitalId The hospital ID of the Administrator to retrieve.
     * @return The Administrator object if found, otherwise null.
     */
    public Administrator getAdministratorByHospitalId(String hospitalId){
        return (Administrator) this.getUserByHospitalId(hospitalId);
    }
}