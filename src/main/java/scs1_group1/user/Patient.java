package scs1_group1.user;

import java.util.ArrayList;

import scs1_group1.record.MedicalRecord;

/**
 * Represents a patient in the hospital system.
 * 
 * The Patient class extends the User class and includes additional information such as date of birth,
 * phone number, blood type, and a medical record.
 * Each patient has their own unique MedicalRecord which stores diagnoses and treatments.
 */
public class Patient extends User {
    private String dateOfBirth;
    private String phoneNumber;
    private String bloodType;
    private MedicalRecord medicalRecord; // Each patient has their own MedicalRecord


    /**
     * Constructs a new Patient instance.
     * 
     * @param hospitalId       The unique ID of the patient in the hospital system.
     * @param password         The password used for the patient's login.
     * @param name             The name of the patient.
     * @param gender           The gender of the patient.
     * @param userType         The user type indicating the role (in this case, "Patient").
     * @param email            The email address of the patient.
     * @param dateOfBirth      The date of birth of the patient.
     * @param phoneNumber      The contact phone number of the patient.
     * @param bloodType        The blood type of the patient.
     * @param medicalRecordPath The file path to load the patient's medical record data from.
     */
    public Patient(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        
        String dateOfBirth,
        String phoneNumber,
        String bloodType,

        String medicalRecordPath
        ) 
    {
        super(hospitalId, password, name, gender, userType,email);

        this.dateOfBirth = dateOfBirth; 
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.medicalRecord = new MedicalRecord(hospitalId,medicalRecordPath); // Initialize MedicalRecord with patient hospital ID and path to the MedicalRecord CSV
    }

    /**
     * Gets the date of birth of the patient.
     * 
     * @return The date of birth of the patient.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the patient.
     * 
     * @param dateOfBirth The new date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the phone number of the patient.
     * 
     * @return The phone number of the patient.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phone number of the patient.
     * 
     * @param phoneNumber The new phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the blood type of the patient.
     * 
     * @return The blood type of the patient.
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * Sets the blood type of the patient.
     * 
     * @param bloodType The new blood type to set.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Gets the medical record of the patient.
     * 
     * @return The medical record of the patient.
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
    
    /**
     * Gets the list of all treatments in the patient's medical record.
     * 
     * @return A list of all treatments.
     */
    public ArrayList<String> getTreatments() {
        return medicalRecord.getTreatments();
    }

    /**
     * Edits a treatment plan in the patient's medical record by index.
     * 
     * @param index        The index of the treatment to edit.
     * @param treatmentPlan The new treatment plan to set.
     */
    public void setTreatment(int index, String treatmentPlan) {
        medicalRecord.setTreatment(index, treatmentPlan);
    }

    /**
     * Adds a new treatment to the patient's medical record.
     * 
     * @param treatmentPlan The treatment plan to add.
     */
    public void addTreatment(String treatmentPlan) {
        medicalRecord.addTreatment(treatmentPlan);
    }

    /**
     * Removes a treatment plan from the patient's medical record by index.
     * 
     * @param index The index of the treatment plan to remove.
     */
    public void removeTreatmentPlan(int index) {
        medicalRecord.removeTreatmentPlan(index);
    }

    /**
     * Gets the list of all diagnoses in the patient's medical record.
     * 
     * @return A list of all diagnoses.
     */
    public ArrayList<String> getDiagnoses() {
        return medicalRecord.getDiagnoses();
    }

    /**
     * Edits a diagnosis in the patient's medical record by index.
     * 
     * @param index    The index of the diagnosis to edit.
     * @param diagnose The new diagnosis to set.
     */
    public void setDiagnose(int index, String diagnose) {
        medicalRecord.setDiagnose(index, diagnose);
    }

    /**
     * Adds a new diagnosis to the patient's medical record.
     * 
     * @param diagnose The diagnosis to add.
     */
    public void addDiagnose(String diagnose) {
        medicalRecord.addDiagnose(diagnose);
    }

    /**
     * Removes a diagnosis from the patient's medical record by index.
     * 
     * @param index The index of the diagnosis to remove.
     */
    public void removeDiagnose(int index) {
        medicalRecord.removeDiagnose(index);
    }

}
