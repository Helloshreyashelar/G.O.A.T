package com.goat.meetingroombooking.view;

import com.goat.meetingroombooking.dao.BookingInformationDaoImpl;
import com.goat.meetingroombooking.dao.MeetingDaoImpl;
import com.goat.meetingroombooking.dao.MeetingRoomDaoImpl;
import com.goat.meetingroombooking.dao.UserDaoImpl;
import com.goat.meetingroombooking.model.*;
import com.goat.meetingroombooking.service.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final UserService userService;
    private final MeetingService meetingService;
    private final MeetingRoomService meetingRoomService;
    private final BookingInformationService bookingInformationService;
    private final Scanner scanner;

    public ConsoleUI(
            UserService userService,
            MeetingService meetingService,
            MeetingRoomService meetingRoomService,
            BookingInformationService bookingInformationService
    ) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.meetingRoomService = meetingRoomService;
        this.bookingInformationService = bookingInformationService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Meeting Room Booking System");
    
        // Request user input for name and ID
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        // Retrieve user by ID
        User user = userService.getUserById(userId);
    
        if (user != null && user.getName().equals(name)) {
            // Check user's role and grant access accordingly
            String userRole = user.getRole();
    
            if ("Admin".equalsIgnoreCase(userRole)) {
                // Admin menu options
                System.out.println("Access granted. You have admin privileges.");
                while (true) {
                    // Admin menu options
                    System.out.println("\nOptions:");
                    System.out.println("1. Manage Users");
                    System.out.println("2. Manage Meetings");
                    System.out.println("3. Manage Meeting Rooms");
                    System.out.println("4. Manage Booking Information");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
    
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
    
                    switch (choice) {
                        case 1:
                            manageUsers();
                            break;
                        case 2:
                            manageMeetings();
                            break;
                        case 3:
                            manageMeetingRooms();
                            break;
                        case 4:
                            manageBookingInformation();
                            break;
                        case 5:
                            System.out.println("Exiting the application.");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if ("Manager".equalsIgnoreCase(userRole)) {
                // Manager menu options
                System.out.println("Access granted. You have manager privileges.");
                while (true) {
                    // Manager menu options
                    System.out.println("\nOptions:");
                    System.out.println("1. Manage Meetings");
                    System.out.println("2. Manage Booking Information");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
    
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
    
                    switch (choice) {
                        case 1:
                            manageMeetings();
                            break;
                        case 2:
                            manageBookingInformation();;
                            break;
                        case 3:
                            System.out.println("Exiting the application.");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if ("Member".equalsIgnoreCase(userRole)) {
                // Member menu options
                System.out.println("Access granted. You have member privileges.");
                while (true) {
                    // Member menu options
                    System.out.println("\nOptions:");
                    System.out.println("1. View All Meeting Rooms");
                    System.out.println("2. View All Booking Information");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
    
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
    
                    switch (choice) {
                        case 1:
                            viewAllMeetingRooms();
                            break;
                        case 2:
                            viewAllBookingInformation();
                            break;
                        case 3:
                            System.out.println("Exiting the application.");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Access denied. Your role is not recognized.");
            }
        } else {
            System.out.println("Access denied. Your credentials are incorrect.");
        }
    }

    private void manageUsers() {
        while (true) {
            System.out.println("\nManage Users:");
            System.out.println("1. View All Users");
            System.out.println("2. Add User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("\nList of Users:");
        for (User user : users) {
            System.out.println("ID: " + user.getUserID() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }

    private void addUser() {
        System.out.println("\nAdd User:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Role (Admin/Manager/Member): ");
        String role = scanner.nextLine();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCredits(credits);
        user.setRole(role);

        userService.addUser(user);
        System.out.println("User added successfully.");
    }

    private void updateUser() {
        System.out.println("\nUpdate User:");
        System.out.print("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Name (Current: " + existingUser.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingUser.setName(name);
        }

        System.out.print("Email (Current: " + existingUser.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            existingUser.setEmail(email);
        }

        System.out.print("Phone (Current: " + existingUser.getPhone() + "): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            existingUser.setPhone(phone);
        }

        System.out.print("Credits (Current: " + existingUser.getCredits() + "): ");
        int credits = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        existingUser.setCredits(credits);

        System.out.print("Role (Current: " + existingUser.getRole() + "): ");
        String role = scanner.nextLine();
        if (!role.isEmpty()) {
            existingUser.setRole(role);
        }

        userService.updateUser(existingUser);
        System.out.println("User updated successfully.");
    }

    private void deleteUser() {
        System.out.println("\nDelete User:");
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            System.out.println("User not found.");
            return;
        }

        userService.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }

    private void manageMeetings() {
        while (true) {
            System.out.println("\nManage Meetings:");
            System.out.println("1. View All Meetings");
            System.out.println("2. Add Meeting");
            System.out.println("3. Update Meeting");
            System.out.println("4. Delete Meeting");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllMeetings();
                    break;
                case 2:
                    addMeeting();
                    break;
                case 3:
                    updateMeeting();
                    break;
                case 4:
                    deleteMeeting();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllMeetings() {
        List<Meeting> meetings = meetingService.getAllMeetings();
        System.out.println("\nList of Meetings:");
        for (Meeting meeting : meetings) {
            System.out.println("ID: " + meeting.getMeetingID() + ", Title: " + meeting.getTitle() +
                    ", Date: " + meeting.getMeetingDate() + ", Type: " + meeting.getMeetingType());
        }
    }

    private void addMeeting() {
        System.out.println("\nAdd Meeting:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Organized By (User ID): ");
        int organizedBy = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Meeting Date (yyyy-MM-dd): ");
        String meetingDate = scanner.nextLine();
        System.out.print("Start Time (HH:mm:ss): ");
        String startTime = scanner.nextLine();
        System.out.print("End Time (HH:mm:ss): ");
        String endTime = scanner.nextLine();
        System.out.print("Meeting Type (Classroom Training/Online Training/Conference Call/Business): ");
        String meetingType = scanner.nextLine();
        System.out.print("Comma-separated List of Members (User IDs): ");
        String membersInput = scanner.nextLine();
        List<Integer> members = parseUserIds(membersInput);
        System.out.print("Booking Information ID: ");
        int bookingInformation = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setOrganizedBy(organizedBy);
        meeting.setMeetingDate(meetingDate);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meeting.setMeetingType(meetingType);
        meeting.setMembers(members);
        meeting.setBookingInformation(this.bookingInformationService.getBookingInformationById(bookingInformation));

        meetingService.addMeeting(meeting);
        System.out.println("Meeting added successfully.");
    }

    private void updateMeeting() {
        System.out.println("\nUpdate Meeting:");
        System.out.print("Enter Meeting ID to update: ");
        int meetingId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Meeting existingMeeting = meetingService.getMeetingById(meetingId);
        if (existingMeeting == null) {
            System.out.println("Meeting not found.");
            return;
        }

        System.out.print("Title (Current: " + existingMeeting.getTitle() + "): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            existingMeeting.setTitle(title);
        }

        System.out.print("Organized By (User ID) (Current: " + existingMeeting.getOrganizedBy() + "): ");
        int organizedBy = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        existingMeeting.setOrganizedBy(organizedBy);

        System.out.print("Meeting Date (yyyy-MM-dd) (Current: " + existingMeeting.getMeetingDate() + "): ");
        String meetingDate = scanner.nextLine();
        if (!meetingDate.isEmpty()) {
            existingMeeting.setMeetingDate(meetingDate);
        }

        System.out.print("Start Time (HH:mm:ss) (Current: " + existingMeeting.getStartTime() + "): ");
        String startTime = scanner.nextLine();
        if (!startTime.isEmpty()) {
            existingMeeting.setStartTime(startTime);
        }

        System.out.print("End Time (HH:mm:ss) (Current: " + existingMeeting.getEndTime() + "): ");
        String endTime = scanner.nextLine();
        if (!endTime.isEmpty()) {
            existingMeeting.setEndTime(endTime);
        }

        System.out.print("Meeting Type (Classroom Training/Online Training/Conference Call/Business) " +
                "(Current: " + existingMeeting.getMeetingType() + "): ");
        String meetingType = scanner.nextLine();
        if (!meetingType.isEmpty()) {
            existingMeeting.setMeetingType(meetingType);
        }

        System.out.print("Comma-separated List of Members (User IDs) (Current: " +
                existingMeeting.getMembers() + "): ");
        String membersInput = scanner.nextLine();
        if (!membersInput.isEmpty()) {
            List<Integer> members = parseUserIds(membersInput);
            existingMeeting.setMembers(members);
        }

        System.out.print("Booking Information ID (Current: " + existingMeeting.getBookingInformation() + "): ");
        int bookingInformation = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        existingMeeting.setBookingInformation(this.bookingInformationService.getBookingInformationById(bookingInformation));

        meetingService.updateMeeting(existingMeeting);
        System.out.println("Meeting updated successfully.");
    }

    private void deleteMeeting() {
        System.out.println("\nDelete Meeting:");
        System.out.print("Enter Meeting ID to delete: ");
        int meetingId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Meeting existingMeeting = meetingService.getMeetingById(meetingId);
        if (existingMeeting == null) {
            System.out.println("Meeting not found.");
            return;
        }

        meetingService.deleteMeeting(meetingId);
        System.out.println("Meeting deleted successfully.");
    }

    private void manageMeetingRooms() {
        while (true) {
            System.out.println("\nManage Meeting Rooms:");
            System.out.println("1. View All Meeting Rooms");
            System.out.println("2. Add Meeting Room");
            System.out.println("3. Update Meeting Room");
            System.out.println("4. Delete Meeting Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllMeetingRooms();
                    break;
                case 2:
                    addMeetingRoom();
                    break;
                case 3:
                    updateMeetingRoom();
                    break;
                case 4:
                    deleteMeetingRoom();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = meetingRoomService.getAllMeetingRooms();
        System.out.println("\nList of Meeting Rooms:");
        for (MeetingRoom room : meetingRooms) {
            System.out.println("ID: " + room.getRoomID() +
                    ", Name: " + room.getRoomName() +
                    ", Capacity: " + room.getSeatingCapacity() +
                    ", Ratings: " + room.getRatings() +
                    ", Projector: " + room.isProjector() +
                    ", WiFi: " + room.isWifi() +
                    ", Conference Call: " + room.isConferenceCall() +
                    ", Whiteboard: " + room.isWhiteboard() +
                    ", Water Dispenser: " + room.isWaterDispenser() +
                    ", TV: " + room.isTV() +
                    ", Coffee Machine: " + room.isCoffeeMachine() +
                    ", Per Hour Cost: " + room.getPerHourCost()
            );
        }
    }

    private void addMeetingRoom() {
        System.out.println("\nAdd Meeting Room:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Ratings: ");
        double ratings = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Projector (true/false): ");
        boolean projector = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("WiFi (true/false): ");
        boolean wifi = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Conference Call (true/false): ");
        boolean conferenceCall = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Whiteboard (true/false): ");
        boolean whiteboard = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Water Dispenser (true/false): ");
        boolean waterDispenser = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("TV (true/false): ");
        boolean tv = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Coffee Machine (true/false): ");
        boolean coffeeMachine = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline
        System.out.print("Per Hour Cost: ");
        double perHourCost = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRoomName(name);
        meetingRoom.setSeatingCapacity(capacity);
        meetingRoom.setRatings(ratings);
        meetingRoom.setProjector(projector);
        meetingRoom.setWifi(wifi);
        meetingRoom.setConferenceCall(conferenceCall);
        meetingRoom.setWhiteboard(whiteboard);
        meetingRoom.setWaterDispenser(waterDispenser);
        meetingRoom.setTV(tv);
        meetingRoom.setCoffeeMachine(coffeeMachine);
        meetingRoom.getPerHourCost();
        meetingRoomService.addMeetingRoom(meetingRoom);
        System.out.println("Meeting Room added successfully.");
    }


    private void updateMeetingRoom() {
        System.out.print("\nEnter the ID of the Meeting Room to update: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        MeetingRoom existingRoom = meetingRoomService.getMeetingRoomById(roomId);

        if (existingRoom == null) {
            System.out.println("Meeting Room not found.");
            return;
        }

        System.out.println("\nUpdate Meeting Room (Leave input empty to keep existing values):");
        System.out.print("Name [" + existingRoom.getRoomName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingRoom.setRoomName(name);
        }
        System.out.print("Capacity [" + existingRoom.getSeatingCapacity() + "]: ");
        String capacityInput = scanner.nextLine();
        if (!capacityInput.isEmpty()) {
            int capacity = Integer.parseInt(capacityInput);
            existingRoom.setSeatingCapacity(capacity);
        }
        System.out.print("Ratings [" + existingRoom.getRatings() + "]: ");
        String ratingsInput = scanner.nextLine();
        if (!ratingsInput.isEmpty()) {
            double ratings = Double.parseDouble(ratingsInput);
            existingRoom.setRatings(ratings);
        }
        System.out.print("Projector (true/false) [" + existingRoom.isProjector() + "]: ");
        String projectorInput = scanner.nextLine();
        if (!projectorInput.isEmpty()) {
            boolean projector = Boolean.parseBoolean(projectorInput);
            existingRoom.setProjector(projector);
        }
        System.out.print("WiFi (true/false) [" + existingRoom.isWifi() + "]: ");
        String wifiInput = scanner.nextLine();
        if (!wifiInput.isEmpty()) {
            boolean wifi = Boolean.parseBoolean(wifiInput);
            existingRoom.setWifi(wifi);
        }
        System.out.print("Conference Call (true/false) [" + existingRoom.isConferenceCall() + "]: ");
        String conferenceCallInput = scanner.nextLine();
        if (!conferenceCallInput.isEmpty()) {
            boolean conferenceCall = Boolean.parseBoolean(conferenceCallInput);
            existingRoom.setConferenceCall(conferenceCall);
        }
        System.out.print("Whiteboard (true/false) [" + existingRoom.isWhiteboard() + "]: ");
        String whiteboardInput = scanner.nextLine();
        if (!whiteboardInput.isEmpty()) {
            boolean whiteboard = Boolean.parseBoolean(whiteboardInput);
            existingRoom.setWhiteboard(whiteboard);
        }
        System.out.print("Water Dispenser (true/false) [" + existingRoom.isWaterDispenser() + "]: ");
        String waterDispenserInput = scanner.nextLine();
        if (!waterDispenserInput.isEmpty()) {
            boolean waterDispenser = Boolean.parseBoolean(waterDispenserInput);
            existingRoom.setWaterDispenser(waterDispenser);
        }
        System.out.print("TV (true/false) [" + existingRoom.isTV() + "]: ");
        String tvInput = scanner.nextLine();
        if (!tvInput.isEmpty()) {
            boolean tv = Boolean.parseBoolean(tvInput);
            existingRoom.setTV(tv);
        }
        System.out.print("Coffee Machine (true/false) [" + existingRoom.isCoffeeMachine() + "]: ");
        String coffeeMachineInput = scanner.nextLine();
        if (!coffeeMachineInput.isEmpty()) {
            boolean coffeeMachine = Boolean.parseBoolean(coffeeMachineInput);
            existingRoom.setCoffeeMachine(coffeeMachine);
        }
        System.out.print("Per Hour Cost [" + existingRoom.getPerHourCost() + "]: ");
        String perHourCostInput = scanner.nextLine();
        if (!perHourCostInput.isEmpty()) {
            double perHourCost = Double.parseDouble(perHourCostInput);
            existingRoom.setPerHourCost((int) perHourCost);
        }

        meetingRoomService.updateMeetingRoom(existingRoom);
        System.out.println("Meeting Room updated successfully.");
    }


    private void deleteMeetingRoom() {
        System.out.println("\nDelete Meeting Room:");
        System.out.print("Enter Meeting Room ID to delete: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        MeetingRoom existingMeetingRoom = meetingRoomService.getMeetingRoomById(roomId);
        if (existingMeetingRoom == null) {
            System.out.println("Meeting Room not found.");
            return;
        }

        meetingRoomService.deleteMeetingRoom(roomId);
        System.out.println("Meeting Room deleted successfully.");
    }

    private void manageBookingInformation() {
        while (true) {
            System.out.println("\nManage Booking Information:");
            System.out.println("1. View All Booking Information");
            System.out.println("2. Add Booking Information");
            System.out.println("3. Update Booking Information");
            System.out.println("4. Delete Booking Information");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllBookingInformation();
                    break;
                case 2:
                    addBookingInformation();
                    break;
                case 3:
                    updateBookingInformation();
                    break;
                case 4:
                    deleteBookingInformation();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllBookingInformation() {
        List<BookingInformation> bookingInformation = bookingInformationService.getAllBookings();
        System.out.println("\nList of Booking Information:");
        for (BookingInformation booking : bookingInformation) {
            System.out.println("ID: " + booking.getBookingID() + ", RoomID: " + booking.getRoomID() +
                    ", Booking Date: " + booking.getBookingDate() + ", Start Time: " + booking.getStartTime() +
                    ", End Time: " + booking.getEndTime() + ", Booked By: " + booking.getBookedBy());
        }
    }

    private void addBookingInformation() {
        System.out.println("\nAdd Booking Information:");
        System.out.print("Room ID: ");
        int roomID = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Booking Date (yyyy-MM-dd): ");
        String bookingDate = scanner.nextLine();
        System.out.print("Start Time (HH:mm:ss): ");
        String startTime = scanner.nextLine();
        System.out.print("End Time (HH:mm:ss): ");
        String endTime = scanner.nextLine();
        System.out.print("Booked By (User ID): ");
        int bookedBy = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        BookingInformation booking = new BookingInformation();
        booking.setRoomID(roomID);
        booking.setBookingDate(bookingDate);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setBookedBy(bookedBy);

        bookingInformationService.addBookingInformation(booking);
        System.out.println("Booking Information added successfully.");
    }

    private void updateBookingInformation() {
        System.out.println("\nUpdate Booking Information:");
        System.out.print("Enter Booking Information ID to update: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        BookingInformation existingBooking = bookingInformationService.getBookingInformationById(bookingId);
        if (existingBooking == null) {
            System.out.println("Booking Information not found.");
            return;
        }

        System.out.print("Room ID (Current: " + existingBooking.getRoomID() + "): ");
        int roomID = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        existingBooking.setRoomID(roomID);

        System.out.print("Booking Date (yyyy-MM-dd) (Current: " + existingBooking.getBookingDate() + "): ");
        String bookingDate = scanner.nextLine();
        if (!bookingDate.isEmpty()) {
            existingBooking.setBookingDate(bookingDate);
        }

        System.out.print("Start Time (HH:mm:ss) (Current: " + existingBooking.getStartTime() + "): ");
        String startTime = scanner.nextLine();
        if (!startTime.isEmpty()) {
            existingBooking.setStartTime(startTime);
        }

        System.out.print("End Time (HH:mm:ss) (Current: " + existingBooking.getEndTime() + "): ");
        String endTime = scanner.nextLine();
        if (!endTime.isEmpty()) {
            existingBooking.setEndTime(endTime);
        }

        System.out.print("Booked By (User ID) (Current: " + existingBooking.getBookedBy() + "): ");
        int bookedBy = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        existingBooking.setBookedBy(bookedBy);

        bookingInformationService.updateBookingInformation(existingBooking);
        System.out.println("Booking Information updated successfully.");
    }

    private void deleteBookingInformation() {
        System.out.println("\nDelete Booking Information:");
        System.out.print("Enter Booking Information ID to delete: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        BookingInformation existingBooking = bookingInformationService.getBookingInformationById(bookingId);
        if (existingBooking == null) {
            System.out.println("Booking Information not found.");
            return;
        }

        bookingInformationService.deleteBookingInformation(bookingId);
        System.out.println("Booking Information deleted successfully.");
    }

    private List<Integer> parseUserIds(String input) {
        String[] parts = input.split(",");
        List<Integer> userIds = new ArrayList<>();
        for (String part : parts) {
            userIds.add(Integer.parseInt(part.trim()));
        }
        return userIds;
    }

    public static void main(String[] args) {
        // Initialize your services and pass them to the ConsoleUI constructor
        UserService userService = new UserServiceImpl(); 
        MeetingService meetingService = new MeetingServiceImpl();  
        MeetingRoomService meetingRoomService = new MeetingRoomServiceImpl();  
        BookingInformationService bookingInformationService = new BookingInformationServiceImpl();  
        ConsoleUI consoleUI = new ConsoleUI(userService, meetingService, meetingRoomService, bookingInformationService);
        if (LocalDateTime.now().getDayOfWeek() == DayOfWeek.MONDAY && LocalDateTime.now().toLocalTime().equals(LocalTime.of(6, 0))) {
			 userService.resetCredits();
		 }
        consoleUI.start();
    }
}
