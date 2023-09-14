package com.goat.meetingroombooking.factory;

import com.goat.meetingroombooking.dao.BookingInformationDao;
import com.goat.meetingroombooking.dao.BookingInformationDaoImpl;

public class BookingInformationFactory {
	static BookingInformationDao dao;
	public static BookingInformationDao getBookingInformationDao() {
		if(dao == null) {
			dao = new BookingInformationDaoImpl();
		}
		return dao;
	}
}
