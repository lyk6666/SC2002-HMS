package scs1_group1.container.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import scs1_group1.user.Administrator;
import scs1_group1.user.Doctor;
import scs1_group1.user.Pharmacist;
import scs1_group1.user.Staff;


/**
 * Container class for managing Staff records, including Doctors, Pharmacists, and Administrators.
 * Provides functionalities to load, retrieve, add, and export Staff data.
 */
public class StaffContainer extends UserContainer {

    /**
     * Constructs a StaffContainer and loads staff from the given CSV file.
     * 
     * @param filePath Path to the CSV file containing staff data.
     * @param userType The type of user to be loaded (e.g., "Doctor", "Pharmacist", "Administrator").
     */
    public StaffContainer(String filePath, String userType) {
        super();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true; // Flag to skip the header line

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the first line (header)
                    continue;
                }

                String[] fields = line.split(",");
                String staffId = fields[0].trim();       // "Staff ID"
                String password = fields[1].trim();      // "Password"
                String name = fields[2].trim();          // "Name"
                String rowUserType = fields[3].trim();   // "Role"
                String gender = fields[4].trim();        // "Gender"
                int age = Integer.parseInt(fields[5].trim()); // "Age"
                
                // Create and add the correct subclass based on role type
                if (rowUserType.equalsIgnoreCase("Doctor")) {
                    putUser(new Doctor(staffId, password, name, gender, rowUserType, "", age));
                } else if (rowUserType.equalsIgnoreCase("Pharmacist")) {
                    putUser(new Pharmacist(staffId, password, name, gender, rowUserType, "", age));
                } else if (rowUserType.equalsIgnoreCase("Administrator")) {
                    putUser(new Administrator(staffId, password, name, gender, rowUserType, "", age));
                } else {
                    System.out.println("Skipping unrecognized role: " + rowUserType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new staff member to the container.
     * 
     * @param staffId  The hospital ID of the staff.
     * @param password The password of the staff.
     * @param name     The name of the staff.
     * @param gender   The gender of the staff.
     * @param userType The type of user (e.g., Doctor, Pharmacist, Administrator).
     * @param email    The email of the staff.
     * @param age      The age of the staff.
     */
    public void addStaff(String staffId, String password, String name, String gender, String userType, String email, int age) {
        if (userType.equalsIgnoreCase("Doctor")) {
            putUser(new Doctor(staffId, password, name, gender, userType, email, age));
        } else if (userType.equalsIgnoreCase("Pharmacist")) {
            putUser(new Pharmacist(staffId, password, name, gender, userType, email, age));
        } else if (userType.equalsIgnoreCase("Administrator")) {
            putUser(new Administrator(staffId, password, name, gender, userType, email, age));
        } else {
        }
    }

    /**
     * Initializes the CSV file for staff with the appropriate header.
     * 
     * @param filePath Path to the CSV file that will be initialized.
     */
    public void initializeStaffCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the header
            writer.write("Staff ID,Password,Name,Role,Gender,Age\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends specific staff type records to the CSV file.
     * 
     * @param filePath Path to the CSV file where staff data will be appended.
     * @param staffType The type of staff to be appended (e.g., "doctor", "pharmacist").
     */
    public void appendStaffToCSVByStaffType(String filePath, String staffType) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // `true` enables appending mode
            List<Staff> staffList = getAllUsersByUserType(staffType).values().stream()
                .map(user -> (Staff) user)
                .collect(Collectors.toList());

            for (Staff staff : staffList) {
                writeStaffToCSV(writer, staff, staffType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Helper method to write individual staff entries to a CSV file.
     * 
     * @param writer The FileWriter to write the data to the CSV file.
     * @param staff The Staff object containing the staff's information.
     * @param role The role of the staff (e.g., Doctor, Pharmacist).
     * @throws IOException If there is an error writing to the file.
     */
    private void writeStaffToCSV(FileWriter writer, Staff staff, String role) throws IOException {
        writer.write(
            String.format(
                "%s,%s,%s,%s,%s,%d\n",
                staff.getHospitalId(),
                staff.getPassword(),
                staff.getName(),
                role,
                staff.getGender(),
                staff.getAge()
            )
        );
    }

}
