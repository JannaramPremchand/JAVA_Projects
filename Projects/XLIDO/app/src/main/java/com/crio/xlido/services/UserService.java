package com.crio.xlido.services;

import com.crio.xlido.entities.User;

public interface UserService {
    int createUser(String email, String password);
    User getUserById(int userId); // Method to get user by ID
}
