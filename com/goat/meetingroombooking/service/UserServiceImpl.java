package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.dao.UserDao;
import com.goat.meetingroombooking.factory.UserFactory;
import com.goat.meetingroombooking.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = UserFactory.getUserDao();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public void resetCredits() {
    	List<User> users = getAllUsers();
    	for(User user : users) {
    		if(user.getRole() == "Manager") {
    			user.resetCredits();
    		}
    	}
    }
}
