package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.model.MeetingRoom;

import java.util.List;

public interface MeetingRoomDao {
    MeetingRoom getMeetingRoomById(int roomId);
    List<MeetingRoom> getAllMeetingRooms();
    void addMeetingRoom(MeetingRoom room);
    void updateMeetingRoom(MeetingRoom room);
    void deleteMeetingRoom(int roomId);
}
