package com.joshua.onlinebank.dao;

import java.util.List;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Transactionlog;
import com.joshua.onlinebank.domain.Transactiontype;

public interface TranDao {

	/**
	 * get Transactiontype by transactiontype_name and return it
	 * @param string
	 * @return
	 */
	Transactiontype getTranType(String name);

	/**
	 * add transaction log to table Transactionlog
	 * @param log
	 * @return
	 */
	boolean addLog(Transactionlog log);

	/**
	 * get transaction logs from database by account
	 * @param account
	 * @param page
	 * @return
	 */
	List getLogs(Account account, int page);

	/**
	 * get the count of accounts related to the account
	 * @param account
	 * @return
	 */
	int getCountOfLogs(Account account);

}
