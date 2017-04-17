package com.joshua.onlinebank.dao;

import java.util.List;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.domain.Status;

public interface UserDao {

	/**
	 * get user account information from database by username
	 * @param username
	 * @return
	 */
	Account getAccount(String username);

	/**
	 * modify account information in dabatase
	 * @param account
	 * @return
	 */
	boolean modifyAccount(Account account);

	/**
	 * modify person's info in database
	 * @param personinfo
	 * @return
	 */
	boolean modifyPersoninfo(Personinfo personinfo);

	/**
	 * 
	 * @param account
	 */
	void reflush(Account account);

	/**
	 * get user account information from database by acountid
	 * @param accountid
	 * @return
	 */
	Account getAccount(Integer accountid);

	/**
	 * get status by status id
	 * @param id
	 * @return
	 */
	Status getStatus(Integer id);

	/**
	 * get personinfos by account status
	 * @param status
	 * @return 
	 */
	List searchPersoninfo(Status status);

	/**
	 * get all person's infos
	 * @return
	 */
	List getAllPersonInfos();

	/**
	 * get personinfro from table personinfo by personinfo bean
	 * @param personinfo
	 * @return
	 */
	List searchPersoninfo(Personinfo personinfo);

	/**
	 * add an account to table account
	 * @param account
	 */
	void addAccount(Account account);

	/**
	 * add peroninfo to table personinfo
	 * @param personinfo
	 */
	void addPersoninfo(Personinfo personinfo);

	/**
	 * delete account from table account
	 * @param account
	 */
	void del(Account account);

}
