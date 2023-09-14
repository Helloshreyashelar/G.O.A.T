package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.model.User;

import java.util.List;

public interface UserDao {
    User getUserById(int userId);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
}
