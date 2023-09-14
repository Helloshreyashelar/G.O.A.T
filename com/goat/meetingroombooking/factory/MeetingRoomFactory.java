package com.goat.meetingroombooking.factory;

import com.goat.meetingroombooking.dao.MeetingRoomDao;
import com.goat.meetingroombooking.dao.MeetingRoomDaoImpl;

public class MeetingRoomFactory {
	static MeetingRoomDao dao;
	public static MeetingRoomDao getMeetingRoomDao() {
		if(dao == null) {
			dao = new MeetingRoomDaoImpl();
		}
		return dao;
	}
}
