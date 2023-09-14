package com.goat.meetingroombooking.factory;

import com.goat.meetingroombooking.dao.UserDao;
import com.goat.meetingroombooking.dao.UserDaoImpl;

public class UserFactory {
	static UserDao dao;
	public static UserDao getUserDao() {
		if(dao == null) {
			dao = new UserDaoImpl();
		}
		return dao;
	}
}
