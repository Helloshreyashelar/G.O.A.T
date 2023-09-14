package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.model.Meeting;

import java.util.List;

public interface MeetingDao {
    Meeting getMeetingById(int meetingId);
    List<Meeting> getAllMeetings();
    void addMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(int meetingId);
}
