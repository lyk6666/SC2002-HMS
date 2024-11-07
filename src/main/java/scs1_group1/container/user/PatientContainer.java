package scs1_group1.container.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import scs1_group1.user.Patient;
import scs1_group1.user.User;

public class PatientContainer extends UserContainer {
    public PatientContainer(String filePath) {
        super();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true; // Flag to skip the header line
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the first line (header)
                    continue;
                }

                String[] fields = line.split(",");
                String hospitalId = fields[0].trim();         // "Patient ID"
                String name = fields[1].trim();               // "Name"
                String dateOfBirth = fields[2].trim();        // "Date of Birth"
                String gender = fields[3].trim();             // "Gender"
                String bloodType = fields[4].trim();          // "Blood Type"
                String email = fields[5].trim();              // "Contact Information"
                
                Patient patient = new Patient(
                    hospitalId,
                    "password",      // Default password
                    name,
                    gender,
                    "Patient",       // User type
                    email,
                    dateOfBirth,
                    "",              // Phone number (not provided in the CSV)
                    bloodType
                );
                
                putUser(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get all patients as a hashmap
    public HashMap <String, User> getAllPatients(){
        return this.getAllUsersByUserType("Patient");
    }

    //get patient by hospital id
    public Patient getPatientByHospitalId(String hospitalId){
        return (Patient) this.getUserByHospitalId(hospitalId);
    }
}
