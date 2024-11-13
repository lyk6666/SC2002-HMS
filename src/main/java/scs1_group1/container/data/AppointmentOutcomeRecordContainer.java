package scs1_group1.container.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scs1_group1.record.Appointment;
import scs1_group1.record.AppointmentOutcomeRecord;
import scs1_group1.record.Prescription;
import scs1_group1.record.Record;



public class AppointmentOutcomeRecordContainer extends RecordContainer {
    public AppointmentOutcomeRecordContainer(String filePath) {
        super();
        loadAppointmentOutcomeRecords(filePath);
    }

    private void loadAppointmentOutcomeRecords(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip the header line
                }
                String[] fields = line.split(",");
                String appointmentRecordId = fields[0].trim();
                String patientHospitalId = fields[1].trim();
                String doctorHospitalId = fields[2].trim();
                String serviceType = fields[3].trim();
                String consultationNotes = fields[4].trim();

                // Create AppointmentOutcomeRecord object and load prescriptions within the class
                AppointmentOutcomeRecord record = new AppointmentOutcomeRecord(
                    appointmentRecordId,
                    patientHospitalId,
                    doctorHospitalId,
                    serviceType,
                    consultationNotes
                );

                // The record itself will load prescriptions from CSV if needed
                super.putRecord(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // a AppointmentOutcomeRecord refer and only refer to one appointment record, so this method only pass in the appointment object
    public void addAppointmentOutcomeRecord(Appointment appointment, String serviceType, String consultationNotes) {
        // Extract details from the Appointment object
        String appointmentRecordId = appointment.getAppointmentIdentifyId();
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
    
    public AppointmentOutcomeRecord getAppointmentOutcomeRecordById(String appointmentRecordId) {
        // Iterate through all records in the container
        for (Record record : getRecords().values()) {
            if (record instanceof AppointmentOutcomeRecord) {
                AppointmentOutcomeRecord outcomeRecord = (AppointmentOutcomeRecord) record;
                if (outcomeRecord.getAppointmentRecordId().equals(appointmentRecordId)) {
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

    // Get all appointment outcome records
    public List<AppointmentOutcomeRecord> getAllAppointmentOutcomeRecords() {
        List<AppointmentOutcomeRecord> outcomeRecords = new ArrayList<>();

        // Iterate through all records in the container
        for (Record record : getRecords().values()) {
            if (record instanceof AppointmentOutcomeRecord) {
                AppointmentOutcomeRecord outcomeRecord = (AppointmentOutcomeRecord) record;
                outcomeRecords.add(outcomeRecord); // Add record to the list
            }
        }
        return outcomeRecords; // Return the list of records
    }

    // Method to export all AppointmentOutcomeRecords to CSV
    public void exportAppointmentOutcomeRecordToCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.write("AppointmentRecordId,PatientHospitalId,DoctorHospitalId,ServiceType,ConsultationNotes\n");

            // Write each AppointmentOutcomeRecord's details
            for (AppointmentOutcomeRecord record : getAllAppointmentOutcomeRecords()) {
                writer.write(String.format("%s,%s,%s,%s,\"%s\"\n",
                    record.getAppointmentRecordId(),
                    record.getPatientHospitalId(),
                    record.getDoctorHospitalId(),
                    record.getServiceType(),
                    record.getConsultationNotes()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to export prescriptions to Prescriptions_List.csv
    public void exportPrescriptionsToCSV(String prescriptions_list) {
        try (FileWriter writer = new FileWriter(prescriptions_list)) {
            // Write header
            writer.write("AppointmentRecordId,Medicine,Amount,Status\n");

            // Write each prescription for each AppointmentOutcomeRecord
            for (AppointmentOutcomeRecord record : getAllAppointmentOutcomeRecords()) {
                String appointmentRecordId = record.getAppointmentRecordId();

                for (Prescription prescription : record.getPrescriptions()) {
                    writer.write(String.format("%s,%s,%d,%s\n",
                        appointmentRecordId,
                        prescription.getMedicine(),
                        prescription.getAmount(),
                        prescription.getStatus()
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
