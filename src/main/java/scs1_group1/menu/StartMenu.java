package scs1_group1.menu;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import scs1_group1.container.Container;
import scs1_group1.container.data.AppointmentContainer;
import scs1_group1.container.data.AppointmentOutcomeRecordContainer;
import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.user.AdministratorContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.container.user.PharmacistContainer;

public class StartMenu extends Menu {
    HashMap<String,Container> containers;
    public StartMenu(HashMap<String,Container> containers) {
        this.containers=containers;
    }
    @Override
public void run() {
    Scanner sc = new Scanner(System.in);
    int choice;
    do {
        System.out.println("----------------------------------------");
        System.out.println("Hospital Start Menu");
        System.out.println("0. Quit");
        System.out.println("1. Log in");
        System.out.print("Enter your choice: ");
        
        // Use try-catch to handle invalid input
        try {
            choice = sc.nextInt();
            
            switch (choice) {
                case 0:
                    System.out.println("Quitting...");
                    break;
                case 1:
                    System.out.print("Enter hospital ID: ");
                    String hospitalId = sc.next();
                    System.out.print("Enter password: ");
                    String password = sc.next();
                    Menu userMenu = createUserMenu(hospitalId, password);
                    if (userMenu != null) {
                        userMenu.run();
                    } else {
                        System.out.println("Wrong hospital ID or wrong password");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid answer! Please enter a number.");
            sc.nextLine(); // Clear the invalid input from the scanner buffer
            choice = -1; // Set choice to a non-exiting value to continue the loop
        }
    } while (choice != 0);
}


    public Menu createUserMenu(String hospitalId,String password) {
        PatientContainer patientContainer = (PatientContainer)(containers.get("Patient"));
        DoctorContainer doctorContainer = (DoctorContainer)(containers.get("Doctor"));
        PharmacistContainer pharmacistContainer = (PharmacistContainer)(containers.get("Pharmacist"));
        AdministratorContainer administratorContainer = (AdministratorContainer)(containers.get("Administrator"));
        AppointmentContainer appointmentContainer = (AppointmentContainer)(containers.get("Appointment"));
        AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer = (AppointmentOutcomeRecordContainer)(containers.get("AppointmentOutcomeRecord"));
        MedicineContainer medicineContainer = (MedicineContainer)(containers.get("Medicine"));

        if (patientContainer.containsUser(hospitalId)&&patientContainer.getUserTypeByHospitalId(hospitalId).equals("Patient")){ 
            String correctPassword=patientContainer.getUserByHospitalId(hospitalId).getPassword();
            if (password.equals(correctPassword)) {
                return new PatientMenu(
                    hospitalId,
                    patientContainer,
                    doctorContainer,
                    appointmentContainer,
                    appointmentOutcomeRecordContainer
                );
            } else return null;
        } else if (doctorContainer.containsUser(hospitalId)&&doctorContainer.getUserTypeByHospitalId(hospitalId).equals("Doctor")){
            String correctPassword=doctorContainer.getUserByHospitalId(hospitalId).getPassword();
            if (password.equals(correctPassword)) {
                return new DoctorMenu(
                    hospitalId,
                    doctorContainer,
                    patientContainer,
                    appointmentContainer,
                    appointmentOutcomeRecordContainer,
                    medicineContainer
                );
            } else return null;
        } else if (pharmacistContainer.containsUser(hospitalId)&&pharmacistContainer.getUserTypeByHospitalId(hospitalId).equals("Pharmacist")){
            String correctPassword=pharmacistContainer.getUserByHospitalId(hospitalId).getPassword();
            if (password.equals(correctPassword)) {
                return new PharmacistMenu();
            } else return null;
        } else if (administratorContainer.containsUser(hospitalId)&&administratorContainer.getUserTypeByHospitalId(hospitalId).equals("Administrator")){
            String correctPassword=administratorContainer.getUserByHospitalId(hospitalId).getPassword();
            if (password.equals(correctPassword)) {
                return new AdministratorMenu(
                    hospitalId,
                    administratorContainer,
                    patientContainer,
                    doctorContainer,
                    pharmacistContainer,
                    medicineContainer
                );
            } else return null;
        }
        return null;
    }

    
}
