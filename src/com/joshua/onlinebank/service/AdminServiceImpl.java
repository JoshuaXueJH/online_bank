package com.joshua.onlinebank.service;

import com.joshua.onlinebank.dao.AdminDao;
import com.joshua.onlinebank.dao.UserDao;
import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Admin;
import com.joshua.onlinebank.domain.Personinfo;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getAdmin(String username) {
		return adminDao.getAdmin(username);
	}

	@Override
	public boolean modifyAdminAccount(Admin admin) {
		return adminDao.modifyAdminAccount(admin);
	}

}
