package scs1_group1.container.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Appointment;
import scs1_group1.record.Record;

public class AppointmentContainer extends RecordContainer {
    public AppointmentContainer() {
        super();
    }
    
    //add appointment obj into container，by passing in time, doctor id, patient id
    public void addAppointment(String time, String patientHospitalId,String doctorHospitalId) {
        Appointment appointment = new Appointment(time,patientHospitalId, doctorHospitalId);
        super.putRecord(appointment);
    }
    

    // Get all appointments for a specific patient by patient ID and status and return as a list
    public List<Appointment> getAllAppointmentsByPatientIdAndStatus(String patientHospitalId, String status) {
        List<Appointment> patientAppointments = new ArrayList<>();
        
        // Iterate through all records and filter by patient ID and status
        for (Record record : getRecords().values()) {
            if (record instanceof Appointment) { // Check if it's an Appointment
                Appointment appointment = (Appointment) record;
                if (appointment.getpatientHospitalId().equals(patientHospitalId) && appointment.getStatus().equals(status)) {
                    patientAppointments.add(appointment); // Add matching appointment
                }
            }
        }
        return patientAppointments;
    }

    // Get all appointments for a specific doctor by doctor ID and status and return as a list
    public List<Appointment> getAllAppointmentsByDoctorIdAndStatus(String doctorHospitalId, String status) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        
        // Iterate through all records and filter by doctor ID and status
        for (Record record : getRecords().values()) {
            if (record instanceof Appointment) { // Check if it's an Appointment
                Appointment appointment = (Appointment) record;
                if (appointment.getdoctorHospitalId().equals(doctorHospitalId) && appointment.getStatus().equals(status)) {
                    doctorAppointments.add(appointment); // Add matching appointment
                }
            }
        }
        return doctorAppointments;
    }

    //delete a appointment
    public void removeAppointment(int appointmentRecordId) {
        super.removeRecord(appointmentRecordId);
    }// the appointment record id is the key of the appointment record in the hashmap, found relevant func in Record.java

    // Get a specific appointment by appointment record ID
    public Appointment getAppointment(int appointmentRecordId) {
        return (Appointment) super.getRecord(appointmentRecordId);
    }

    //get all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> allAppointments = new ArrayList<>();
        
        for (Record record : getRecords().values()) {
            if (record instanceof Appointment) { // Check if it's an Appointment
                Appointment appointment = (Appointment) record;
                allAppointments.add(appointment); // Add matching appointment
            }
        }
        return allAppointments;
    }

    public static boolean isValidDateTime(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setLenient(false); // Set to false to ensure strict matching
        try {
            format.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // new functions
    public List<Appointment> getAllAppointmentsByPatientIdDoctorIdAndStatus(String patientHospitalId, String doctorHospitalId, String status) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        // Iterate through all records and filter by patient ID, doctor ID, and status
        for (Record record : getRecords().values()) {
            if (record instanceof Appointment) { // Check if it's an Appointment
                Appointment appointment = (Appointment) record;
                if (appointment.getpatientHospitalId().equals(patientHospitalId) &&
                    appointment.getdoctorHospitalId().equals(doctorHospitalId) &&
                    appointment.getStatus().equals(status)) {
                    filteredAppointments.add(appointment); // Add matching appointment
                }
            }
        }
        return filteredAppointments;
    }

}
