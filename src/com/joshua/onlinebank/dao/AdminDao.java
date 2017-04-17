package com.joshua.onlinebank.dao;

import com.joshua.onlinebank.domain.Admin;

public interface AdminDao {

	/**
	 * get Admin by username from admin table
	 * @param username
	 * @return
	 */
	Admin getAdmin(String username);

	/**
	 * modify admin account in table admin
	 * @param admin
	 * @return
	 */
	boolean modifyAdminAccount(Admin admin);

}
