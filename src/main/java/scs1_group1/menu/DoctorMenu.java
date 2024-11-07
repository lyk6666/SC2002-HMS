package scs1_group1.menu;

import java.util.List;
import java.util.Scanner;

import scs1_group1.container.data.AppointmentContainer;
import scs1_group1.container.data.AppointmentOutcomeRecordContainer;
import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.record.Appointment;
import scs1_group1.record.AppointmentOutcomeRecord;
import scs1_group1.record.Medicine;
import scs1_group1.record.Prescription;
import scs1_group1.user.Doctor;

public class DoctorMenu extends Menu {
    private String doctorHospitalId;
    private Doctor doctor;
    private PatientContainer patientContainer;
    private AppointmentContainer appointmentContainer;
    private AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer;
    private MedicineContainer medicineContainer;


    //constructor
    public DoctorMenu(String hospitalId, DoctorContainer doctorContainer, PatientContainer patientContainer, AppointmentContainer appointmentContainer, AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer, MedicineContainer medicineContainer) {
        this.doctorHospitalId = hospitalId;
        doctor = (Doctor) doctorContainer.getUserByHospitalId(doctorHospitalId);//here dont need this keyword because the parameter name is different
        this.patientContainer = patientContainer;
        this.appointmentContainer = appointmentContainer;
        this.appointmentOutcomeRecordContainer = appointmentOutcomeRecordContainer;
        this.medicineContainer = medicineContainer;
    }
   
