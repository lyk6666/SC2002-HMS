package scs1_group1.record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the medical record of a patient, which contains information such as 
 * diagnoses and treatment plans. Medical records are loaded from a CSV file.
 */
public class MedicalRecord extends Record {
    private String patientHospitalId;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;

    /**
     * Constructs a MedicalRecord for a specific patient based on the provided CSV file path.
     *
     * @param patientHospitalId The hospital ID of the patient.
     * @param medicalRecordPath The path to the CSV file containing medical records.
     */
    public MedicalRecord(String patientHospitalId, String medicalRecordPath) {
        super();
        this.patientHospitalId = patientHospitalId;
        this.treatments = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
        
        // Load data for this specific patientHospitalId
        loadMedicalRecordFromCSV(medicalRecordPath);
    }

    /**
     * Loads the medical record data for the specified patient from a CSV file.
     *
     * @param filePath The path to the CSV file containing medical records.
     */
    private void loadMedicalRecordFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header line
                    continue;
                }
                
                String[] fields = line.split(",");
                String csvPatientHospitalId = fields[0].trim();
                
                // Check if this line corresponds to the current patient's ID
                if (csvPatientHospitalId.equals(this.patientHospitalId)) {
                    // Parse diagnoses and treatments into lists
                    this.diagnoses = new ArrayList<>(Arrays.asList(fields[1].split(";")));
                    this.treatments = new ArrayList<>(Arrays.asList(fields[2].split(";")));
                    break; // Stop after finding the matching record
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representation of the medical record.
     *
     * @return A string representing the patient hospital ID.
     */
    @Override
    public String toString() {
        return "Patient"+patientHospitalId;
    }

    /**
     * Gets the hospital ID of the patient.
     *
     * @return The hospital ID of the patient.
     */
    public String getPatientHospitalId() {
        return patientHospitalId;
    }

    /**
     * Sets the hospital ID of the patient.
     *
     * @param patientHospitalId The hospital ID to set.
     */
    public void setPatientHospitalId(String patientHospitalId) {
        this.patientHospitalId = patientHospitalId;
    }

    /**
     * Gets the treatment plans for the patient.
     *
     * @return A list of treatment plans.
     */
    public ArrayList<String> getTreatments() {
        return treatments;
    }

    /**
     * Updates a treatment plan at a specific index.
     *
     * @param index         The index of the treatment plan to update.
     * @param treatmentPlan The new treatment plan.
     */
    public void setTreatment(int index, String treatmentPlan) {
        if (index >= 0 && index < treatments.size()) {
            treatments.set(index, treatmentPlan);
        }
        else {
            System.out.println("Invalid index");
        }
    }

    /**
     * Adds a new treatment plan to the medical record.
     *
     * @param treatmentPlan The treatment plan to add.
     */
    public void addTreatment(String treatmentPlan) {
        treatments.add(treatmentPlan);
    }

    /**
     * Removes a treatment plan from the medical record at a specific index.
     *
     * @param index The index of the treatment plan to remove.
     */
    public void removeTreatmentPlan(int index) {
        if (index >= 0 && index < treatments.size()) {
            treatments.remove(index);
        }
    }

    /**
     * Gets the diagnoses for the patient.
     *
     * @return A list of diagnoses.
     */
    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }


    /**
     * Updates a diagnosis at a specific index.
     *
     * @param index    The index of the diagnosis to update.
     * @param diagnose The new diagnosis.
     */
    public void setDiagnose(int index, String diagnose) {
        if (index >= 0 && index < diagnoses.size()) {
            diagnoses.set(index, diagnose);
        }
        else {
            System.out.println("Invalid index");
        }
    }

    /**
     * Adds a new diagnosis to the medical record.
     *
     * @param diagnose The diagnosis to add.
     */
    public void addDiagnose(String diagnose) {
        diagnoses.add(diagnose);
    }


    /**
     * Removes a diagnosis from the medical record at a specific index.
     *
     * @param index The index of the diagnosis to remove.
     */
    public void removeDiagnose(int index) {
        if (index >= 0 && index < diagnoses.size()) {
            diagnoses.remove(index);
        }
    }
}
