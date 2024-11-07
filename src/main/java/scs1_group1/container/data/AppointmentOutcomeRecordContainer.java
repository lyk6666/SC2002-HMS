package scs1_group1.container.data;

import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Appointment;
import scs1_group1.record.AppointmentOutcomeRecord;
import scs1_group1.record.Record;



public class AppointmentOutcomeRecordContainer extends RecordContainer {
    public AppointmentOutcomeRecordContainer() {
        super();
    }

    // a AppointmentOutcomeRecord refer and only refer to one appointment record, so this method only pass in the appointment object
    public void addAppointmentOutcomeRecord(Appointment appointment, String serviceType, String consultationNotes) {
        // Extract details from the Appointment object
        int appointmentRecordId = appointment.getRecordId();
        String patientHospitalId = appointment.getpatientHospitalId();
        String doctorHospitalId = appointment.getdoctorHospitalId();
    
        // Create a new AppointmentOutcomeRecord with the extracted information
        AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord(
            appointmentRecordId,
            patientHospitalId,
            doctorHospitalId,
            serviceType,
            consultationNotes
        );
    
        // Add the AppointmentOutcomeRecord to the container
        super.putRecord(appointmentOutcomeRecord);
    }    
    
    public AppointmentOutcomeRecord getAppointmentOutcomeRecordById(int appointmentRecordId) {
        // Iterate through all records in the container
        for (Record record : getRecords().values()) {
            if (record instanceof AppointmentOutcomeRecord) {
                AppointmentOutcomeRecord outcomeRecord = (AppointmentOutcomeRecord) record;
                if (outcomeRecord.getAppointmentRecordId() == appointmentRecordId) {
                    return outcomeRecord; // Return the matching record
                }
            }
        }
        return null; // Return null if no matching record is found
    }

    // Get all appointment outcome records of a specific patient ID
    public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordsByPatientId(String patientHospitalId) {
        List<AppointmentOutcomeRecord> outcomeRecords = new ArrayList<>();

        // Iterate through all records in the container
        for (Record record : getRecords().values()) {
            if (record instanceof AppointmentOutcomeRecord) {
                AppointmentOutcomeRecord outcomeRecord = (AppointmentOutcomeRecord) record;
                if (outcomeRecord.getPatientHospitalId().equals(patientHospitalId)) {
                    outcomeRecords.add(outcomeRecord); // Add matching record to the list
                }
            }
        }
        return outcomeRecords; // Return the list of matching records (empty if none found)
    }

    

}
