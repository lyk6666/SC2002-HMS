package scs1_group1.container.user;

public class AdministratorContainer extends StaffContainer {
    public AdministratorContainer(String filePath) {
        super(filePath, "Administrator"); // Only import rows with userType "Administrator"
    }
}
