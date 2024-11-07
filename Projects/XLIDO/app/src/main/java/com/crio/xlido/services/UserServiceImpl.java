package com.crio.xlido.services;

import com.crio.xlido.entities.User;
import java.util.HashMap;

public class UserServiceImpl implements UserService {

    private HashMap<Integer, User> users = new HashMap<>();
    private int currentUserId = 1;

    @Override
    public int createUser(String email, String password) {
        User user = new User(currentUserId, email, password);
        users.put(currentUserId, user);
        return currentUserId++;
   
    }

    @Override
    public User getUserById(int userId) {
        return users.get(userId);
    }
}
