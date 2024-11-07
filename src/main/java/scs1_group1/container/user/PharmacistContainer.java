package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.User;

public class PharmacistContainer extends StaffContainer {
    public PharmacistContainer(String filePath) {
        super(filePath, "Pharmacist"); // Only import rows with userType "Pharmacist"
    }

    //give out all pharmacist as hashmap
    public HashMap <String, User> getAllPharmacists(){
        return this.getAllUsersByUserType("Pharmacist");
    }
}
