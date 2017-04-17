package com.joshua.onlinebank.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.domain.Status;

public interface UserService {

	/**
	 * get account by username
	 * @param username
	 * @return
	 */
	abstract Account getAccount(String username);

	/**
	 * modify account information
	 * @param account
	 * @return
	 */
	boolean modifyAccount(Account account);

	/**
	 * modify person's info
	 * @param personinfo
	 * @return
	 */
	boolean modifyPersoninfo(Personinfo personinfo);

	/**
	 * 
	 * @param account
	 */
	void reflush(Account account);

	@Transactional(readOnly = true)
	abstract Account getAccount(Integer accountid);

	/**
	 * search for personinfo by account status
	 * @param status
	 * @return
	 */
	abstract List searchPersoninfo(Status status);

	/**
	 * lock account by accountid
	 * @param accountid
	 */
	abstract void lock(Integer accountid);

	/**
	 * unlock account by accountid
	 * @param accountid
	 */
	abstract void unlock(Integer accountid);

	/**
	 * search personinfo by personinfo
	 * @param personinfo
	 * @return
	 */
	abstract List searchPersoninfo(Personinfo personinfo);

	/**
	 * add an account to table account
	 * @param account
	 * @param cardId 
	 */
	abstract void addAccount(Account account, String cardId);

	/**
	 * add personinfo
	 * @param personinfo
	 * @param account
	 */
	abstract void addPeroninfo(Personinfo personinfo, Account account);

	/**
	 * delete account
	 * @param account
	 */
	abstract void del(Account account);

}
