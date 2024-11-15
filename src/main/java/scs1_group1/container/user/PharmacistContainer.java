package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.Pharmacist;
import scs1_group1.user.User;

/**
 * Container class for managing Pharmacist records.
 * Provides functionalities to load, retrieve, and append Pharmacist data.
 */
public class PharmacistContainer extends StaffContainer {

    /**
     * Constructs a PharmacistContainer and loads Pharmacists from the given CSV file.
     * 
     * @param filePath Path to the CSV file containing Pharmacist data.
     */
    public PharmacistContainer(String filePath) {
        super(filePath, "Pharmacist"); // Only import rows with userType "Pharmacist"
    }

    /**
     * Appends Pharmacist records to the CSV file by extending StaffContainer's functionality.
     * 
     * @param filePath Path to the CSV file where the Pharmacist data will be appended.
     */
    public void appendPharmacistToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "pharmacist");
    }

    /**
     * Retrieves all Pharmacists in the container.
     * 
     * @return A HashMap containing all Pharmacist objects, where the key is the hospital ID and the value is the User object.
     */
    public HashMap <String, User> getAllPharmacists(){
        return this.getAllUsersByUserType("Pharmacist");
    }

    /**
     * Retrieves a Pharmacist by their hospital ID.
     * 
     * @param hospitalId The hospital ID of the Pharmacist to retrieve.
     * @return The Pharmacist object if found, otherwise null.
     */
    public Pharmacist getPharmacistByHospitalId(String hospitalId){
        return (Pharmacist) this.getUserByHospitalId(hospitalId);
    }
}
