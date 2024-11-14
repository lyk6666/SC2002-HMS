package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.Pharmacist;
import scs1_group1.user.User;

public class PharmacistContainer extends StaffContainer {
    public PharmacistContainer(String filePath) {
        super(filePath, "Pharmacist"); // Only import rows with userType "Pharmacist"
    }

    //append pharmacist to csv by extending staff container
    public void appendPharmacistToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "pharmacist");
    }

    //give out all pharmacist as hashmap
    public HashMap <String, User> getAllPharmacists(){
        return this.getAllUsersByUserType("Pharmacist");
    }

    //get pharmacist by hospital id
    public Pharmacist getPharmacistByHospitalId(String hospitalId){
        return (Pharmacist) this.getUserByHospitalId(hospitalId);
    }
}
