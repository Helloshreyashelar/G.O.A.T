package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.model.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {
    MeetingRoom getMeetingRoomById(int roomId);
    void addMeetingRoom(MeetingRoom meetingRoom);
    void updateMeetingRoom(MeetingRoom meetingRoom);
    void deleteMeetingRoom(int roomId);
    List<MeetingRoom> getAllMeetingRooms();
}
