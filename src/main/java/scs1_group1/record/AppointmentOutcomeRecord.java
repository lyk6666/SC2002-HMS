package scs1_group1.record;

import java.util.ArrayList;

import scs1_group1.container.data.RecordContainer;

public class AppointmentOutcomeRecord extends Record {
    private int appointmentRecordId;
    private String serviceType;
    private String patientHospitalId;
    private String doctorHospitalId;
    private final ArrayList<Prescription> prescriptions;
    private String consultationNotes;  // Changed to single String

    public AppointmentOutcomeRecord(int appointmentRecordId, String patientHospitalId, String doctorHospitalId, String serviceType, String consultationNotes) {
        super();
        this.appointmentRecordId = appointmentRecordId;
        this.patientHospitalId = patientHospitalId;
        this.doctorHospitalId = doctorHospitalId;
        this.serviceType = serviceType;
        this.prescriptions = new ArrayList<>();
        this.consultationNotes = consultationNotes;
    }

    @Override
    public String toString() {
        return serviceType;
    }

    //get the appointment record id
    public int getAppointmentRecordId() {
        return appointmentRecordId;
    }

    //get the patient hospital id
    public String getPatientHospitalId() {
        return patientHospitalId;
    }

    // Date
    public String getTime(RecordContainer appointmentContainer) {
        return ((Appointment) (appointmentContainer.getRecord(appointmentRecordId))).getTime();
    }

    // Service Type
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    // Prescriptions
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(String medicineName, int amount) {
        Prescription prescription = new Prescription(medicineName, amount);
        prescriptions.add(prescription);
    }

    public String getMedication(int prescriptionIndex) {
        return prescriptions.get(prescriptionIndex).getMedicine();
    }

    public void setMedication(int prescriptionIndex, String medication) {
        prescriptions.get(prescriptionIndex).setMedicine(medication);
    }

    public String getStatus(int prescriptionIndex) {
        return prescriptions.get(prescriptionIndex).getStatus();
    }

    public void setStatus(int prescriptionIndex, String status) {
        prescriptions.get(prescriptionIndex).setStatus(status);
    }

    // Consultation Notes (changed to single String)
    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
}
