package com.joshua.onlinebank.service;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Admin;
import com.joshua.onlinebank.domain.Personinfo;

public interface AdminService {

	/**
	 * get Admin by username
	 * @param username
	 * @return
	 */
	Admin getAdmin(String username);

	/**
	 * modify admin account
	 * @param admin
	 * @return
	 */
	boolean modifyAdminAccount(Admin admin);

}
