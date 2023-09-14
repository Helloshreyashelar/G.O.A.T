package com.goat.meetingroombooking.dao;

import com.goat.meetingroombooking.model.BookingInformation;

import java.util.List;

public interface BookingInformationDao {
    BookingInformation getBookingInformationById(int bookingId);
    List<BookingInformation> getAllBookings();
    void addBookingInformation(BookingInformation booking);
    void updateBookingInformation(BookingInformation booking);
    void deleteBookingInformation(int bookingId);
}
