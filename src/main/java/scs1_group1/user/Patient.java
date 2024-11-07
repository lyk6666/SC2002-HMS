package scs1_group1.user;

import java.util.ArrayList;

import scs1_group1.record.MedicalRecord;

public class Patient extends User {
    private String dateOfBirth;
    private String phoneNumber;
    private String bloodType;
    private MedicalRecord medicalRecord; // Each patient has their own MedicalRecord

    public Patient(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        
        String dateOfBirth,
        String phoneNumber,
        String bloodType
        ) 
    {
        super(hospitalId, password, name, gender, userType,email);

        this.dateOfBirth = dateOfBirth; 
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.medicalRecord = new MedicalRecord(hospitalId); // Initialize MedicalRecord with patient hospital ID
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    // Medical Record
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
    
    //get all treatments
    public ArrayList<String> getTreatments() {
        return medicalRecord.getTreatments();
    }

    //edit treatment plan by index
    public void setTreatment(int index, String treatmentPlan) {
        medicalRecord.setTreatment(index, treatmentPlan);
    }

    public void addTreatment(String treatmentPlan) {
        medicalRecord.addTreatment(treatmentPlan);
    }

    public void removeTreatmentPlan(int index) {
        medicalRecord.removeTreatmentPlan(index);
    }

    //get all diagnoses
    public ArrayList<String> getDiagnoses() {
        return medicalRecord.getDiagnoses();
    }

    //edit diagnose by index
    public void setDiagnose(int index, String diagnose) {
        medicalRecord.setDiagnose(index, diagnose);
    }

    public void addDiagnose(String diagnose) {
        medicalRecord.addDiagnose(diagnose);
    }

    public void removeDiagnose(int index) {
        medicalRecord.removeDiagnose(index);
    }

}
