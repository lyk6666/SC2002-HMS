package scs1_group1.container.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import scs1_group1.user.Administrator;
import scs1_group1.user.Doctor;
import scs1_group1.user.Pharmacist;

public class StaffContainer extends UserContainer {

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
                String staffId = fields[0].trim();      // "Staff ID"
                String name = fields[1].trim();         // "Name"
                String rowUserType = fields[2].trim();  // "Role"
                String gender = fields[3].trim();       // "Gender"
                int age = Integer.parseInt(fields[4].trim()); // "Age"
                
                // Directly create and put the correct subclass based on userType
                if (rowUserType.equalsIgnoreCase("Doctor")) {
                    putUser(new Doctor(staffId, "password", name, gender, rowUserType, "", age));
                } else if (rowUserType.equalsIgnoreCase("Pharmacist")) {
                    putUser(new Pharmacist(staffId, "password", name, gender, rowUserType, "", age));
                } else if (rowUserType.equalsIgnoreCase("Administrator")) {
                    putUser(new Administrator(staffId, "password", name, gender, rowUserType, "", age));
                } else {
                    System.out.println("Skipping unrecognized role: " + rowUserType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
