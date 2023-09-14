package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.model.User;

import java.util.List;

public interface UserService {
    User getUserById(int userId);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    public void resetCredits();
}
