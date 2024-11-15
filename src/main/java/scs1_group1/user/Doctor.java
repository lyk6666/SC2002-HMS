package scs1_group1.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a doctor in the hospital system.
 * 
 * The Doctor class extends the Staff class and includes additional information such as
 * available appointment slots and patients under the doctor's care.
 * Doctor-specific data is loaded from an external CSV file during instantiation.
 */
public class Doctor extends Staff {
    private List<String> availableSlots; 
    private List<String> HospitalIdOfPatientsUnderCare;
    private static final String DoctorAdditionals = "data/DoctorAdditionals_List.csv";


    /**
     * Constructs a new Doctor instance.
     * 
     * @param hospitalId The unique ID of the doctor in the hospital system.
     * @param password The password used for the doctor's login.
     * @param name The name of the doctor.
     * @param gender The gender of the doctor.
     * @param userType The user type indicating the role (in this case, "Doctor").
     * @param email The email address of the doctor.
     * @param age The age of the doctor.
     */
    public Doctor(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        int age
    ) {
        super(hospitalId, password, name, gender, userType, email, age);
        this.availableSlots = new ArrayList<>();
        this.HospitalIdOfPatientsUnderCare = new ArrayList<>();
        loadDoctorAdditionals(hospitalId);
    }


    /**
     * Loads doctor-specific data (available slots and patients under care) from a CSV file.
     * 
     * @param doctorHospitalId The hospital ID of the doctor whose additional data needs to be loaded.
     */
    private void loadDoctorAdditionals(String doctorHospitalId) {
        try (BufferedReader br = new BufferedReader(new FileReader(DoctorAdditionals))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header
                    continue;
                }

                String[] fields = line.split(",");
                String hospitalId = fields[0].trim();

                if (hospitalId.equals(doctorHospitalId)) {
                    // Load available slots and patients under care
                    if (fields.length > 1 && !fields[1].isEmpty()) {
                        this.availableSlots.addAll(Arrays.asList(fields[1].trim().split(";")));
                    }
                    if (fields.length > 2 && !fields[2].isEmpty()) {
                        this.HospitalIdOfPatientsUnderCare.addAll(Arrays.asList(fields[2].trim().split(";")));
                    }
                    break; // Exit after finding the correct doctor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds an available slot to the doctor's schedule.
     * 
     * @param slot The time slot to be added.
     */
    public void addAvailableSlot(String slot) {
        availableSlots.add(slot);
    }

    /**
     * Retrieves all available slots for the doctor.
     * 
     * @return A list of available slots.
     */
    public List<String> getAvailableSlots() {
        return new ArrayList<>(availableSlots); // Return a copy to avoid modification
    }

    /**
     * Removes an available slot from the doctor's schedule.
     * 
     * @param slot The time slot to be removed.
     */
    public void removeAvailableSlot(String slot) {
        availableSlots.remove(slot);
    }

    /**
     * Adds a patient under the doctor's care.
     * 
     * @param patientHospitalId The hospital ID of the patient to be added under care.
     */
    public void addPatientUnderCare(String patientHospitalId) {
        HospitalIdOfPatientsUnderCare.add(patientHospitalId);
    }

    /**
     * Retrieves all patients currently under the doctor's care.
     * 
     * @return A list of hospital IDs for patients under care.
     */
    public List<String> getAllPatientsUnderCare() {
        return new ArrayList<>(HospitalIdOfPatientsUnderCare); // Return a copy to avoid modification
    }

    @Override
    public String toString() {
        return super.toString() + ", Available Slots: " + availableSlots;
    }
}
