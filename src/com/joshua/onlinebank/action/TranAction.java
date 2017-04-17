package com.joshua.onlinebank.action;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Pager;
import com.joshua.onlinebank.domain.Transactionlog;
import com.joshua.onlinebank.service.TranService;
import com.joshua.onlinebank.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class TranAction extends ActionSupport implements SessionAware, RequestAware {
	private TranService tranService;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTranService(TranService tranService) {
		this.tranService = tranService;
	}

	Map<String, Object> request;
	Map<String, Object> session;
	private Transactionlog log;
	private Account account;
	private Pager pager;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Transactionlog getLog() {
		return log;
	}

	public void setLog(Transactionlog log) {
		this.log = log;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		account = (Account) session.get("user");
	}

	/*	public void validateDeposit() {
			if (log.getDatetime().equals("") || log.getDatetime() == null) {
				this.addFieldError("log.datetime", "存款时间不能为空");
			}
			if (Pattern.compile("").matcher(log.getTrMoney())) {
				this.addFieldError("", "");
			}
		}*/

	private boolean isEnable() {
		userService.reflush(account);
		if (account.getStatus().getName().equals("inactive")) {
			request.put("message", "对不起，该账户被冻结，无法进行相关操作");
			return false;
		}
		return true;
	}

	private String isSuccess(boolean flag) {
		if (flag) {
			request.put("message", "操作成功");
			return "message";
		}
		request.put("message", "操作失败");
		return "message";
	}

	public String deposit() {
		if (isEnable()) {
			log.setAccountByFromid(account);
			session.put("user", account);
			return isSuccess(tranService.deposit(log));
		}
		return "message";
	}

	public String withdrawal() {
		if (isEnable()) {
			log.setAccountByFromid(account);
			session.put("user", account);
			return isSuccess(tranService.withdrawal(log));
		}
		return "message";
	}

	public void validateTransfer() {
		if (log.getToid().intValue() == account.getAccountid().intValue()) {
			this.addFieldError("log.toid", "您不能转账给自己");
		}
		if (userService.getAccount(log.getToid()) == null) {
			this.addFieldError("log.toid", "该账户不存在");
		}
		if (account.getBalance() < log.getTrMoney()) {
			this.addFieldError("log.trMoney", "您的账户余额不足");
		}
	}

	public String transfer() {
		if (isEnable()) {
			log.setAccountByFromid(account);
			return isSuccess(tranService.transfer(log));
		}
		return "message";
	}

	public String list() {
		int curPage = pager.getCurPage();
		List<Transactionlog> logs = tranService.getLogs(account, curPage);
		pager = tranService.getPagerOfLogs(account);
		pager.setCurPage(curPage);
		request.put("logs", logs);
		return "success";
	}
}
