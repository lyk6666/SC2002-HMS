package scs1_group1.record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an appointment outcome record, which contains information about the 
 * outcome of an appointment, including consultation notes, service type, and prescriptions.
 */
public class AppointmentOutcomeRecord extends Record {
    private static final String prescriptions_list = "data/Prescriptions_List.csv";

    private String appointmentRecordId;
    private String serviceType;
    private String patientHospitalId;
    private String doctorHospitalId;
    private final ArrayList<Prescription> prescriptions;
    private String consultationNotes;  // Changed to single String

    /**
     * Constructs an AppointmentOutcomeRecord instance with the specified details.
     *
     * @param appointmentRecordId The unique identifier of the appointment.
     * @param patientHospitalId   The hospital ID of the patient involved in the appointment.
     * @param doctorHospitalId    The hospital ID of the doctor involved in the appointment.
     * @param serviceType         The type of service provided during the appointment.
     * @param consultationNotes   Notes from the consultation.
     */
    public AppointmentOutcomeRecord(String appointmentRecordId, String patientHospitalId, String doctorHospitalId, String serviceType, String consultationNotes) {
        super();
        this.appointmentRecordId = appointmentRecordId;
        this.patientHospitalId = patientHospitalId;
        this.doctorHospitalId = doctorHospitalId;
        this.serviceType = serviceType;
        this.prescriptions = new ArrayList<>();
        this.consultationNotes = consultationNotes;
        
        loadPrescriptions();
    }

    /**
     * Loads prescription details from a CSV file and adds them to the list of prescriptions.
     * The prescriptions are filtered based on the appointment ID to match this instance.
     */
    private void loadPrescriptions() {
        try (BufferedReader br = new BufferedReader(new FileReader(prescriptions_list))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4) {
                    String fileAppointmentRecordId = fields[0].trim();
                    if (fileAppointmentRecordId.equals(this.appointmentRecordId)) {
                        String medicine = fields[1].trim();
                        int amount = Integer.parseInt(fields[2].trim());
                        String status = fields[3].trim();

                        Prescription prescription = new Prescription(medicine, amount, status);
                        prescriptions.add(prescription);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representation of the service type.
     *
     * @return A string representing the service type of the appointment.
     */
    @Override
    public String toString() {
        return serviceType;
    }

    /**
     * Returns the appointment record ID.
     *
     * @return The unique identifier for this appointment.
     */
    public String getAppointmentRecordId() {
        return appointmentRecordId;
    }

    /**
     * Returns the hospital ID of the patient involved in the appointment.
     *
     * @return The patient hospital ID.
     */
    public String getPatientHospitalId() {
        return patientHospitalId;
    }


    /**
     * Returns the hospital ID of the doctor involved in the appointment.
     *
     * @return The doctor hospital ID.
     */
    public String getDoctorHospitalId() {
        return doctorHospitalId;
    }


    /**
     * Returns the service type of the appointment.
     *
     * @return The service type provided during the appointment.
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the service type for the appointment.
     *
     * @param serviceType The new service type to set.
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Returns the list of prescriptions associated with the appointment.
     *
     * @return A list of Prescription objects.
     */
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }


    /**
     * Adds a new prescription to the list of prescriptions.
     *
     * @param medicineName The name of the prescribed medicine.
     * @param amount       The quantity of the prescribed medicine.
     */
    public void addPrescription(String medicineName, int amount) {
        Prescription prescription = new Prescription(medicineName, amount, "Pending");
        prescriptions.add(prescription);
    }

    /**
     * Returns the name of the medication for a specific prescription.
     *
     * @param prescriptionIndex The index of the prescription in the list.
     * @return The name of the prescribed medication.
     */
    public String getMedication(int prescriptionIndex) {
        return prescriptions.get(prescriptionIndex).getMedicine();
    }

    /**
     * Updates the name of the medication for a specific prescription.
     *
     * @param prescriptionIndex The index of the prescription in the list.
     * @param medication        The new name of the medication.
     */
    public void setMedication(int prescriptionIndex, String medication) {
        prescriptions.get(prescriptionIndex).setMedicine(medication);
    }

    /**
     * Returns the status of a specific prescription.
     *
     * @param prescriptionIndex The index of the prescription in the list.
     * @return The status of the prescription (e.g., "Pending", "Dispensed").
     */
    public String getStatus(int prescriptionIndex) {
        return prescriptions.get(prescriptionIndex).getStatus();
    }


    /**
     * Updates the status of a specific prescription.
     *
     * @param prescriptionIndex The index of the prescription in the list.
     * @param status            The new status of the prescription (e.g., "Pending", "Dispensed").
     */
    public void setStatus(int prescriptionIndex, String status) {
        prescriptions.get(prescriptionIndex).setStatus(status);
    }


    /**
     * Returns the consultation notes for the appointment.
     *
     * @return The consultation notes.
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * Sets the consultation notes for the appointment.
     *
     * @param consultationNotes The new consultation notes.
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
}
