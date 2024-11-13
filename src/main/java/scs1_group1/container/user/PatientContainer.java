package scs1_group1.container.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import scs1_group1.user.Patient;
import scs1_group1.user.User;

public class PatientContainer extends UserContainer {
    public PatientContainer(String filePath, String medicalRecordPath) {
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
                String password = fields[1].trim();           // "Password"
                String name = fields[2].trim();               // "Name"
                String dateOfBirth = fields[3].trim();        // "Date of Birth"
                String gender = fields[4].trim();             // "Gender"
                String bloodType = fields[5].trim();          // "Blood Type"
                String email = fields[6].trim();              // "Contact Information"
                
                Patient patient = new Patient(
                    hospitalId,
                    password,           // Use password from CSV
                    name,
                    gender,
                    "Patient",          // User type
                    email,
                    dateOfBirth,
                    "",                 // Phone number (not provided in the CSV)
                    bloodType,
                    medicalRecordPath
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

    // Method to export all patient data to CSV
    public void exportPatientsToCSV(String output_filePath) {
        try (FileWriter writer = new FileWriter(output_filePath)) {
            // Write the header
            writer.write("Patient ID,Password,Name,Date of Birth,Gender,Blood Type,Contact Information\n");

            // Get all patients
            HashMap<String, User> patients = getAllPatients();
            for (User user : patients.values()) {
                if (user instanceof Patient) {
                    Patient patient = (Patient) user;
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s\n",
                        patient.getHospitalId(),
                        patient.getPassword(),
                        patient.getName(),
                        patient.getDateOfBirth(),
                        patient.getGender(),
                        patient.getBloodType(),
                        patient.getEmail()));
                }
            }
            System.out.println("Patient data successfully exported to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Export medical records to CSV
    public void exportMedicalRecordsToCSV(String medicalRecordPath) {
        try (FileWriter writer = new FileWriter(medicalRecordPath)) {
            // Write header
            writer.write("PatientHospitalId,Diagnoses,Treatments\n");

            // Write each patient's medical record
            for (User user : getAllPatients().values()) {
                Patient patient = (Patient) user;
                String patientId = patient.getHospitalId();
                
                // Join diagnoses and treatments into comma-separated lists
                String diagnoses = String.join(", ", patient.getDiagnoses());
                String treatments = String.join(", ", patient.getTreatments());
                
                writer.write(String.format("%s,\"%s\",\"%s\"\n", patientId, diagnoses, treatments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
