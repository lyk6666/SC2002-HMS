package scs1_group1.record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppointmentOutcomeRecord extends Record {
    private static final String prescriptions_list = "data/Prescriptions_List.csv";

    private String appointmentRecordId;
    private String serviceType;
    private String patientHospitalId;
    private String doctorHospitalId;
    private final ArrayList<Prescription> prescriptions;
    private String consultationNotes;  // Changed to single String

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

    @Override
    public String toString() {
        return serviceType;
    }

    //get the appointment record id
    public String getAppointmentRecordId() {
        return appointmentRecordId;
    }

    //get the patient hospital id
    public String getPatientHospitalId() {
        return patientHospitalId;
    }

    //get the doctor hospital id
    public String getDoctorHospitalId() {
        return doctorHospitalId;
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
        Prescription prescription = new Prescription(medicineName, amount, "Pending");
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
