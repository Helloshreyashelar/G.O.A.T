package com.goat.meetingroombooking.factory;

import com.goat.meetingroombooking.dao.MeetingDao;
import com.goat.meetingroombooking.dao.MeetingDaoImpl;

public class MeetingFactory {
	static MeetingDao dao;
	public static MeetingDao getMeetingDao() {
		if(dao == null) {
			dao = new MeetingDaoImpl();
		}
		return dao;
	}
}
