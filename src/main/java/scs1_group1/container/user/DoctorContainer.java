package scs1_group1.container.user;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

import scs1_group1.user.Doctor;
import scs1_group1.user.User;

public class DoctorContainer extends StaffContainer {
    public DoctorContainer(String filePath) {
        super(filePath, "Doctor"); // Only import rows with userType "Doctor"
    }

    //append doctor to csv by extending staff container
    public void appendDoctorToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "doctor");
    }

    //give out all doctor as hashmap
    public HashMap <String, User> getAllDoctors(){
        return this.getAllUsersByUserType("Doctor");
    }

    //get doctor by hospital id
    public Doctor getDoctorByHospitalId(String hospitalId){
        return (Doctor) this.getUserByHospitalId(hospitalId);
    }

    // Export doctor additionals to CSV
    public void exportDoctorAdditionalsToCSV(String Doctor_Additional_Path) {
        try (FileWriter writer = new FileWriter(Doctor_Additional_Path)) {
            // Write header
            writer.write("DoctorHospitalId,AvailableSlots,PatientsUnderCare\n");

            // Write each doctor's additional data
            for (Doctor doctor : getAllDoctors().values().stream().map(d -> (Doctor) d).collect(Collectors.toList())) {
                String doctorId = doctor.getHospitalId();
                String availableSlots = String.join(";", doctor.getAvailableSlots());
                String patientsUnderCare = String.join(";", doctor.getAllPatientsUnderCare());

                writer.write(String.format("%s,%s,%s\n", doctorId, availableSlots, patientsUnderCare));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}