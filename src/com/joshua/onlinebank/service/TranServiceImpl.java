package com.joshua.onlinebank.service;

import java.util.List;

import com.joshua.onlinebank.dao.TranDao;
import com.joshua.onlinebank.dao.UserDao;
import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Pager;
import com.joshua.onlinebank.domain.Transactionlog;
import com.joshua.onlinebank.domain.Transactiontype;

public class TranServiceImpl implements TranService {
	private TranDao tranDao;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setTranDao(TranDao tranDao) {
		this.tranDao = tranDao;
	}

	@Override
	public boolean deposit(Transactionlog log) {
		Account self = log.getAccountByFromid();
		self.setBalance(self.getBalance() + log.getTrMoney());
		userDao.modifyAccount(self);

		Transactiontype type = tranDao.getTranType("deposit");
		log.setToid(log.getAccountByFromid().getAccountid());
		log.setTransactiontype(type);
		return tranDao.addLog(log);
	}

	@Override
	public boolean withdrawal(Transactionlog log) {
		Account self = log.getAccountByFromid();
		self.setBalance(self.getBalance() - log.getTrMoney());
		userDao.modifyAccount(self);

		Transactiontype type = tranDao.getTranType("withdrawal");
		log.setToid(log.getAccountByFromid().getAccountid());
		log.setTransactiontype(type);
		return tranDao.addLog(log);
	}

	@Override
	public boolean transfer(Transactionlog log) {
		Account self = log.getAccountByFromid();
		Account to = userDao.getAccount(log.getToid());
		if (to != null) {
			self.setBalance(log.getAccountByFromid().getBalance() - log.getTrMoney());
			to.setBalance(to.getBalance() + log.getTrMoney());
			userDao.modifyAccount(self);
			userDao.modifyAccount(to);

			Transactiontype type = tranDao.getTranType("transfer");
			log.setTransactiontype(type);
			return tranDao.addLog(log);
		}
		return false;
	}

	@Override
	public List getLogs(Account account, int page) {
		return tranDao.getLogs(account, page);
	}

	@Override
	public Pager getPagerOfLogs(Account account) {
		int count = tranDao.getCountOfLogs(account);
		Pager pager = new Pager();
		pager.setPerPageRows(10);
		pager.setRowCount(count);
		return pager;
	}

}
