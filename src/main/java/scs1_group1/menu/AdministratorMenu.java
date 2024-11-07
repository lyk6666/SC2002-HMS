package scs1_group1.menu;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.user.AdministratorContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.container.user.PharmacistContainer;
import scs1_group1.user.Administrator;
import scs1_group1.user.Doctor;
import scs1_group1.user.Patient;
import scs1_group1.user.Pharmacist;

public class AdministratorMenu extends Menu {
    private String adminHospitalId;
    private Administrator administrator;
    private PatientContainer patientContainer;
    private DoctorContainer doctorContainer;
    private PharmacistContainer pharmacistContainer;
    private MedicineContainer medicineContainer;

    public AdministratorMenu(String hospitalId, AdministratorContainer administratorContainer, PatientContainer patientContainer, DoctorContainer doctorContainer, PharmacistContainer pharmacistContainer, MedicineContainer medicineContainer) {
        this.adminHospitalId = hospitalId;
        administrator = (Administrator) administratorContainer.getUserByHospitalId(adminHospitalId);
        this.patientContainer = patientContainer;
        this.doctorContainer = doctorContainer;
        this.pharmacistContainer = pharmacistContainer;
        this.medicineContainer = medicineContainer;
    }
    
    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Administrator Menu");
            System.out.println("0. Log out");
            System.out.println("1. Manage Patient");
            System.out.println("2. Manage Doctor");
            System.out.println("3. Manage Pharmacist");
            System.out.println("4. Manage Inventory");
            
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    managePatient(); break;
                case 2:
                    manageDoctor(); break;
                case 3:
                    managePharmacist(); break;
                case 4:
                    manageInventory(); break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=0);
    }

    public void managePatient() {
        // Filter and collect all patients from the values of the map
        Collection<Patient> patients = patientContainer.getAllPatients().values().stream()
            .filter(Patient.class::isInstance)  // Ensure the object is an instance of Patient
            .map(Patient.class::cast)           // Cast each item to Patient
            .collect(Collectors.toList());

        // Print a header
        System.out.printf("%-15s %-20s%n", "Hospital ID", "Patient Name");
        System.out.println("----------------------------------------");

        // Print each patient's hospitalId and name
        for (Patient patient : patients) {
            System.out.printf("%-15s %-20s%n", patient.getHospitalId(), patient.getName());
        }
    }
    

    private void manageDoctor() {
        // Filter and collect all doctors from the values of the map
        Collection<Doctor> doctors = doctorContainer.getAllDoctors().values().stream()
            .filter(Doctor.class::isInstance)  // Ensure the object is an instance of Doctor
            .map(Doctor.class::cast)           // Cast each item to Doctor
            .collect(Collectors.toList());
    
        // Print a header
        System.out.printf("%-15s %-20s%n", "Hospital ID", "Doctor Name");
        System.out.println("----------------------------------------");
    
        // Print each doctor's hospitalId and name
        for (Doctor doctor : doctors) {
            System.out.printf("%-15s %-20s%n", doctor.getHospitalId(), doctor.getName());
        }
    }

    private void managePharmacist() {
        // Filter and collect all pharmacists from the values of the map
        Collection<Pharmacist> pharmacists = pharmacistContainer.getAllPharmacists().values().stream()
            .filter(Pharmacist.class::isInstance)  // Ensure the object is an instance of Pharmacist
            .map(Pharmacist.class::cast)           // Cast each item to Pharmacist
            .collect(Collectors.toList());

        // Print a header
        System.out.printf("%-15s %-20s%n", "Hospital ID", "Pharmacist Name");
        System.out.println("----------------------------------------");

        // Print each pharmacist's hospitalId and name
        for (Pharmacist pharmacist : pharmacists) {
            System.out.printf("%-15s %-20s%n", pharmacist.getHospitalId(), pharmacist.getName());
        }
    }


    private void manageInventory() {
        System.out.println("Manage Inventory");
    }
}
