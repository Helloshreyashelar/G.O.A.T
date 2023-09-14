package com.goat.meetingroombooking.service;

import com.goat.meetingroombooking.dao.BookingInformationDao;
import com.goat.meetingroombooking.dao.UserDao;
import com.goat.meetingroombooking.exception.ServiceException;
import com.goat.meetingroombooking.factory.BookingInformationFactory;
import com.goat.meetingroombooking.factory.UserFactory;
import com.goat.meetingroombooking.model.BookingInformation;
import com.goat.meetingroombooking.model.User;

import java.util.List;

public class BookingInformationServiceImpl implements BookingInformationService {

    private final BookingInformationDao bookingInformationDao;
    private final UserDao userDao;

    public BookingInformationServiceImpl() {
        this.bookingInformationDao = BookingInformationFactory.getBookingInformationDao();
        this.userDao = UserFactory.getUserDao();
    }

    @Override
    public BookingInformation getBookingInformationById(int bookingId) {
        return bookingInformationDao.getBookingInformationById(bookingId);
    }

    @Override
    public List<BookingInformation> getAllBookings() {
        return bookingInformationDao.getAllBookings();
    }

    @Override
    public void addBookingInformation(BookingInformation booking) {
    	User bookedBy = userDao.getUserById(booking.getBookedBy());
    	if(bookedBy.getRole() == "Manager") {
    		boolean hasCredits = bookedBy.updateCredits(booking.getRoomID());
    		if(hasCredits) {
    			bookingInformationDao.addBookingInformation(booking);
    		}else {
    			throw new ServiceException("Manager does not have enough credits.");
    		}
    	}else {
    		throw new ServiceException("Unauthorised to add booking information.");
    	}
    }

    @Override
    public void updateBookingInformation(BookingInformation booking) {
        bookingInformationDao.updateBookingInformation(booking);
    }

    @Override
    public void deleteBookingInformation(int bookingId) {
        bookingInformationDao.deleteBookingInformation(bookingId);
    }
}
