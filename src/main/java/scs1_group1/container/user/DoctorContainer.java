package scs1_group1.container.user;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

import scs1_group1.user.Doctor;
import scs1_group1.user.User;


/**
 * Container class for managing Doctor records.
 * Provides functionalities to load, retrieve, append, and export Doctor data.
 */
public class DoctorContainer extends StaffContainer {

    /**
     * Constructs a DoctorContainer and loads doctors from the given CSV file.
     * 
     * @param filePath Path to the CSV file containing Doctor data.
     */
    public DoctorContainer(String filePath) {
        super(filePath, "Doctor"); // Only import rows with userType "Doctor"
    }

    /**
     * Appends Doctor records to the CSV file by extending StaffContainer's functionality.
     * 
     * @param filePath Path to the CSV file where the Doctor data will be appended.
     */
    public void appendDoctorToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "doctor");
    }

    /**
     * Retrieves all Doctors in the container.
     * 
     * @return A HashMap containing all Doctor objects, where the key is the hospital ID and the value is the User object.
     */
    public HashMap <String, User> getAllDoctors(){
        return this.getAllUsersByUserType("Doctor");
    }

    /**
     * Retrieves a Doctor by their hospital ID.
     * 
     * @param hospitalId The hospital ID of the Doctor to retrieve.
     * @return The Doctor object if found, otherwise null.
     */
    public Doctor getDoctorByHospitalId(String hospitalId){
        return (Doctor) this.getUserByHospitalId(hospitalId);
    }

    /**
     * Exports additional information for each doctor, such as available slots and patients under care, to a CSV file.
     * 
     * @param doctorAdditionalPath Path to the CSV file where the additional data for doctors will be exported.
     */
    public void exportDoctorAdditionalsToCSV(String Doctor_Additional_Path) {
        try (FileWriter writer = new FileWriter(Doctor_Additional_Path)) {
            // Write header
            writer.write("DoctorHospitalId,AvailableSlots,PatientsUnderCare\n");

            // Write each doctor's additional data
            for (Doctor doctor : getAllDoctors().values().stream().map(d -> (Doctor) d).collect(Collectors.toList())) {
                String doctorId = doctor.getHospitalId();
                String availableSlots = String.join(";", doctor.getAvailableSlots());
                String patientsUnderCare = String.join(";", doctor.getAllPatientsUnderCare());

                writer.write(String.format("%s,%s,%s\n", doctorId, availableSlots, patientsUnderCare));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}