    @Override
    public void run() {

        //put some sample appointments for testing
        appointmentContainer.addAppointment("2021-10-01 10:00", "P1001", doctorHospitalId);
        appointmentContainer.addAppointment("2021-10-01 11:00", "P1002", doctorHospitalId);
        appointmentContainer.addAppointment("2021-10-01 14:00", "P1003", doctorHospitalId);

        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Doctor Menu");
            System.out.println("0. Log out");
            System.out.println("1. Medical record of patient under my care");
            System.out.println("2. Appointments");
            System.out.println("3. My Availability");
            System.out.println("4. Add a new patient to my care");

            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    MedicalRecord(); break;
                case 2:
                    MyAppointments(); break;
                case 3: 
                    setAvailability(); break;
                case 4:
                    addPatientUnderCare(); break;                    
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=0);
    }

    private void MyAppointments() {
        Scanner sc = new Scanner(System.in);
        int appointmentChoice;
    
        do {
            System.out.println("----------------------------------------");
            System.out.println("Appointments Menu");
            System.out.println("0. Log out");
            System.out.println("1. View Pending Appointments");
            System.out.println("2. View Confirmed Appointments");
            System.out.println("3. View Completed Appointments");
    
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); // Clear the invalid input
            }
            appointmentChoice = sc.nextInt();
            sc.nextLine(); // Clear buffer after reading integer choice
    
            switch (appointmentChoice) {
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                case 1:
                    pendingAppointments();
                    break;
                case 2:
                    confirmedAppointments();
                    break;
                case 3:
                    completedAppointments();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (appointmentChoice != 0);
    }

    private void pendingAppointments() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pending Appointments:");
        System.out.printf("%-5s %-15s %-20s %-20s%n", "No.", "Date & Time", "Patient ID", "Status");
        System.out.println("------------------------------------------------------------");
    
        List<Appointment> appointments = appointmentContainer.getAllAppointmentsByDoctorIdAndStatus(doctorHospitalId, "pending");
    
        if (appointments.isEmpty()) {
            System.out.println("No pending appointments found.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                String patientId = appointment.getpatientHospitalId();
                System.out.printf("%-5d %-15s %-20s %-20s%n", (i + 1), appointment.getTime(), patientId, appointment.getStatus());
            }
    
            // Prompt doctor to select an appointment to confirm or cancel
            System.out.println("----------------------------------------");
            System.out.print("Enter the number of the appointment you want to confirm/cancel (or 0 to go back): ");
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice >= 0 && choice <= appointments.size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + appointments.size());
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
    
            Appointment selectedAppointment = appointments.get(choice - 1);
            System.out.println("You selected the appointment with Patient ID: " + selectedAppointment.getpatientHospitalId() + "name" + patientContainer.getUserByHospitalId(selectedAppointment.getpatientHospitalId()).getName() + " on " + selectedAppointment.getTime());
            System.out.println("----------------------------------------");
            System.out.println("0. Reject the appointment");
            System.out.println("1. Confirm the appointment");  
            System.out.print("Enter your choice: ");          
            String action = sc.nextLine().trim().toUpperCase();
    
            if (action.equals("1")) {
                selectedAppointment.setStatus("confirmed");
                System.out.println("Appointment confirmed.");
            } else if (action.equals("0")) {
                // Cancel the appointment: remove from container and add time slot back
                appointmentContainer.removeAppointment(selectedAppointment.getRecordId());
                doctor.addAvailableSlot(selectedAppointment.getTime());
                System.out.println("Appointment canceled, and the time slot is now available.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    

    private void confirmedAppointments() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Confirmed Appointments:");
        System.out.printf("%-5s %-15s %-20s %-20s%n", "No.", "Date & Time", "Patient ID", "Status");
        System.out.println("------------------------------------------------------------");
    
        List<Appointment> appointments = appointmentContainer.getAllAppointmentsByDoctorIdAndStatus(doctorHospitalId, "confirmed");
    
        if (appointments.isEmpty()) {
            System.out.println("No confirmed appointments found.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                String patientId = appointment.getpatientHospitalId();
                String patientName = patientContainer.getUserByHospitalId(patientId).getName();
                System.out.printf("%-5d %-15s %-20s %-20s%n", (i + 1), appointment.getTime(), patientId + " (" + patientName + ")", appointment.getStatus());
            }
    
            // Prompt doctor to select an appointment to mark as completed
            System.out.println("----------------------------------------");
            System.out.print("Enter the number of the appointment you complete (or 0 to go back): ");
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice >= 0 && choice <= appointments.size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + appointments.size());
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
    
            // Mark the selected appointment as completed
            Appointment selectedAppointment = appointments.get(choice - 1);
            System.out.println("You selected the appointment with Patient ID: " + selectedAppointment.getpatientHospitalId() + " Name: " + patientContainer.getUserByHospitalId(selectedAppointment.getpatientHospitalId()).getName() + " on " + selectedAppointment.getTime());
            System.out.println("----------------------------------------");
            System.out.println("0. Complete the appointment and fill in Appointment Outcome Record");  
            System.out.print("Enter your choice: ");          
            String action = sc.nextLine().trim().toUpperCase();
    
            if (action.equals("0")) {
                selectedAppointment.setStatus("completed");
                System.out.println("Appointment completed.");
                System.out.println("----------------------------------------");

                // Prompt to fill in AppointmentOutcomeRecord
                System.out.println("Fill in the Appointment Outcome Record:");
                System.out.print("Enter service type: ");
                String serviceType = sc.nextLine();
                System.out.print("Enter consultation notes: ");
                String consultationNotes = sc.nextLine();
            
                // Create and add AppointmentOutcomeRecord
                appointmentOutcomeRecordContainer.addAppointmentOutcomeRecord(selectedAppointment, serviceType, consultationNotes);
                
                System.out.println("----------------------------------------");

                // Ask if the doctor wants to add any prescriptions
                System.out.println("Would you like to add any prescriptions for this appointment?");
                System.out.println("0. No");
                System.out.println("1. Yes");  
                System.out.print("Enter your choice: ");   
                String addPrescriptions = sc.nextLine().trim().toLowerCase();
            
                if (addPrescriptions.equals("1")) {
                    System.out.println("Add prescriptions for the appointment:");
            
                    String addMore;
                    do {
                        // Display all available medicines with numbers for selection
                        List<Medicine> medicines = medicineContainer.getAllMedicines();
                        System.out.println("Available Medicines:");
                        System.out.printf("%-5s %-20s %-10s %-10s%n", "No.", "Medicine Name", "Stock", "Alert Level");
                        System.out.println("------------------------------------------------------------");

                        for (int i = 0; i < medicines.size(); i++) {
                            Medicine medicine = medicines.get(i);
                            System.out.printf("%-5d %-20s %-10d %-10d%n", (i + 1), medicine.getMedicineName(), medicine.getCurrentStock(), medicine.getAlertThreshold());
                        }

                        System.out.println("----------------------------------------");
                        System.out.print("Enter the number of the medicine you want to prescribe (or 0 to go back): ");
                        
                        int medicineChoice;
                        while (true) {
                            if (sc.hasNextInt()) {
                                medicineChoice = sc.nextInt();
                                sc.nextLine(); // Clear buffer
                                if (medicineChoice == 0) {
                                    System.out.println("Returning to previous menu...");
                                    return; // Exit the prescription loop
                                } else if (medicineChoice >= 1 && medicineChoice <= medicines.size()) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please enter a number between 0 and " + medicines.size());
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a number.");
                                sc.next(); // Clear invalid input
                            }
                        }

                        // Get the selected medicine based on the number
                        Medicine selectedMedicine = medicines.get(medicineChoice - 1);

                        // Prompt for amount
                        System.out.print("Enter amount: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Please enter a valid integer amount.");
                            sc.next(); // Clear the invalid input
                        }
                        int amount = sc.nextInt();
                        sc.nextLine(); // Clear the buffer

                        // Create and add Prescription to the outcome record
                        appointmentOutcomeRecordContainer.getAppointmentOutcomeRecordById(selectedAppointment.getRecordId()).addPrescription(selectedMedicine.getMedicineName(), amount);
                        System.out.println("Prescription added.");
                        System.out.println("----------------------------------------");

                        // Prompt to add another prescription
                        System.out.println("Add another prescription?");
                        System.out.println("0. No");
                        System.out.println("1. Yes");
                        System.out.print("Enter your choice: ");
                        addMore = sc.nextLine().trim();

                    } while (addMore.equals("1"));

                }
                System.out.println("You selected the appointment with Patient ID: " + selectedAppointment.getpatientHospitalId() + " Name: " + patientContainer.getUserByHospitalId(selectedAppointment.getpatientHospitalId()).getName() + " on " + selectedAppointment.getTime());

                System.out.println("Appointment Outcome Record completed for the appointment with Patient ID: " + selectedAppointment.getpatientHospitalId() + " Name: " + patientContainer.getUserByHospitalId(selectedAppointment.getpatientHospitalId()).getName() + " on " + selectedAppointment.getTime());
            } else {
                System.out.println("Invalid choice.");
            }
            
        }
    }
    

    private void completedAppointments() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Completed Appointments:");
        System.out.printf("%-5s %-15s %-20s %-20s%n", "No.", "Date & Time", "Patient ID", "Status");
        System.out.println("------------------------------------------------------------");

        List<Appointment> appointments = appointmentContainer.getAllAppointmentsByDoctorIdAndStatus(doctorHospitalId, "completed");

        if (appointments.isEmpty()) {
            System.out.println("No completed appointments found.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                String patientId = appointment.getpatientHospitalId();
                System.out.printf("%-5d %-15s %-20s %-20s%n", (i + 1), appointment.getTime(), patientId, appointment.getStatus());
            }

            // Prompt doctor to select a completed appointment to view its outcome record
            System.out.println("----------------------------------------");
            System.out.print("Enter the number of the completed appointment to view its outcome record (or 0 to go back): ");
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice == 0) {
                        System.out.println("Returning to previous menu...");
                        return; // Exit if the user wants to go back
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
                System.out.println("\nAppointment Outcome Record completed for the appointment with Patient ID: " + selectedAppointment.getpatientHospitalId() + " Name: " + patientContainer.getUserByHospitalId(selectedAppointment.getpatientHospitalId()).getName() + " on " + selectedAppointment.getTime());
                System.out.println("Service Type: " + outcomeRecord.getServiceType());
                System.out.println("Consultation Notes: " + outcomeRecord.getConsultationNotes());
                System.out.println("\nPrescriptions:");
                if (outcomeRecord.getPrescriptions().isEmpty()) {
                    System.out.println("No prescriptions recorded.");
                } else {
                    for (int i = 0; i < outcomeRecord.getPrescriptions().size(); i++) {
                        Prescription prescription = outcomeRecord.getPrescriptions().get(i);
                        System.out.printf("%d. Medication: %s, Amount: %d%n", (i + 1), prescription.getMedicine(), prescription.getAmount());
                    }
                }
                System.out.println("----------------------------------------");
            } else {
                System.out.println("No outcome record found for this appointment.");
            }
        }
    }


    private void setAvailability() {
        Scanner sc = new Scanner(System.in);
        int setAvailabilityChoice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Set Availability Menu");
            System.out.println("0. Log out");
            System.out.println("1. Add Available Slot");
            System.out.println("2. View Available Slots");
    
            System.out.print("Enter your choice: ");
            
            // Validate that the input is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); // Clear the invalid input
            }
            setAvailabilityChoice = sc.nextInt();
            sc.nextLine(); // Clear buffer after reading integer choice
    
            switch (setAvailabilityChoice) {
                case 0:
                    System.out.println("Logging out...");
                    break;
                case 1:
                    System.out.print("Enter available slot (YYYY-MM-DD HH:MM): ");
                    String slot = sc.nextLine(); // Use nextLine to capture the full slot input
                    doctor.addAvailableSlot(slot);
                    System.out.println("Slot added successfully.");
                    break;
                case 2:
                    System.out.println("Available Slots:");
                    doctor.getAvailableSlots().forEach((availableSlot) -> {
                        System.out.println("- " + availableSlot);
                    });
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (setAvailabilityChoice != 0);
    }
    

    private void MedicalRecord() {
        Scanner sc = new Scanner(System.in);

        int medicalRecordChoice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Medical Record Menu");
            System.out.println("0. Log out");
            System.out.println("1. View Medical Record");
            System.out.println("2. Edit Medical Record");

            System.out.print("Enter your choice: ");
            medicalRecordChoice=sc.nextInt();
            switch (medicalRecordChoice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    viewMedicalRecord(); break;
                case 2:
                    editMedicalRecord(); break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (medicalRecordChoice!=0);
    }

    private void viewMedicalRecord() {
        System.out.println("Patients Under Your Care:");
        System.out.printf("%-15s %-20s%n", "Hospital ID", "Patient Name");
        System.out.println("----------------------------------------");
    
        // Print each patient's hospital ID and name
        doctor.getAllPatientsUnderCare().forEach((patientHospitalId) -> {
            String patientName = patientContainer.getUserByHospitalId(patientHospitalId).getName();
            System.out.printf("%-15s %-20s%n", patientHospitalId, patientName);
            //also print the diagnosis and treatment
            System.out.println("Diagnosis:");
            patientContainer.getPatientByHospitalId(patientHospitalId).getMedicalRecord().getDiagnoses().forEach((diagnosis) -> {
                System.out.println("- " + diagnosis);
            });
            System.out.println("Treatment:");
            patientContainer.getPatientByHospitalId(patientHospitalId).getMedicalRecord().getTreatments().forEach((treatment) -> {
                System.out.println("- " + treatment);
            });
        });
    }

    private void editMedicalRecord() {
        Scanner scMedical = new Scanner(System.in);
        System.out.print("Enter patient hospital ID that you want to edit: ");
        String patientHospitalId = scMedical.next();
        scMedical.nextLine(); // Clear buffer after reading hospital ID
    
        if (doctor.getAllPatientsUnderCare().contains(patientHospitalId)) {
            int editMedicalRecordChoice;
            do {
                System.out.println("----------------------------------------");
                System.out.println("Edit Medical Record of " + patientContainer.getUserByHospitalId(patientHospitalId).getName() + " (" + patientHospitalId + ")");
                System.out.println("0. Log out");
                System.out.println("1. Add Diagnosis");
                System.out.println("2. Add Treatment");
    
                System.out.print("Enter your choice: ");
                while (!scMedical.hasNextInt()) {  // Check for integer input
                    System.out.println("Invalid input! Please enter a number.");
                    scMedical.next(); // Clear the invalid input
                }
                editMedicalRecordChoice = scMedical.nextInt();
                scMedical.nextLine(); // Clear buffer after reading integer choice
    
                switch (editMedicalRecordChoice) {
                    case 0:
                        System.out.println("Logging out...");
                        break;
                    case 1:
                        System.out.print("Enter diagnosis: ");
                        String diagnosis = scMedical.nextLine(); // Use nextLine to capture full diagnosis input
                        patientContainer.getPatientByHospitalId(patientHospitalId).getMedicalRecord().addDiagnose(diagnosis);
                        System.out.println("Diagnosis added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter treatment: ");
                        String treatment = scMedical.nextLine(); // Use nextLine to capture full treatment input
                        patientContainer.getPatientByHospitalId(patientHospitalId).getMedicalRecord().addTreatment(treatment);
                        System.out.println("Treatment added successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (editMedicalRecordChoice != 0);
        } else {
            System.out.println("Patient not found");
        }
    }

    private void addPatientUnderCare() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter patient hospital ID that wanna put under care: ");
        String patientHospitalId = sc.next();
        if (patientContainer.getUserByHospitalId(patientHospitalId) != null) {
            doctor.addPatientUnderCare(patientHospitalId);
            String patientName = patientContainer.getUserByHospitalId(patientHospitalId).getName(); 
            System.out.println("Patient " + patientName + " added to your care");   
        } else {
            System.out.println("Patient not found");
        }

    }// adding to this doctor here will also update the doctor in the doctor container
}
