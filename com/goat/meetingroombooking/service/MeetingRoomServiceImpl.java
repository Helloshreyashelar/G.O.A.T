package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.dao.MeetingRoomDao;
import com.goat.meetingroombooking.factory.MeetingRoomFactory;
import com.goat.meetingroombooking.model.MeetingRoom;

import java.util.List;

public class MeetingRoomServiceImpl implements MeetingRoomService {

    private final MeetingRoomDao meetingRoomDao;

    public MeetingRoomServiceImpl() {
        this.meetingRoomDao = MeetingRoomFactory.getMeetingRoomDao();
    }

    @Override
    public MeetingRoom getMeetingRoomById(int roomId) {
        return meetingRoomDao.getMeetingRoomById(roomId);
    }

    @Override
    public void addMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomDao.addMeetingRoom(meetingRoom);
    }

    @Override
    public void updateMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomDao.updateMeetingRoom(meetingRoom);
    }

    @Override
    public void deleteMeetingRoom(int roomId) {
        meetingRoomDao.deleteMeetingRoom(roomId);
    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        return meetingRoomDao.getAllMeetingRooms();
    }
}
