package com.goat.meetingroombooking.model;

import java.util.List;

public class Meeting {
    private int meetingID;
    private String title;
    private int organizedBy;
    private String meetingDate;
    private String startTime;
    private String endTime;
    private String meetingType;
    private List<Integer> members;
    private BookingInformation bookingInformation;

    public Meeting() {
    }

    public Meeting(
            String title, int organizedBy, String meetingDate, String startTime, String endTime,
            String meetingType, List<Integer> members, BookingInformation bookingInformation) {
        this.title = title;
        this.organizedBy = organizedBy;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingType = meetingType;
        this.members = members;
        this.bookingInformation = bookingInformation;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrganizedBy() {
        return organizedBy;
    }

    public void setOrganizedBy(int organizedBy) {
        this.organizedBy = organizedBy;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public BookingInformation getBookingInformation() {
        return bookingInformation;
    }

    public void setBookingInformation(BookingInformation bookingInformation) {
        this.bookingInformation = bookingInformation;
    }
}
