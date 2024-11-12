package scs1_group1.menu;
import java.util.List;
import java.util.Scanner;

import scs1_group1.container.data.AppointmentContainer;
import scs1_group1.container.data.AppointmentOutcomeRecordContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.record.Appointment;
import scs1_group1.record.AppointmentOutcomeRecord;
import scs1_group1.record.MedicalRecord;
import scs1_group1.record.Prescription;
import scs1_group1.user.Doctor;
import scs1_group1.user.Patient;
public class PatientMenu extends Menu {
    private String patientHospitalId;
    private Patient patient;
    private DoctorContainer doctorContainer;
    private AppointmentContainer appointmentContainer;
    private AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer;

    public PatientMenu(
        String hospitalId ,
        PatientContainer patientContainer, DoctorContainer doctorContainer, AppointmentContainer appointmentContainer, AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer) 
    {
        this.patientHospitalId = hospitalId;
        patient=(Patient)patientContainer.getUserByHospitalId(patientHospitalId);
        this.doctorContainer=doctorContainer;
        this.appointmentContainer=appointmentContainer;
        this.appointmentOutcomeRecordContainer=appointmentOutcomeRecordContainer;
    }


    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Patient Menu");
            System.out.println("0. Log out");
            System.out.println("1. Medical Record");
            System.out.println("2. Contact information");
            System.out.println("3. Appointment");
            System.out.println("4. Appointment Outcome Record");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    viewMedicalRecord(); break;
                case 2:
                    viewContactInformation(); break;
                case 3:
                    manageAppointment(); break;
                case 4:
                    manageAppointmentOutcomeRecord(); break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=0);
    }

    private void manageAppointmentOutcomeRecord() {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Filter options:");
        System.out.println("1. Filter by Doctor");
        System.out.println("2. Filter by Date");
        System.out.print("Enter your choice (1 or 2): ");
        int filterChoice = sc.nextInt();
        sc.nextLine(); // Clear buffer
    
        List<Appointment> appointments;
    
        // Apply the chosen filter
        switch (filterChoice) {
            case 1: // Filter by doctor
                System.out.print("Enter Doctor's Hospital ID: ");
                String doctorId = sc.nextLine();
                appointments = appointmentContainer.getAllAppointmentsByPatientIdDoctorIdAndStatus(patientHospitalId, doctorId, "completed");
                break;
            case 2: // Filter by date (YYYY-MM-DD only)
                System.out.print("Enter Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                // Filter appointments by date part only (YYYY-MM-DD)
                appointments = appointmentContainer.getAllAppointmentsByPatientIdAndStatus(patientHospitalId, "completed").stream()
                        .filter(appointment -> appointment.getTime().substring(0, 10).equals(date))
                        .toList();
                break;
            default:
                System.out.println("Invalid choice. Returning to the previous menu.");
                return;
        }
    
        // Display the filtered appointments
        System.out.println("Completed Appointments:");
        System.out.printf("%-5s %-20s %-20s %-20s %-20s%n", "No.", "Date & Time", "Patient ID", "Doctor ID","Status");
        System.out.println("------------------------------------------------------------");
    
        if (appointments.isEmpty()) {
            System.out.println("No completed appointments found for the selected filter.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                String patientId = appointment.getpatientHospitalId();
                String doctorId = appointment.getdoctorHospitalId();
                System.out.printf("%-5s %-20s %-20s %-20s %-20s%n", (i + 1), appointment.getTime(), patientId, doctorId,appointment.getStatus());
            }
    
            // Prompt patient to select a completed appointment to view its outcome record
            System.out.println("----------------------------------------");
            System.out.print("Enter the number of the completed appointment to view its outcome record (or 0 to go back): ");
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice == 0) {
                        System.out.println("Returning to previous menu...");
                        return;
                    } else if (choice >= 1 && choice <= appointments.size()) {
                        break; // Valid choice to view an outcome record
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + appointments.size());
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Fetch and display the Appointment Outcome Record
            Appointment selectedAppointment = appointments.get(choice - 1);
            AppointmentOutcomeRecord outcomeRecord = appointmentOutcomeRecordContainer.getAppointmentOutcomeRecordById(selectedAppointment.getRecordId());
    
            if (outcomeRecord != null) {
                System.out.println("----------------------------------------");
                System.out.println("\nAppointment Outcome Record completed for the appointment with doctor ID: " + selectedAppointment.getdoctorHospitalId() +
                                   " Name: " + doctorContainer.getDoctorByHospitalId(selectedAppointment.getdoctorHospitalId()).getName() +
                                   " on " + selectedAppointment.getTime());
                System.out.println("Service Type: " + outcomeRecord.getServiceType());
                System.out.println("Consultation Notes: " + outcomeRecord.getConsultationNotes());
                System.out.println("\nPrescriptions:");
    
                if (outcomeRecord.getPrescriptions().isEmpty()) {
                    System.out.println("No prescriptions recorded.");
                } else {
                    System.out.printf("%-5s %-20s %-10s %-10s%n", "No.", "Medication", "Amount", "Status");
                    for (int i = 0; i < outcomeRecord.getPrescriptions().size(); i++) {
                        Prescription prescription = outcomeRecord.getPrescriptions().get(i);
                        System.out.printf("%-5d %-20s %-10d %-10s%n",
                                          (i + 1),
                                          prescription.getMedicine(),
                                          prescription.getAmount(),
                                          prescription.getStatus());
                    }
                }
                System.out.println("----------------------------------------");
            } else {
                System.out.println("No outcome record found for this appointment.");
            }
        }
    }    

    private void viewContactInformation() {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("----------------------------------------");
            System.out.println("Contact Information");
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Phone: " + patient.getPhoneNumber());
            System.out.println("----------------------------------------");
            System.out.println("0. Back");
            System.out.println("1. Change Email");
            System.out.println("2. Change Phone Number");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next(); // Clear the invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer
            
            switch (choice) {
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                    
                case 1:
                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();
                    patient.setEmail(newEmail);
                    System.out.println("Email changed to " + newEmail);
                    break;
                    
                case 2:
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = sc.nextLine();
                    patient.setPhoneNumber(newPhoneNumber);
                    System.out.println("Phone number changed to " + newPhoneNumber);
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void manageAppointment() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Appointment Management Menu");
            System.out.println("0. Back");
            System.out.println("1. Schedule an Appointment");
            System.out.println("2. Upcoming Appointments");
    
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer
    
            switch (choice) {
                case 0:
                    System.out.println("Returning to previous menu...");
                    break;
                case 1:
                    scheduleAppointment();
                    break;
                case 2:
                    UpcomingAppointments();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private void scheduleAppointment() {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Available Doctors:");
        System.out.printf("%-15s %-20s%n", "Hospital ID", "Doctor Name");
        System.out.println("----------------------------------------");
    
        // Display all doctors
        doctorContainer.getAllDoctors().values().forEach(doctor -> {
            System.out.printf("%-15s %-20s%n", doctor.getHospitalId(), doctor.getName());
        });
    
        System.out.println("----------------------------------------");
        System.out.print("Enter doctor's hospital ID: ");
        String doctorHospitalId = sc.nextLine();
    
        Doctor doctor = doctorContainer.getDoctorByHospitalId(doctorHospitalId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
    
        // Display available slots for the chosen doctor
        List<String> availableSlots = doctor.getAvailableSlots();
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for this doctor.");
            return;
        }
    
        System.out.println("\nAvailable Slots:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableSlots.get(i));
        }
        System.out.println("----------------------------------------");
    
        System.out.print("Enter the number of the slot you want to book: ");
        int slotChoice;
        while (true) {
            if (sc.hasNextInt()) {
                slotChoice = sc.nextInt();
                sc.nextLine(); // Clear buffer
                if (slotChoice >= 1 && slotChoice <= availableSlots.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + availableSlots.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
            }
        }
    
        // Book the selected slot
        String chosenSlot = availableSlots.get(slotChoice - 1);
    
        // Assume thisPatientHospitalId is a variable holding the current patient's hospital ID
    
        appointmentContainer.addAppointment(chosenSlot, patientHospitalId,doctorHospitalId);
        doctor.removeAvailableSlot(chosenSlot);    
        System.out.println("Appointment scheduled successfully with " + doctor.getName() + " for " + chosenSlot + ".");
    }
    
    private void UpcomingAppointments() {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Upcoming Appointments:");
        System.out.printf("%-5s %-15s %-20s %-20s %-20s%n", "No.", "Date & Time", "Doctor ID", "Doctor Name", "Status");
        System.out.println("-----------------------------------------------------------------------");
    
        // Retrieve all appointments for the patient
        List<Appointment> allAppointments = appointmentContainer.getAllAppointmentsByPatientIdAndStatus(patientHospitalId, "confirmed");
        allAppointments.addAll(appointmentContainer.getAllAppointmentsByPatientIdAndStatus(patientHospitalId, "pending"));
    
        if (allAppointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
            return;
        }
    
        // Display appointments with numbering
        for (int i = 0; i < allAppointments.size(); i++) {
            Appointment appointment = allAppointments.get(i);
            String doctorHospitalId = appointment.getdoctorHospitalId();
            Doctor doctor = doctorContainer.getDoctorByHospitalId(doctorHospitalId);
            String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
    
            System.out.printf("%-5d %-15s %-20s %-20s %-20s%n",
                    (i + 1), appointment.getTime(), doctorHospitalId, doctorName, appointment.getStatus());
        }
    
        // Select appointment to modify or cancel
        System.out.println("----------------------------------------");
        System.out.print("Enter the number of the appointment you want to modify/cancel (or 0 to go back): ");
        int choice;
        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // Clear buffer
                if (choice >= 0 && choice <= allAppointments.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + allAppointments.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
            }
        }
    
        // If the user wants to go back
        if (choice == 0) {
            System.out.println("Returning to previous menu...");
            return;
        }
    
        Appointment selectedAppointment = allAppointments.get(choice - 1);
        System.out.println("----------------------------------------");
        System.out.println("You selected the appointment with "+ doctorContainer.getDoctorByHospitalId(selectedAppointment.getdoctorHospitalId()).getName() + " time " + selectedAppointment.getTime());
        System.out.println("0. Cancel the appointment");
        System.out.println("1. Modify the appointment");
        System.out.print("Enter your choice: ");
        String action = sc.nextLine().trim().toUpperCase();
    
        if (action.equals("1")) {
            modifyAppointment(selectedAppointment);
        } else if (action.equals("0")) {
            cancelAppointment(selectedAppointment);
        } else {
            System.out.println("Invalid choice.");
        }
    }
    
    // Method to modify an appointment
    private void modifyAppointment(Appointment appointment) {
        Scanner sc = new Scanner(System.in);
        Doctor doctor = doctorContainer.getDoctorByHospitalId(appointment.getdoctorHospitalId());
    
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
    
        // Display available slots for the doctor
        List<String> availableSlots = doctor.getAvailableSlots();
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots to modify the appointment.");
            return;
        }
    
        System.out.println("\nAvailable Slots:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableSlots.get(i));
        }
        System.out.println("----------------------------------------");
    
        System.out.print("Enter the number of the slot you want to reschedule to: ");
        int slotChoice;
        while (true) {
            if (sc.hasNextInt()) {
                slotChoice = sc.nextInt();
                sc.nextLine(); // Clear buffer
                if (slotChoice >= 1 && slotChoice <= availableSlots.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + availableSlots.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
            }
        }
    
        // Update the appointment with the new slot and remove old slot from availability
        String newSlot = availableSlots.get(slotChoice - 1);
        doctor.removeAvailableSlot(newSlot); // Book new slot
        doctor.addAvailableSlot(appointment.getTime()); // Free up old slot
        appointment.setTime(newSlot); // Update appointment time
        appointment.setStatus("pending");
        System.out.println("Appointment with "+doctorContainer.getDoctorByHospitalId(appointment.getdoctorHospitalId()).getName()+ " rescheduled successfully to " + newSlot + ".");
    }
    
    // Method to cancel an appointment
    private void cancelAppointment(Appointment appointment) {
        appointmentContainer.removeAppointment(appointment.getRecordId());
        System.out.println("Appointment cancelled successfully.");
    }
    
    private void viewMedicalRecord() {
        String name = patient.getName();
        String dateOfBirth = patient.getDateOfBirth();
        String gender = patient.getGender();
        String bloodType = patient.getBloodType();
        MedicalRecord medicalRecord = patient.getMedicalRecord();
    
        System.out.println("----------------------------------------");
        System.out.println("Medical Record of"+name);
        System.out.println("Patient Id: " + medicalRecord.getPatientHospitalId());
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Blood Type: " + bloodType);
    
        // Print all diagnoses
        System.out.println("Diagnoses:");
        if (medicalRecord.getDiagnoses().isEmpty()) {
            System.out.println("No diagnoses available.");
        } else {
            for (String diagnosis : medicalRecord.getDiagnoses()) {
                System.out.println("- " + diagnosis);
            }
        }
    
        // Print all treatment plans
        System.out.println("Treatment Plans:");
        if (medicalRecord.getTreatments().isEmpty()) {
            System.out.println("No treatment plans available.");
        } else {
            for (String treatment : medicalRecord.getTreatments()) {
                System.out.println("- " + treatment);
            }
        }
    }   
}