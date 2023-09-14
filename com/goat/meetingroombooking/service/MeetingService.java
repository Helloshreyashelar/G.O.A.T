package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.model.Meeting;

import java.util.List;

public interface MeetingService {
    Meeting getMeetingById(int meetingId);
    void addMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(int meetingId);
    List<Meeting> getAllMeetings();
}
