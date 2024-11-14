package scs1_group1.menu;

import java.util.List;
import java.util.Scanner;

import scs1_group1.container.data.AppointmentOutcomeRecordContainer;
import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.data.ReplenishmentRequestContainer;
import scs1_group1.record.AppointmentOutcomeRecord;
import scs1_group1.record.Medicine;
import scs1_group1.record.Prescription;


public class PharmacistMenu extends Menu {
    private String pharmacistHospitalId;
    private AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer;
    private MedicineContainer medicineContainer;
    private ReplenishmentRequestContainer ReplenishmentRequestContainer;

    public PharmacistMenu(String pharmacistHospitalId , AppointmentOutcomeRecordContainer appointmentOutcomeRecordContainer, MedicineContainer medicineContainer, ReplenishmentRequestContainer ReplenishmentRequestContainer) {
        super();
        this.pharmacistHospitalId=pharmacistHospitalId;
        this.appointmentOutcomeRecordContainer=appointmentOutcomeRecordContainer;
        this.medicineContainer=medicineContainer;
        this.ReplenishmentRequestContainer=ReplenishmentRequestContainer;
    }
    
    @Override
    public void run() {

        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Pharmacist Menu");
            System.out.println("0. Log out");
            System.out.println("1. Appointment Outcome Records");
            System.out.println("2. Medicine Inventory");
            
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    appointmentOutcomeRecord(); break;
                case 2:
                    medicineInventory(); break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=0);
    }

    private void appointmentOutcomeRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Appointment Outcome Records:");
        System.out.printf("%-5s %-30s %-20s %-20s %-20s%n", "No.", "Appointment ID", "Service Type", "Patient ID", "Doctor ID");
        System.out.println("--------------------------------------------------------------------------------");

        // Retrieve all AppointmentOutcomeRecords
        List<AppointmentOutcomeRecord> records = appointmentOutcomeRecordContainer.getAllAppointmentOutcomeRecords();

        if (records.isEmpty()) {
            System.out.println("No appointment outcome records found.");
        } else {
            for (int i = 0; i < records.size(); i++) {
                AppointmentOutcomeRecord record = records.get(i);
                System.out.printf("%-5d %-30s %-20s %-20s %-20s%n", 
                    (i + 1), 
                    record.getAppointmentRecordId(), 
                    record.getServiceType(), 
                    record.getPatientHospitalId(), 
                    record.getDoctorHospitalId()
                );
            }

            // Prompt to select a record by number
            System.out.println("----------------------------------------");
            System.out.print("Enter the number of the Appointment Outcome Record to view prescriptions (or 0 to go back): ");
            
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice >= 0 && choice <= records.size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + records.size());
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }

            // Go back if the user selects 0
            if (choice == 0) {
                System.out.println("Returning to previous menu...");
                return;
            }

            // Display prescriptions for the selected AppointmentOutcomeRecord
            AppointmentOutcomeRecord selectedRecord = records.get(choice - 1);
            System.out.println("Prescriptions for Appointment ID: " + selectedRecord.getAppointmentRecordId());
            System.out.printf("%-5s %-20s %-10s %-10s%n", "No.", "Medicine Name", "Amount", "Status");
            System.out.println("----------------------------------------------------");

            List<Prescription> prescriptions = selectedRecord.getPrescriptions();

            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found for this appointment.");
            } else {
                for (int i = 0; i < prescriptions.size(); i++) {
                    Prescription prescription = prescriptions.get(i);
                    System.out.printf("%-5d %-20s %-10d %-10s%n", 
                        (i + 1), 
                        prescription.getMedicine(), 
                        prescription.getAmount(), 
                        prescription.getStatus()
                    );
                }
                 // Prompt pharmacist to select a prescription to dispense
                System.out.println("----------------------------------------");
                System.out.print("Enter the number of the Prescription to dispense (or 0 to go back): ");
                
                int prescriptionChoice;
                while (true) {
                    if (sc.hasNextInt()) {
                        prescriptionChoice = sc.nextInt();
                        sc.nextLine(); // Clear buffer
                        if (prescriptionChoice >= 0 && prescriptionChoice <= prescriptions.size()) {
                            break;
                        } else {
                            System.out.println("Invalid choice. Please enter a number between 0 and " + prescriptions.size());
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.next(); // Clear invalid input
                    }
                }

                // Go back if the user selects 0
                if (prescriptionChoice == 0) {
                    System.out.println("Returning to previous menu...");
                    return;
                }

                // Check and update the status of the selected prescription
                Prescription selectedPrescription = prescriptions.get(prescriptionChoice - 1);
                if (selectedPrescription.getStatus().equalsIgnoreCase("pending")) {
                    selectedPrescription.setStatus("dispensed");
                    System.out.println("Prescription for " + selectedPrescription.getMedicine() + " has been dispensed "+ selectedPrescription.getAmount() +" amount.");

                    // Update the stock for the dispensed medicine
                    Medicine dispensedMedicine = medicineContainer.getMedicineByName(selectedPrescription.getMedicine());
                    if (dispensedMedicine != null) {
                        int newStock = dispensedMedicine.getCurrentStock() - selectedPrescription.getAmount();
                        dispensedMedicine.setCurrentStock(newStock);
                        System.out.println("Updated stock for " + dispensedMedicine.getMedicineName() + ": " + newStock + " remaining.");
                    } else {
                        System.out.println("Error: Medicine not found in inventory.");
                    }
                } else {
                    System.out.println("This prescription has already been dispensed.");
                }
            }
        }
    }



    private void medicineInventory() {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Medicine Inventory:");
        System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", "No.", "Medicine Name", "Current Stock", "Alert Threshold", "Stock Level");
        System.out.println("--------------------------------------------------------------------------");
    
        List<Medicine> medicines = medicineContainer.getAllMedicines();
    
        if (medicines.isEmpty()) {
            System.out.println("No medicines in the inventory.");
        } else {
            for (int i = 0; i < medicines.size(); i++) {
                Medicine medicine = medicines.get(i);
                String stockLevel = (medicine.getCurrentStock() <= medicine.getAlertThreshold()) ? "low stock" : "normal";
                System.out.printf("%-5d %-20s %-15d %-15d %-15s%n",
                    (i + 1),
                    medicine.getMedicineName(),
                    medicine.getCurrentStock(),
                    medicine.getAlertThreshold(),
                    stockLevel
                );
            }
    
            // Prompt to select a medicine for replenishment
            System.out.println("--------------------------------------------------------------------------");
            System.out.print("Enter the number of the medicine to request replenishment (or 0 to go back): ");
            
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice >= 0 && choice <= medicines.size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + medicines.size());
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // If user wants to go back
            if (choice == 0) {
                System.out.println("Returning to previous menu...");
                return;
            }
    
            // Selected medicine for replenishment
            Medicine selectedMedicine = medicines.get(choice - 1);
    
            // Prompt for replenishment quantity
            System.out.print("Enter the quantity to replenish for " + selectedMedicine.getMedicineName() + ": ");
            int quantity;
            while (true) {
                if (sc.hasNextInt()) {
                    quantity = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println("Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Add replenishment request
            ReplenishmentRequestContainer.addReplenishmentRequest(selectedMedicine.getMedicineName(), quantity);
            System.out.println("Replenishment request for " + quantity + " units of " + selectedMedicine.getMedicineName() + " has been added and is pending for approval from admin.");
        }
    }
    

}
