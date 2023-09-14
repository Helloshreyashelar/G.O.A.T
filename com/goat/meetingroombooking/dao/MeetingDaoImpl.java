package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.model.BookingInformation;
import com.goat.meetingroombooking.model.Meeting;
import com.goat.meetingroombooking.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoImpl implements MeetingDao {

    @Override
    public Meeting getMeetingById(int meetingId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Meetings WHERE MeetingID = ?")) {

            preparedStatement.setInt(1, meetingId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapMeeting(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching meeting by ID", e);
        }

        throw new IllegalArgumentException("Meeting not found");
    }

    @Override
    public List<Meeting> getAllMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Meetings")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    meetings.add(mapMeeting(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all meetings", e);
        }
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {//booking info id
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Meetings (Title, OrganizedBy, MeetingDate, StartTime, EndTime, MeetingType, BookingID) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, meeting.getTitle());
            preparedStatement.setInt(2, meeting.getOrganizedBy());
            preparedStatement.setString(3, meeting.getMeetingDate());
            preparedStatement.setString(4, meeting.getStartTime());
            preparedStatement.setString(5, meeting.getEndTime());
            preparedStatement.setString(6, meeting.getMeetingType());
            preparedStatement.setInt(7, meeting.getBookingInformation().getBookingID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding a new meeting", e);
        }
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Meetings SET Title = ?, OrganizedBy = ?, MeetingDate = ?, " +
                             "StartTime = ?, EndTime = ?, MeetingType = ?, BookingID = ? WHERE MeetingID = ?")) {

            preparedStatement.setString(1, meeting.getTitle());
            preparedStatement.setInt(2, meeting.getOrganizedBy());
            preparedStatement.setString(3, meeting.getMeetingDate());
            preparedStatement.setString(4, meeting.getStartTime());
            preparedStatement.setString(5, meeting.getEndTime());
            preparedStatement.setString(6, meeting.getMeetingType());
            preparedStatement.setInt(7, meeting.getBookingInformation().getBookingID());
            preparedStatement.setInt(8, meeting.getMeetingID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Meeting not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating meeting information", e);
        }
    }

    @Override
    public void deleteMeeting(int meetingId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Meetings WHERE MeetingID = ?")) {

            preparedStatement.setInt(1, meetingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Meeting not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting meeting", e);
        }
    }

    private Meeting mapMeeting(ResultSet resultSet) throws SQLException {
        Meeting meeting = new Meeting();
        meeting.setMeetingID(resultSet.getInt("MeetingID"));
        meeting.setTitle(resultSet.getString("Title"));
        meeting.setOrganizedBy(resultSet.getInt("OrganizedBy"));
        meeting.setMeetingDate(resultSet.getString("MeetingDate"));
        meeting.setStartTime(resultSet.getString("StartTime"));
        meeting.setEndTime(resultSet.getString("EndTime"));
        meeting.setMeetingType(resultSet.getString("MeetingType"));
        int bookingId = resultSet.getInt("BookingID");
        BookingInformation booking = new BookingInformationDaoImpl().getBookingInformationById(bookingId);
        meeting.setBookingInformation(booking);
        return meeting;
    }
}
