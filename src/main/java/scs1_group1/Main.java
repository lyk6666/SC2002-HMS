package scs1_group1;
import java.util.HashMap;

import scs1_group1.container.Container;
import scs1_group1.container.data.AppointmentContainer;
import scs1_group1.container.data.AppointmentOutcomeRecordContainer;
import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.data.ReplenishmentRequestContainer;
import scs1_group1.container.user.AdministratorContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.container.user.PharmacistContainer;
import scs1_group1.menu.StartMenu;

public class Main {
    public static void main(String[] args) {
        String patientPath="data/Patient_List.csv";
        String staffPath="data/Staff_List.csv";
        String MedicinePath="data/Medicine_List.csv";
        
        PatientContainer patientContainer=new PatientContainer(patientPath);
        // Initialize separate containers for each staff type
        DoctorContainer doctorContainer = new DoctorContainer(staffPath);
        PharmacistContainer pharmacistContainer = new PharmacistContainer(staffPath);
        AdministratorContainer administratorContainer = new AdministratorContainer(staffPath);
        
        MedicineContainer medicineContainer=new MedicineContainer(MedicinePath);
        AppointmentContainer appointmentContainer=new AppointmentContainer();
        AppointmentOutcomeRecordContainer appointmentOutcomeContainer=new AppointmentOutcomeRecordContainer();
        ReplenishmentRequestContainer replenishmentRequestContainer = new ReplenishmentRequestContainer();

        HashMap<String,Container> containers=new HashMap<>();
        containers.put("Patient",patientContainer);
        containers.put("Doctor",doctorContainer);
        containers.put("Pharmacist",pharmacistContainer);
        containers.put("Administrator",administratorContainer);
        containers.put("Medicine",medicineContainer);
        containers.put("Appointment",appointmentContainer);
        containers.put("AppointmentOutcomeRecord",appointmentOutcomeContainer);
        containers.put("ReplenishmentRequest",replenishmentRequestContainer);

        StartMenu startMenu=new StartMenu(containers);
        startMenu.run();

    }
}