package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.exception.DaoException;
import com.goat.meetingroombooking.model.BookingInformation;
import com.goat.meetingroombooking.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingInformationDaoImpl implements BookingInformationDao {

    @Override
    public BookingInformation getBookingInformationById(int bookingId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM BookingInformation WHERE BookingID = ?")) {

            preparedStatement.setInt(1, bookingId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapBookingInformation(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching booking information by ID", e);
        }

        throw new IllegalArgumentException("Booking information not found");
    }

    @Override
    public List<BookingInformation> getAllBookings() {
        List<BookingInformation> bookings = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM BookingInformation")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    bookings.add(mapBookingInformation(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching all booking information", e);
        }
        return bookings;
    }

    @Override
    public void addBookingInformation(BookingInformation booking) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO BookingInformation (RoomID, BookingDate, StartTime, EndTime, BookedBy) " +
                             "VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, booking.getRoomID());

            // Convert String to java.sql.Date
            java.sql.Date bookingDate = java.sql.Date.valueOf(booking.getBookingDate());
            preparedStatement.setDate(2, bookingDate);

            // Convert String to java.sql.Time
            java.sql.Time startTime = java.sql.Time.valueOf(booking.getStartTime());
            preparedStatement.setTime(3, startTime);

            // Convert String to java.sql.Time
            java.sql.Time endTime = java.sql.Time.valueOf(booking.getEndTime());
            preparedStatement.setTime(4, endTime);

            preparedStatement.setInt(5, booking.getBookedBy());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding booking information", e);
        }
    }

    @Override
    public void updateBookingInformation(BookingInformation booking) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE BookingInformation SET RoomID = ?, BookingDate = ?, " +
                             "StartTime = ?, EndTime = ?, BookedBy = ? WHERE BookingID = ?")) {

            preparedStatement.setInt(1, booking.getRoomID());

            // Convert String to java.sql.Date
            java.sql.Date bookingDate = java.sql.Date.valueOf(booking.getBookingDate());
            preparedStatement.setDate(2, bookingDate);

            // Convert String to java.sql.Time
            java.sql.Time startTime = java.sql.Time.valueOf(booking.getStartTime());
            preparedStatement.setTime(3, startTime);

            // Convert String to java.sql.Time
            java.sql.Time endTime = java.sql.Time.valueOf(booking.getEndTime());
            preparedStatement.setTime(4, endTime);

            preparedStatement.setInt(5, booking.getBookedBy());
            preparedStatement.setInt(6, booking.getBookingID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Booking information not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating booking information", e);
        }
    }

    @Override
    public void deleteBookingInformation(int bookingId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM BookingInformation WHERE BookingID = ?")) {

            preparedStatement.setInt(1, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Booking information not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deleting booking information", e);
        }
    }

    private BookingInformation mapBookingInformation(ResultSet resultSet) throws SQLException {
        BookingInformation booking = new BookingInformation();
        booking.setBookingID(resultSet.getInt("BookingID"));
        booking.setRoomID(resultSet.getInt("RoomID"));
        booking.setBookingDate(resultSet.getString("BookingDate"));
        booking.setStartTime(resultSet.getString("StartTime"));
        booking.setEndTime(resultSet.getString("EndTime"));
        booking.setBookedBy(resultSet.getInt("BookedBy"));
        return booking;
    }
}
