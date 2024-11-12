package scs1_group1.menu;

import java.util.List;
import java.util.Scanner;

import scs1_group1.container.data.AppointmentContainer;
import scs1_group1.container.data.MedicineContainer;
import scs1_group1.container.data.ReplenishmentRequestContainer;
import scs1_group1.container.user.AdministratorContainer;
import scs1_group1.container.user.DoctorContainer;
import scs1_group1.container.user.PatientContainer;
import scs1_group1.container.user.PharmacistContainer;
import scs1_group1.container.user.StaffContainer;
import scs1_group1.record.Appointment;
import scs1_group1.record.Medicine;
import scs1_group1.record.ReplenishmentRequest;
import scs1_group1.user.Administrator;
import scs1_group1.user.Doctor;
import scs1_group1.user.Pharmacist;
import scs1_group1.user.Staff;
import scs1_group1.user.User;

public class AdministratorMenu extends Menu {
    private String adminHospitalId;
    private Administrator administrator;
    private PatientContainer patientContainer;
    private DoctorContainer doctorContainer;
    private PharmacistContainer pharmacistContainer;
    private MedicineContainer medicineContainer;
    private ReplenishmentRequestContainer replenishmentRequestContainer;
    private AppointmentContainer appointmentContainer;
    private AdministratorContainer administratorContainer;

    public AdministratorMenu(String hospitalId, AdministratorContainer administratorContainer, PatientContainer patientContainer, DoctorContainer doctorContainer, PharmacistContainer pharmacistContainer, MedicineContainer medicineContainer, ReplenishmentRequestContainer replenishmentRequestContainer, AppointmentContainer appointmentContainer) {
        this.adminHospitalId = hospitalId;
        administrator = (Administrator) administratorContainer.getUserByHospitalId(adminHospitalId);
        this.patientContainer = patientContainer;
        this.doctorContainer = doctorContainer;
        this.pharmacistContainer = pharmacistContainer;
        this.medicineContainer = medicineContainer;
        this.replenishmentRequestContainer = replenishmentRequestContainer;
        this.appointmentContainer = appointmentContainer;
        this.administratorContainer = administratorContainer;
    }
    
