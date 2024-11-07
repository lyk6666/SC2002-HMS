package scs1_group1.user;

public class Pharmacist extends Staff {
    
    public Pharmacist(
        String hospitalId, 
        String password, 
        String name, 
        String gender, 
        String userType, 
        String email,
        int age
    ) {
        super(hospitalId, password, name, gender, userType, email, age);
    }
    
    // Add any additional Pharmacist-specific methods here if needed
}
