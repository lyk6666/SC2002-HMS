package scs1_group1.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor extends Staff {
    private List<String> availableSlots; 
    private List<String> HospitalIdOfPatientsUnderCare;
    private static final String DoctorAdditionals = "data/DoctorAdditionals_List.csv";

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

    // Load doctor-specific data (availableSlots and patients under care) from CSV
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

    // Method to add an available slot
    public void addAvailableSlot(String slot) {
        availableSlots.add(slot);
    }

    // Method to show all available slots
    public List<String> getAvailableSlots() {
        return new ArrayList<>(availableSlots); // Return a copy to avoid modification
    }

    // Method to remove an available slot
    public void removeAvailableSlot(String slot) {
        availableSlots.remove(slot);
    }

    // Method to add a patient under care
    public void addPatientUnderCare(String patientHospitalId) {
        HospitalIdOfPatientsUnderCare.add(patientHospitalId);
    }

    // Method to show all patients under care
    public List<String> getAllPatientsUnderCare() {
        return new ArrayList<>(HospitalIdOfPatientsUnderCare); // Return a copy to avoid modification
    }

    @Override
    public String toString() {
        return super.toString() + ", Available Slots: " + availableSlots;
    }
}