    @Override
    public void run() {
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Administrator Menu");
            System.out.println("0. Log out");
            System.out.println("1. View Appointment");
            System.out.println("2. Manage Staff");
            System.out.println("3. Manage Medicine");
            System.out.println("4. Manage Replenishment Request");
            System.out.println("5. Change Medicine Alert Level");
            
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Logging out..."); break;
                case 1:
                    viewAppointment(); break;
                case 2:
                    manageStaff(); break;
                case 3:
                    manageMedicine(); break;
                case 4:
                    manageReplenishmentRequest(); break;
                case 5:
                    changeAlertLevel(); break; // missed function
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice!=0);
    }

    private void changeAlertLevel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("All Medicines:");
        System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", "No.", "Medicine Name", "Current Stock", "Alert Threshold", "Stock Level");
        System.out.println("------------------------------------------------------------");
    
        List<Medicine> medicines = medicineContainer.getAllMedicines();
    
        if (medicines.isEmpty()) {
            System.out.println("No medicines in the inventory.");
        } else {
            for (int i = 0; i < medicines.size(); i++) {
                Medicine medicine = medicines.get(i);
                String stockLevel = (medicine.getCurrentStock() <= medicine.getAlertThreshold()) ? "Low Stock" : "Normal";
                System.out.printf("%-5d %-20s %-15d %-15d %-15s%n",
                    (i + 1),
                    medicine.getMedicineName(),
                    medicine.getCurrentStock(),
                    medicine.getAlertThreshold(),
                    stockLevel
                );
            }
    
            // Prompt the user to select a medicine to update
            System.out.println("------------------------------------------------------------");
            System.out.print("Enter the number of the medicine you want to update stock alert level (or 0 to go back): ");
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
    
            // Go back if user chooses 0
            if (choice == 0) {
                System.out.println("Returning to previous menu...");
                return;
            }
    
            // Get the selected medicine
            Medicine selectedMedicine = medicines.get(choice - 1);
            System.out.println("You selected: " + selectedMedicine.getMedicineName());
            
            // Prompt for the new stock level
            System.out.print("Enter the new stock alert level: ");
            int newAlertLevel;
            while (true) {
                if (sc.hasNextInt()) {
                    newAlertLevel = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (newAlertLevel >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid positive integer for alert stock level.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Update the stock level
            selectedMedicine.setAlertThreshold(newAlertLevel);
            System.out.println("Alert stock level for " + selectedMedicine.getMedicineName() + " updated to " + newAlertLevel + ".");
        }
    }

    private void manageMedicine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("All Medicines:");
        System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", "No.", "Medicine Name", "Current Stock", "Alert Threshold", "Stock Level");
        System.out.println("------------------------------------------------------------");
    
        List<Medicine> medicines = medicineContainer.getAllMedicines();
    
        if (medicines.isEmpty()) {
            System.out.println("No medicines in the inventory.");
        } else {
            for (int i = 0; i < medicines.size(); i++) {
                Medicine medicine = medicines.get(i);
                String stockLevel = (medicine.getCurrentStock() <= medicine.getAlertThreshold()) ? "Low Stock" : "Normal";
                System.out.printf("%-5d %-20s %-15d %-15d %-15s%n",
                    (i + 1),
                    medicine.getMedicineName(),
                    medicine.getCurrentStock(),
                    medicine.getAlertThreshold(),
                    stockLevel
                );
            }
    
            // Prompt the user to select a medicine to update
            System.out.println("------------------------------------------------------------");
            System.out.print("Enter the number of the medicine you want to update (or 0 to go back): ");
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
    
            // Go back if user chooses 0
            if (choice == 0) {
                System.out.println("Returning to previous menu...");
                return;
            }
    
            // Get the selected medicine
            Medicine selectedMedicine = medicines.get(choice - 1);
            System.out.println("You selected: " + selectedMedicine.getMedicineName());
            
            // Prompt for the new stock level
            System.out.print("Enter the new stock level: ");
            int newStock;
            while (true) {
                if (sc.hasNextInt()) {
                    newStock = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (newStock >= 0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid positive integer for the stock level.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Update the stock level
            selectedMedicine.setCurrentStock(newStock);
            System.out.println("Stock level for " + selectedMedicine.getMedicineName() + " updated to " + newStock + ".");
        }
    }
    
    private void manageReplenishmentRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("All Replenishment Requests:");
        System.out.printf("%-5s %-20s %-15s %-15s%n", "No.", "Medicine Name", "Quantity", "Status");
        System.out.println("------------------------------------------------------------");
    
        List<ReplenishmentRequest> requests = replenishmentRequestContainer.getAllReplenishmentRequests();
    
        if (requests.isEmpty()) {
            System.out.println("No replenishment requests found.");
        } else {
            for (int i = 0; i < requests.size(); i++) {
                ReplenishmentRequest request = requests.get(i);
                System.out.printf("%-5d %-20s %-15d %-15s%n",
                    (i + 1),
                    request.getMedicineName(),
                    request.getQuantity(),
                    request.getStatus()
                );
            }
    
            // Prompt the user to select a request
            System.out.println("------------------------------------------------------------");
            System.out.print("Enter the number of the request you want to approve/reject (or 0 to go back): ");
            int choice;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (choice >= 0 && choice <= requests.size()) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 0 and " + requests.size());
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }
    
            // Go back if user chooses 0
            if (choice == 0) {
                System.out.println("Returning to previous menu...");
                return;
            }
    
            // Get the selected request
            ReplenishmentRequest selectedRequest = requests.get(choice - 1);
            System.out.println("You selected the request for: " + selectedRequest.getMedicineName());
    
            // Prompt to approve or reject the request
            System.out.println("----------------------------------------");
            System.out.println("Select an action:");
            System.out.println("1. Approve and add medicine");
            System.out.println("2. Reject and delete request");
            System.out.print("Enter your choice: ");
            
            int action;
            while (true) {
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                    if (action == 1 || action == 2) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 to approve or 2 to reject.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear invalid input
                }
            }
    
            if (action == 1) {
                // Approve: update status and inventory
                selectedRequest.setStatus("approved");
                Medicine medicine = medicineContainer.getMedicineByName(selectedRequest.getMedicineName());
                if (medicine != null) {
                    int newStock = medicine.getCurrentStock() + selectedRequest.getQuantity();
                    medicine.setCurrentStock(newStock);
                    System.out.println("Request approved. Inventory updated for " + selectedRequest.getMedicineName() + ".");
                } else {
                    System.out.println("Error: Medicine not found in inventory.");
                }
            } else if (action == 2) {
                // Reject: remove the request
                replenishmentRequestContainer.removeReplenishmentRequest(selectedRequest.getRecordId());
                System.out.println("Request rejected and removed.");
            }
        }
    }
    

    public void viewAppointment() {
        System.out.println("----------------------------------------");
        System.out.println("All Appointments:");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s%n", "No.", "Date & Time", "Patient ID", "Doctor ID", "Status");
        System.out.println("------------------------------------------------------------");

        List<Appointment> appointments = appointmentContainer.getAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                System.out.printf("%-5d %-15s %-15s %-15s %-15s%n",
                    (i + 1),
                    appointment.getTime(),
                    appointment.getpatientHospitalId(),
                    appointment.getdoctorHospitalId(),
                    appointment.getStatus()
                );
            }
        }
    }

    private void manageStaff() {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("----------------------------------------");
            System.out.println("Manage Staff");
            System.out.println("0. Back");
            System.out.println("1. View All Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Edit Staff");
            System.out.println("4. Delete Staff");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 0:
                    System.out.println("Returning to previous menu..."); break;
                case 1:
                    printAllStaff(); break;
                case 2:
                    addStaff(); break;
                case 3:
                    editStaff(); break;
                case 4:
                    deleteStaff(); break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 0);
    }

    private void addStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Staff");
        
        // Collect staff details
        System.out.print("Enter staff type (Doctor/Pharmacist/Administrator): ");
        String userType = sc.nextLine().trim();
        if (!userType.equalsIgnoreCase("Doctor") && !userType.equalsIgnoreCase("Pharmacist") && !userType.equalsIgnoreCase("Administrator")) {
            System.out.println("Invalid staff type entered.");
            return;
        }
        
        System.out.print("Enter Hospital ID: ");
        String hospitalId = sc.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Clear buffer after reading integer input

        // Add staff to the appropriate container based on type
        ((StaffContainer) getContainerByType(userType)).addStaff(hospitalId, password, name, gender, userType, email, age);

        System.out.println("Staff added successfully.");
    }

    // Helper function to get the correct container based on user type
    private StaffContainer getContainerByType(String userType) {
        switch (userType.toLowerCase()) {
            case "doctor":
                return doctorContainer;
            case "pharmacist":
                return pharmacistContainer;
            case "administrator":
                return administratorContainer;
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    private void editStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit Staff");

        // Ask for the staff type
        System.out.print("Enter staff type to edit (Doctor/Pharmacist/Administrator): ");
        String userType = sc.nextLine().trim();
        Staff staff = null;

        if (userType.equalsIgnoreCase("Doctor")) {
            System.out.print("Enter Doctor's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            staff = doctorContainer.getDoctorByHospitalId(hospitalId);

        } else if (userType.equalsIgnoreCase("Pharmacist")) {
            System.out.print("Enter Pharmacist's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            staff = pharmacistContainer.getPharmacistByHospitalId(hospitalId);

        } else if (userType.equalsIgnoreCase("Administrator")) {
            System.out.print("Enter Administrator's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            staff = administratorContainer.getAdministratorByHospitalId(hospitalId);

        } else {
            System.out.println("Invalid staff type entered.");
            return;
        }

        // Check if staff was found
        if (staff == null) {
            System.out.println("No staff found with the provided Hospital ID.");
            return;
        }

        // Display current details and prompt for updates
        System.out.println("Editing details for " + userType + " ID: " + staff.getHospitalId());
        System.out.println("Current Name: " + staff.getName());
        System.out.print("Enter new Name (or press Enter to keep current): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            staff.setName(newName);
        }

        System.out.println("Current Gender: " + staff.getGender());
        System.out.print("Enter new Gender (or press Enter to keep current): ");
        String newGender = sc.nextLine().trim();
        if (!newGender.isEmpty()) {
            staff.setGender(newGender);
        }

        System.out.println("Current Email: " + staff.getEmail());
        System.out.print("Enter new Email (or press Enter to keep current): ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty()) {
            staff.setEmail(newEmail);
        }

        System.out.println("Current Age: " + staff.getAge());
        System.out.print("Enter new Age (or press Enter to keep current): ");
        String ageInput = sc.nextLine().trim();
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                staff.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age entered. Age not updated.");
            }
        }

        System.out.println(userType + " details updated successfully.");
    }


    private void deleteStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete Staff");
    
        // Ask for the staff type
        System.out.print("Enter staff type to delete (Doctor/Pharmacist/Administrator): ");
        String userType = sc.nextLine().trim();
        boolean success = false;
    
        if (userType.equalsIgnoreCase("Doctor")) {
            System.out.print("Enter Doctor's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            Doctor doctor = doctorContainer.getDoctorByHospitalId(hospitalId);
            
            if (doctor != null) {
                doctorContainer.removeUser(hospitalId);
                success = true;
                System.out.println("Doctor with Hospital ID " + hospitalId + " has been deleted.");
            } else {
                System.out.println("No Doctor found with the provided Hospital ID.");
            }
    
        } else if (userType.equalsIgnoreCase("Pharmacist")) {
            System.out.print("Enter Pharmacist's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            Pharmacist pharmacist = pharmacistContainer.getPharmacistByHospitalId(hospitalId);
            
            if (pharmacist != null) {
                pharmacistContainer.removeUser(hospitalId);
                success = true;
                System.out.println("Pharmacist with Hospital ID " + hospitalId + " has been deleted.");
            } else {
                System.out.println("No Pharmacist found with the provided Hospital ID.");
            }
    
        } else if (userType.equalsIgnoreCase("Administrator")) {
            System.out.print("Enter Administrator's Hospital ID: ");
            String hospitalId = sc.nextLine().trim();
            Administrator administrator = administratorContainer.getAdministratorByHospitalId(hospitalId);
            
            if (administrator != null) {
                administratorContainer.removeUser(hospitalId);
                success = true;
                System.out.println("Administrator with Hospital ID " + hospitalId + " has been deleted.");
            } else {
                System.out.println("No Administrator found with the provided Hospital ID.");
            }
    
        } else {
            System.out.println("Invalid staff type entered.");
            return;
        }
    
        if (!success) {
            System.out.println("Deletion failed. Please check the Hospital ID and try again.");
        }
    }    

    private void printAllStaff() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("View Staff");
            System.out.println("0. Back");
            System.out.println("1. Filtered by gender");
            System.out.println("2. Filtered by age");
            System.out.println("3. Filetered by role");
            System.out.println("4. No Fileter");
            choice = sc.nextInt();
            
            switch (choice) {
                case 0:
                    System.out.println("Returning to previous menu..."); break;
                case 1:
                    genderFilteredView(); break;
                case 2:
                    ageFilteredView(); break;
                case 3:
                    roleFilterView(); break;
                case 4:
                    noFilterView();break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 0);
    }


    public void genderFilteredView() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Gender to View:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.print("Enter the number corresponding to the gender: ");
        
        int choice = sc.nextInt();
        sc.nextLine(); 
        String gender;
        switch (choice) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            default:
                System.out.println("Invalid choice. Returning to previous menu.");
                return;
        }
        System.out.println("Staff - Gender: " + gender);
        System.out.printf("%-5s %-15s %-20s %-15s %-5s%n", "No.", "Hospital ID", "Name", "Role", "Age");
        System.out.println("-----------------------------------------------------------------------");
        int counter = 1;
    
        List<User> doctors = doctorContainer.getAllDoctors().values().stream().filter(user -> user.getGender().equalsIgnoreCase(gender)).toList();
        for (User user : doctors) {
            Doctor doctor = (Doctor) user;
            System.out.printf("%-5d %-15s %-20s %-15s %-5d%n", counter++, doctor.getHospitalId(), doctor.getName(), "Doctor", doctor.getAge());
        }
    
        List<User> pharmacists = pharmacistContainer.getAllPharmacists().values().stream().filter(user -> user.getGender().equalsIgnoreCase(gender)).toList();
        for (User user : pharmacists) {
            Pharmacist pharmacist = (Pharmacist) user;
            System.out.printf("%-5d %-15s %-20s %-15s %-5d%n", counter++, pharmacist.getHospitalId(), pharmacist.getName(), "Pharmacist", pharmacist.getAge());
        }
    
        List<User> admins = administratorContainer.getAllAdministrators().values().stream().filter(user -> user.getGender().equalsIgnoreCase(gender)).toList();
        for (User user : admins) {
            Administrator admin = (Administrator) user;
            System.out.printf("%-5d %-15s %-20s %-15s %-5d%n", counter++, admin.getHospitalId(), admin.getName(), "Administrator", admin.getAge());
        }
    
        if (counter == 1) {
            System.out.println("No staff members found with the gender: " + gender);
        }
    }
    

    public void ageFilteredView() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Age Range:");
        System.out.print("Enter lower age limit: ");
        int lowerLimit = sc.nextInt();
        
        System.out.print("Enter upper age limit: ");
        int upperLimit = sc.nextInt();
        sc.nextLine();
    
        if (lowerLimit > upperLimit) {
            System.out.println("Invalid range. The lower limit should be less than or equal to the upper limit.");
            return;
        }
    
        System.out.println("Staff - Age Range: " + lowerLimit + " to " + upperLimit);
        System.out.printf("%-5s %-15s %-20s %-15s %-10s%n", "No.", "Hospital ID", "Name", "Role", "Age");
        System.out.println("-----------------------------------------------------------------------");
    
        int counter = 1;
    
        List<Doctor> doctors = doctorContainer.getAllDoctors().values().stream()
            .filter(user -> {
                Doctor doctor = (Doctor) user;
                return doctor.getAge() >= lowerLimit && doctor.getAge() <= upperLimit;
            })
            .map(user -> (Doctor) user)
            .toList();
        
        for (Doctor doctor : doctors) {
            System.out.printf("%-5d %-15s %-20s %-15s %-10d%n", counter++, doctor.getHospitalId(), doctor.getName(), "Doctor", doctor.getAge());
        }
    
        List<Pharmacist> pharmacists = pharmacistContainer.getAllPharmacists().values().stream()
            .filter(user -> {
                Pharmacist pharmacist = (Pharmacist) user;
                return pharmacist.getAge() >= lowerLimit && pharmacist.getAge() <= upperLimit;
            })
            .map(user -> (Pharmacist) user)
            .toList();
        
        for (Pharmacist pharmacist : pharmacists) {
            System.out.printf("%-5d %-15s %-20s %-15s %-10d%n", counter++, pharmacist.getHospitalId(), pharmacist.getName(), "Pharmacist", pharmacist.getAge());
        }
    
        List<Administrator> admins = administratorContainer.getAllAdministrators().values().stream()
            .filter(user -> {
                Administrator admin = (Administrator) user;
                return admin.getAge() >= lowerLimit && admin.getAge() <= upperLimit;
            })
            .map(user -> (Administrator) user)
            .toList();
        
        for (Administrator admin : admins) {
            System.out.printf("%-5d %-15s %-20s %-15s %-10d%n", counter++, admin.getHospitalId(), admin.getName(), "Administrator", admin.getAge());
        }
    
        if (counter == 1) {
            System.out.println("No staff members found within the age range: " + lowerLimit + " to " + upperLimit);
        }
    }
    
    

    public void roleFilterView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Role to View:");
        System.out.println("1. Doctor");
        System.out.println("2. Pharmacist");
        System.out.println("3. Administrator");
        System.out.print("Enter the number corresponding to the role: ");
        
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.printf("%-5s %-15s %-20s %-15s %-10s %-5s%n", "No.", "Hospital ID", "Name", "Role", "Gender", "Age");
                System.out.println("-----------------------------------------------------------------------");
                List<User> doctors = doctorContainer.getAllDoctors().values().stream().toList();
                for (int i = 0; i < doctors.size(); i++) {
                    Doctor doctor = (Doctor) doctors.get(i);
                    System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), doctor.getHospitalId(), doctor.getName(), "Doctor", doctor.getGender(), doctor.getAge());
                }
                break;
            case 2:
                System.out.printf("%-5s %-15s %-20s %-15s %-10s %-5s%n", "No.", "Hospital ID", "Name", "Role", "Gender", "Age");
                System.out.println("-----------------------------------------------------------------------");
                List<User> pharmacists = pharmacistContainer.getAllPharmacists().values().stream().toList();
                for (int i = 0; i < pharmacists.size(); i++) {
                    Pharmacist pharmacist = (Pharmacist) pharmacists.get(i);
                    System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), pharmacist.getHospitalId(), pharmacist.getName(), "Pharmacist", pharmacist.getGender(), pharmacist.getAge());
                }
                break;
            case 3:
                System.out.printf("%-5s %-15s %-20s %-15s %-10s %-5s%n", "No.", "Hospital ID", "Name", "Role", "Gender", "Age");
                System.out.println("-----------------------------------------------------------------------");
                List<User> admins = administratorContainer.getAllAdministrators().values().stream().toList();
                for (int i = 0; i < admins.size(); i++) {
                    Administrator admin = (Administrator) admins.get(i);
                    System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), admin.getHospitalId(), admin.getName(), "Administrator", admin.getGender(), admin.getAge());
                }
                break;
            default:
                System.out.println("Invalid choice. Returning to previous menu.");
                return;
        }
    }

    public void noFilterView(){
        System.out.println("All Staff:");
        System.out.printf("%-5s %-15s %-20s %-15s %-10s %-5s%n", "No.", "Hospital ID", "Name", "Role", "Gender", "Age");
        System.out.println("-----------------------------------------------------------------------");
        // Display Doctors
        List<User> doctors = doctorContainer.getAllDoctors().values().stream().toList();
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = (Doctor) doctors.get(i);
            System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), doctor.getHospitalId(), doctor.getName(), "Doctor", doctor.getGender(), doctor.getAge());
        }
        // Display Pharmacists
        List<User> pharmacists = pharmacistContainer.getAllPharmacists().values().stream().toList();
        for (int i = 0; i < pharmacists.size(); i++) {
            Pharmacist pharmacist = (Pharmacist) pharmacists.get(i);
            System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), pharmacist.getHospitalId(), pharmacist.getName(), "Pharmacist", pharmacist.getGender(), pharmacist.getAge());
        }
        // Display Administrators
        List<User> admins = administratorContainer.getAllAdministrators().values().stream().toList();
        for (int i = 0; i < admins.size(); i++) {
            Administrator admin = (Administrator) admins.get(i);
            System.out.printf("%-5d %-15s %-20s %-15s %-10s %-5d%n", (i + 1), admin.getHospitalId(), admin.getName(), "Administrator", admin.getGender(), admin.getAge());
        }
    }
}