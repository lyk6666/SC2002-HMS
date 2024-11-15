package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Appointment;
import scs1_group1.record.Record;


/**
 * Container class for managing appointment records.
 * Provides functionalities for loading, adding, removing, and exporting appointments.
 */
public class AppointmentContainer extends RecordContainer {

    /**
     * Constructs an AppointmentContainer and loads appointments from the given CSV file.
     * 
     * @param appointmentsFilePath Path to the CSV file containing appointment records.
     */

     public AppointmentContainer(String appointmentsFilePath) {
        super();
        loadAppointmentsFromCSV(appointmentsFilePath);
    }


    /**
     * Loads appointments from a CSV file and adds them to the container.
     * 
     * @param filePath Path to the CSV file.
     */

    private void loadAppointmentsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header line
                    continue;
                }
                
                String[] fields = line.split(",");
                String time = fields[0].trim();
                String patientHospitalId = fields[1].trim();
                String doctorHospitalId = fields[2].trim();
                String status = fields[3].trim();
                
                Appointment appointment = new Appointment(time, patientHospitalId, doctorHospitalId, status);
                putRecord(appointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an appointment by its identifier.
     * 
     * @param appointmentIdentifyId The unique identifier of the appointment.
     * @return The Appointment object if found, otherwise null.
     */

    public Appointment getAppointmentByAppointmentIdentifyId(String appointmentIdentifyId) {
        for (Record record : getRecords().values()) {
            if (record instanceof Appointment) {
                Appointment appointment = (Appointment) record;
                if (appointment.getAppointmentIdentifyId().equals(appointmentIdentifyId)) {
                    return appointment;
                }
            }
        }
        return null;
    }

    /**
     * Adds a new appointment to the container with given details.
     * 
     * @param time The time of the appointment.
     * @param patientHospitalId The hospital ID of the patient.
     * @param doctorHospitalId The hospital ID of the doctor.
     */

    public void addAppointment(String time, String patientHospitalId,String doctorHospitalId) {
        Appointment appointment = new Appointment(time,patientHospitalId, doctorHospitalId, "Pending");
        super.putRecord(appointment);
    }
    


    /**
     * Retrieves all appointments for a specific patient with a given status.
     * 
     * @param patientHospitalId The hospital ID of the patient.
     * @param status The status of the appointment.
     * @return A list of Appointment objects.
     */

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


    /**
     * Retrieves all appointments for a specific doctor with a given status.
     * 
     * @param doctorHospitalId The hospital ID of the doctor.
     * @param status The status of the appointment.
     * @return A list of Appointment objects.
     */
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

    /**
     * Removes an appointment from the container by its record ID.
     * 
     * @param appointmentRecordId The unique record ID of the appointment to be removed.
     */
    public void removeAppointment(int appointmentRecordId) {
        super.removeRecord(appointmentRecordId);
    }// the appointment record id is the key of the appointment record in the hashmap, found relevant func in Record.java


    /**
     * Retrieves an appointment by its record ID.
     * 
     * @param appointmentRecordId The record ID of the appointment.
     * @return The Appointment object if found, otherwise null.
     */
    public Appointment getAppointment(int appointmentRecordId) {
        return (Appointment) super.getRecord(appointmentRecordId);
    }


    /**
     * Retrieves all appointments in the container.
     * 
     * @return A list of all Appointment objects.
     */
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


    /**
     * Checks if the given date and time string matches the expected format.
     * 
     * @param dateTime The date and time string to be validated.
     * @return true if the date and time are valid, otherwise false.
     */
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

    /**
     * Exports all appointments to a CSV file.
     * 
     * @param filePath The path of the CSV file to write to.
     */
    public void exportAppointmentToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.write("Time,PatientHospitalId,DoctorHospitalId,Status\n");

            // Write each appointment's details
            for (Appointment appointment : getAllAppointments()) {
                writer.write(String.format("%s,%s,%s,%s\n",
                    appointment.getTime(),
                    appointment.getpatientHospitalId(),
                    appointment.getdoctorHospitalId(),
                    appointment.getStatus()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
