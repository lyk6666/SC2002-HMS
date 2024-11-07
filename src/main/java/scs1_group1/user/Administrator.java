package scs1_group1.user;

public class Administrator extends Staff {
    
    public Administrator(
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
    
    // Add any additional Administrator-specific methods here if needed
}
