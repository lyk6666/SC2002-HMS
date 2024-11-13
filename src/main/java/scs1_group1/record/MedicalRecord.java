package scs1_group1.record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MedicalRecord extends Record {
    private String patientHospitalId;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;

    public MedicalRecord(String patientHospitalId, String medicalRecordPath) {
        super();
        this.patientHospitalId = patientHospitalId;
        this.treatments = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
        
        // Load data for this specific patientHospitalId
        loadMedicalRecordFromCSV(medicalRecordPath);
    }

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

    @Override
    public String toString() {
        return "Patient"+patientHospitalId;
    }

    //Patient Hospital ID
    public String getPatientHospitalId() {
        return patientHospitalId;
    }

    public void setPatientHospitalId(String patientHospitalId) {
        this.patientHospitalId = patientHospitalId;
    }

    //Treatment Plans
    public ArrayList<String> getTreatments() {
        return treatments;
    }

    //edit treatment plan by index
    public void setTreatment(int index, String treatmentPlan) {
        if (index >= 0 && index < treatments.size()) {
            treatments.set(index, treatmentPlan);
        }
        else {
            System.out.println("Invalid index");
        }
    }

    public void addTreatment(String treatmentPlan) {
        treatments.add(treatmentPlan);
    }

    public void removeTreatmentPlan(int index) {
        if (index >= 0 && index < treatments.size()) {
            treatments.remove(index);
        }
    }

    //Diagnoses
    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }

    //edit diagnose by index
    public void setDiagnose(int index, String diagnose) {
        if (index >= 0 && index < diagnoses.size()) {
            diagnoses.set(index, diagnose);
        }
        else {
            System.out.println("Invalid index");
        }
    }

    public void addDiagnose(String diagnose) {
        diagnoses.add(diagnose);
    }

    public void removeDiagnose(int index) {
        if (index >= 0 && index < diagnoses.size()) {
            diagnoses.remove(index);
        }
    }
}
