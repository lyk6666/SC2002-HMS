package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.user.Doctor;
import scs1_group1.user.User;

public class DoctorContainer extends StaffContainer {
    public DoctorContainer(String filePath) {
        super(filePath, "Doctor"); // Only import rows with userType "Doctor"
    }

    //give out all doctor as hashmap
    public HashMap <String, User> getAllDoctors(){
        return this.getAllUsersByUserType("Doctor");
    }

    //get doctor by hospital id
    public Doctor getDoctorByHospitalId(String hospitalId){
        return (Doctor) this.getUserByHospitalId(hospitalId);
    }
}
