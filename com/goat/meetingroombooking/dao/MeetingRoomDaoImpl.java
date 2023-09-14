package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.exception.DaoException;
import com.goat.meetingroombooking.model.MeetingRoom;
import com.goat.meetingroombooking.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingRoomDaoImpl implements MeetingRoomDao {

    @Override
    public MeetingRoom getMeetingRoomById(int roomId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM MeetingRooms WHERE RoomID = ?")) {

            preparedStatement.setInt(1, roomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapMeetingRoom(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching meeting room by ID", e);
        }

        throw new IllegalArgumentException("Meeting room not found");
    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM MeetingRooms")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    meetingRooms.add(mapMeetingRoom(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching all meeting rooms", e);
        }
        return meetingRooms;
    }

    @Override
    public void addMeetingRoom(MeetingRoom room) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO MeetingRooms (RoomName, SeatingCapacity, Ratings, " +
                             "Projector, Wifi, ConferenceCall, Whiteboard, " +
                             "WaterDispenser, TV, CoffeeMachine, PerHourCost) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getSeatingCapacity());
            preparedStatement.setDouble(3, room.getRatings());
            preparedStatement.setBoolean(4, room.isProjector());
            preparedStatement.setBoolean(5, room.isWifi());
            preparedStatement.setBoolean(6, room.isConferenceCall());
            preparedStatement.setBoolean(7, room.isWhiteboard());
            preparedStatement.setBoolean(8, room.isWaterDispenser());
            preparedStatement.setBoolean(9, room.isTV());
            preparedStatement.setBoolean(10, room.isCoffeeMachine());
            preparedStatement.setInt(11, room.getPerHourCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error adding a new meeting room", e);
        }
    }

    @Override
    public void updateMeetingRoom(MeetingRoom room) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE MeetingRooms SET RoomName = ?, SeatingCapacity = ?, Ratings = ?, " +
                             "Projector = ?, Wifi = ?, ConferenceCall = ?, Whiteboard = ?, " +
                             "WaterDispenser = ?, TV = ?, CoffeeMachine = ?, PerHourCost = ? " +
                             "WHERE RoomID = ?")) {

            preparedStatement.setString(1, room.getRoomName());
            preparedStatement.setInt(2, room.getSeatingCapacity());
            preparedStatement.setDouble(3, room.getRatings());
            preparedStatement.setBoolean(4, room.isProjector());
            preparedStatement.setBoolean(5, room.isWifi());
            preparedStatement.setBoolean(6, room.isConferenceCall());
            preparedStatement.setBoolean(7, room.isWhiteboard());
            preparedStatement.setBoolean(8, room.isWaterDispenser());
            preparedStatement.setBoolean(9, room.isTV());
            preparedStatement.setBoolean(10, room.isCoffeeMachine());
            preparedStatement.setInt(11, room.getPerHourCost());
            preparedStatement.setInt(12, room.getRoomID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Meeting room not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating meeting room information", e);
        }
    }

    @Override
    public void deleteMeetingRoom(int roomId) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM MeetingRooms WHERE RoomID = ?")) {

            preparedStatement.setInt(1, roomId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("Meeting room not found");
            }
        } catch (SQLException e) {
            throw new DaoException("Error deleting meeting room", e);
        }
    }

    private MeetingRoom mapMeetingRoom(ResultSet resultSet) throws SQLException {
        MeetingRoom room = new MeetingRoom();
        room.setRoomID(resultSet.getInt("RoomID"));
        room.setRoomName(resultSet.getString("RoomName"));
        room.setSeatingCapacity(resultSet.getInt("SeatingCapacity"));
        room.setRatings(resultSet.getDouble("Ratings"));
        room.setProjector(resultSet.getBoolean("Projector"));
        room.setWifi(resultSet.getBoolean("Wifi"));
        room.setConferenceCall(resultSet.getBoolean("ConferenceCall"));
        room.setWhiteboard(resultSet.getBoolean("Whiteboard"));
        room.setWaterDispenser(resultSet.getBoolean("WaterDispenser"));
        room.setTV(resultSet.getBoolean("TV"));
        room.setCoffeeMachine(resultSet.getBoolean("CoffeeMachine"));
        room.setPerHourCost(resultSet.getInt("PerHourCost"));
        return room;
    }
}
