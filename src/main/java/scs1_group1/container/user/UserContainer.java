package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.container.Container;
import scs1_group1.user.User;

/**
 * Container class for managing User records.
 * Provides functionalities to add, retrieve, and manage user data.
 */
public class UserContainer implements Container {
    private final HashMap<String, User> users;

    /**
     * Constructs a UserContainer to manage user records.
     */
    public UserContainer() {
        users = new HashMap<>();
    }

    /**
     * Adds a user to the container.
     * 
     * @param user The User object to be added.
     */
    public void putUser(User user) {
        users.put(user.getHospitalId(), user);
    }

    /**
     * Checks if a user with a specific hospital ID exists.
     * 
     * @param hospitalId The hospital ID of the user to check.
     * @return True if the user exists, otherwise false.
     */
    public Boolean containsUser(String hospitalId) {
        return users.containsKey(hospitalId);
    }

    /**
     * Retrieves a user by their hospital ID.
     * 
     * @param hospitalId The hospital ID of the user to retrieve.
     * @return The User object if found, otherwise null.
     */
    public User getUserByHospitalId(String hospitalId) {
        return users.get(hospitalId);
    }

    /**
     * Removes a user by their hospital ID.
     * 
     * @param hospitalId The hospital ID of the user to be removed.
     */
    public void removeUser(String hospitalId) {
        users.remove(hospitalId);
    }

    /**
     * Retrieves the user type by hospital ID.
     * 
     * @param hospitalId The hospital ID of the user.
     * @return The user type as a string.
     */
    public String getUserTypeByHospitalId(String hospitalId) {
        return users.get(hospitalId).getUserType();
    }

    /**
     * Clears all users from the container.
     */
    public void clear() {
        users.clear();
    }

    /**
     * Retrieves all users of a specific user type.
     * 
     * @param userType The type of user to retrieve.
     * @return A HashMap containing all users of the specified type.
     */
    public HashMap<String, User> getAllUsersByUserType(String userType) {
        HashMap<String, User> result = new HashMap<>();
        for (User user : users.values()) {
            if (user.getUserType().equals(userType)) {
                result.put(user.getHospitalId(), user);
            }
        }
        return result;
    }



    /**
     * Returns a string representation of all users in the container.
     * 
     * @return A formatted string with all user details.
     */
    @Override
    public String toString() {//
        StringBuilder result = new StringBuilder();
        for (User user : users.values()) {
            result.append(user.getHospitalId()).append(": ").append(user.toString()).append("\n");
        }
        return result.toString();
    }
}
