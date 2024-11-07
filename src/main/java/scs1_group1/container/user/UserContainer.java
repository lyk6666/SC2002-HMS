package scs1_group1.container.user;

import java.util.HashMap;

import scs1_group1.container.Container;
import scs1_group1.user.User;

public class UserContainer implements Container {
    private final HashMap<String, User> users;

    public UserContainer() {
        users = new HashMap<>();
    }

    // Add a user to the container
    public void putUser(User user) {
        users.put(user.getHospitalId(), user);
    }

    // Check if a user with a specific hospital ID exists
    public Boolean containsUser(String hospitalId) {
        return users.containsKey(hospitalId);
    }

    // Get user by hospital ID
    public User getUserByHospitalId(String hospitalId) {
        return users.get(hospitalId);
    }

    // Remove a user by hospital ID
    public void removeUser(String hospitalId) {
        users.remove(hospitalId);
    }

    //get usertype by hospital id
    public String getUserTypeByHospitalId(String hospitalId) {
        return users.get(hospitalId).getUserType();
    }

    // Clear all users
    public void clear() {
        users.clear();
    }

    // get all user of a specific usertype and return a hashmap
    public HashMap<String, User> getAllUsersByUserType(String userType) {
        HashMap<String, User> result = new HashMap<>();
        for (User user : users.values()) {
            if (user.getUserType().equals(userType)) {
                result.put(user.getHospitalId(), user);
            }
        }
        return result;
    }



    @Override
    public String toString() {//
        StringBuilder result = new StringBuilder();
        for (User user : users.values()) {
            result.append(user.getHospitalId()).append(": ").append(user.toString()).append("\n");
        }
        return result.toString();
    }
}
