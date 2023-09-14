package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.exception.DaoException;
import com.goat.meetingroombooking.model.User;
import com.goat.meetingroombooking.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(int userId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Users WHERE UserID = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching user by ID", e);
        }

        throw new IllegalArgumentException("User not found");
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Users WHERE Email = ?")) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching user by email", e);
        }

        throw new IllegalArgumentException("User not found");
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Users (Name, Email, Phone, Credits, Role) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setInt(4, user.getCredits());
            preparedStatement.setString(5, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding a new user", e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Users SET Name = ?, Phone = ?, Credits = ?, Role = ? WHERE UserID = ?")) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setInt(3, user.getCredits());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("User not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating user information", e);
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Users WHERE UserID = ?")) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("User not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deleting user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Users")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(mapUser(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching all users", e);
        }
        return users;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("UserID"));
        user.setName(resultSet.getString("Name"));
        user.setEmail(resultSet.getString("Email"));
        user.setPhone(resultSet.getString("Phone"));
        user.setCredits(resultSet.getInt("Credits"));
        user.setRole(resultSet.getString("Role")); // Assuming Role is a String
        return user;
    }
}
