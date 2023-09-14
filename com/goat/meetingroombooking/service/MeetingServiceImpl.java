package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.dao.MeetingDao;
import com.goat.meetingroombooking.dao.MeetingRoomDao;
import com.goat.meetingroombooking.exception.ServiceException;
import com.goat.meetingroombooking.factory.MeetingFactory;
import com.goat.meetingroombooking.factory.MeetingRoomFactory;
import com.goat.meetingroombooking.model.Meeting;
import com.goat.meetingroombooking.model.MeetingRoom;

import java.util.List;

public class MeetingServiceImpl implements MeetingService {

    private final MeetingDao meetingDao;
    private final MeetingRoomDao meetingRoomDao;

    public MeetingServiceImpl() {
        this.meetingDao = MeetingFactory.getMeetingDao();
        this.meetingRoomDao = MeetingRoomFactory.getMeetingRoomDao();
    }

    @Override
    public Meeting getMeetingById(int meetingId) {
        return meetingDao.getMeetingById(meetingId);
    }

    @Override
    public void addMeeting(Meeting meeting) {
    	int roomId = meeting.getBookingInformation().getRoomID();
    	MeetingRoom room = meetingRoomDao.getMeetingRoomById(roomId);
    	switch(meeting.getMeetingType()) {
    		case "Classroom Training": 
    			if(!room.isWhiteboard() || !room.isProjector()) {
    				throw new ServiceException("Cannot add meeting. Lack of required amenities.");
    			}
    			break;
    		case "Online Training":
    			if(!room.isWifi() || !room.isProjector()) {
    				throw new ServiceException("Cannot add meeting. Lack of required amenities.");
    			}
    			break;
    		case "Conference Call":
    			if(!room.isConferenceCall()) {
    				throw new ServiceException("Cannot add meeting. Lack of required amenities.");
    			}
    			break;
    		case "Business":
    			if(!room.isProjector()) {
    				throw new ServiceException("Cannot add meeting. Lack of required amenities.");
    			}
    			break;
    	}
        meetingDao.addMeeting(meeting);
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        meetingDao.updateMeeting(meeting);
    }

    @Override
    public void deleteMeeting(int meetingId) {
        meetingDao.deleteMeeting(meetingId);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingDao.getAllMeetings();
    }
}
