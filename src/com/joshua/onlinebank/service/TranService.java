package com.joshua.onlinebank.service;

import java.util.List;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Pager;
import com.joshua.onlinebank.domain.Transactionlog;

public interface TranService {

	/**
	 * deposit money to an account
	 * @param log
	 * @return
	 */
	boolean deposit(Transactionlog log);

	/**
	 * withdrawl money from an account
	 * @param log
	 * @return
	 */
	boolean withdrawal(Transactionlog log);

	/**
	 * transfer money from account A to account B
	 * @param log
	 * @return
	 */
	boolean transfer(Transactionlog log);

	/**
	 * get transaction logs
	 * @param account
	 * @param curPage
	 * @return
	 */
	List getLogs(Account account, int page);

	/**
	 * set perPageRows and rowCount
	 * @param account
	 * @return
	 */
	Pager getPagerOfLogs(Account account);

}
