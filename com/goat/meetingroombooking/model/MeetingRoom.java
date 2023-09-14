package com.goat.meetingroombooking.model;

public class MeetingRoom {
    private int roomID;
    private String roomName;
    private int seatingCapacity;
    private double ratings;
    private boolean projector;
    private boolean wifi;
    private boolean conferenceCall;
    private boolean whiteboard;
    private boolean waterDispenser;
    private boolean TV;
    private boolean coffeeMachine;
    private int perHourCost;

    public MeetingRoom() {
    }

    public MeetingRoom(
            String roomName, int seatingCapacity, double ratings,
            boolean projector, boolean wifi, boolean conferenceCall,
            boolean whiteboard, boolean waterDispenser, boolean TV, boolean coffeeMachine,
            int perHourCost) {
        this.roomName = roomName;
        this.seatingCapacity = seatingCapacity;
        this.ratings = ratings;
        this.projector = projector;
        this.wifi = wifi;
        this.conferenceCall = conferenceCall;
        this.whiteboard = whiteboard;
        this.waterDispenser = waterDispenser;
        this.TV = TV;
        this.coffeeMachine = coffeeMachine;
        this.perHourCost = perHourCost;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isConferenceCall() {
        return conferenceCall;
    }

    public void setConferenceCall(boolean conferenceCall) {
        this.conferenceCall = conferenceCall;
    }

    public boolean isWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }

    public boolean isWaterDispenser() {
        return waterDispenser;
    }

    public void setWaterDispenser(boolean waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(boolean coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public int getPerHourCost() {
        return perHourCost;
    }

    public void setPerHourCost(int perHourCost) {
        this.perHourCost = perHourCost;
    }
}
