package com.goat.meetingroombooking.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.goat.meetingroombooking.dao.MeetingRoomDao;
import com.goat.meetingroombooking.factory.MeetingRoomFactory;

public class User {
    private int userID;
    private String name;
    private String email;
    private String phone;
    private int credits;
    private String role;

    public User() {
    }

    public User(String name, String email, String phone, int credits, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.credits = credits;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public boolean updateCredits(int roomID) {
		// TODO Auto-generated method stub
		MeetingRoomDao dao = MeetingRoomFactory.getMeetingRoomDao();
		MeetingRoom room = dao.getMeetingRoomById(roomID);
		int totalCredits = 0;
		int seatingCapacity = room.getSeatingCapacity();
		if(seatingCapacity > 5 && seatingCapacity <= 10) {
			totalCredits += 10;
		}else if(seatingCapacity > 10) {
			totalCredits += 20;
		}
		if(room.isProjector()) totalCredits += 5;
		if(room.isWifi()) totalCredits += 10;
		if(room.isConferenceCall()) totalCredits += 15;
		if(room.isWhiteboard()) totalCredits += 5;
		if(room.isWaterDispenser()) totalCredits += 5;
		if(room.isTV()) totalCredits += 10;
		if(room.isCoffeeMachine()) totalCredits += 10;
		int currCredits = getCredits();
		if(currCredits <= totalCredits) {
			setCredits(currCredits - totalCredits);
			return true;
		}
		return false;
	}
	
	public void resetCredits() {
		 if (LocalDateTime.now().getDayOfWeek() == DayOfWeek.MONDAY && LocalDateTime.now().toLocalTime().equals(LocalTime.of(6, 0))) {
			 setCredits(2000);
		 }
	}
}
