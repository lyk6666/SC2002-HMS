package scs1_group1.user;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Staff {
    private List<String> availableSlots; // List of available slots for appointments formatted as "YYYY-MM-DD HH:MM"
    private List<String> HospitalIdOfPatientsUnderCare; // List of hospital IDs of patients under care

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
         // Initialize the list of available slots in ''YYYY-MM-DD HH:MM'' format,and add samples for testing
        this.availableSlots = new ArrayList<>();
        
        this.HospitalIdOfPatientsUnderCare = new ArrayList<>(); // Initialize the list of hospital IDs of patients under care
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
