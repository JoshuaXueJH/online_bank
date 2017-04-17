package com.joshua.onlinebank.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.joshua.onlinebank.dao.UserDao;
import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.domain.Status;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Account getAccount(String username) {
		return userDao.getAccount(username);
	}

	@Override
	public boolean modifyAccount(Account account) {
		return userDao.modifyAccount(account);
	}

	@Override
	public boolean modifyPersoninfo(Personinfo personinfo) {
		return userDao.modifyPersoninfo(personinfo);
	}

	@Override
	public void reflush(Account account) {
		userDao.reflush(account);
	}

	@Override
	public Account getAccount(Integer accountid) {
		return userDao.getAccount(accountid);
	}

	@Override
	public List searchPersoninfo(Status status) {
		List personinfos = new ArrayList();
		if (status.getId() != 0) {
			status = userDao.getStatus(status.getId());
			personinfos = userDao.searchPersoninfo(status);
		} else if (status.getId() == 0) {
			personinfos = userDao.getAllPersonInfos();
		}
		return personinfos;
	}

	@Override
	public void lock(Integer accountid) {
		Account account = userDao.getAccount(accountid);
		Status status = userDao.getStatus(2);
		account.setStatus(status);
		userDao.modifyAccount(account);
	}

	@Override
	public void unlock(Integer accountid) {
		Account account = userDao.getAccount(accountid);
		Status status = userDao.getStatus(1);
		account.setStatus(status);
		userDao.modifyAccount(account);
	}

	@Override
	public List searchPersoninfo(Personinfo personinfo) {
		return userDao.searchPersoninfo(personinfo);
	}

	@Override
	public void addAccount(Account account, String cardId) {
		Status status = userDao.getStatus(1);
		String accountid = cardId.substring(0, 9);
		account.setStatus(status);
		account.setAccountid(Integer.parseInt(accountid));
		userDao.addAccount(account);
	}

	@Override
	public void addPeroninfo(Personinfo personinfo, Account account) {
		personinfo.setAccount(account);
		userDao.addPersoninfo(personinfo);
	}

	@Override
	public void del(Account account) {
		userDao.del(userDao.getAccount(account.getAccountid()));
	}

}
