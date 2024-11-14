package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.Administrator;
import scs1_group1.user.User;

public class AdministratorContainer extends StaffContainer {
    public AdministratorContainer(String filePath) {
        super(filePath, "Administrator"); // Only import rows with userType "Administrator"
    }

    //append administrator to csv by extending staff container
    public void appendAdministratorToCSV(String filePath) {
        appendStaffToCSVByStaffType(filePath, "administrator");
    }

    //give out all admins as hashmap
    public HashMap <String, User> getAllAdministrators(){
        return this.getAllUsersByUserType("Administrator");
    }

    //get admin by hospital id
    public Administrator getAdministratorByHospitalId(String hospitalId){
        return (Administrator) this.getUserByHospitalId(hospitalId);
    }
}