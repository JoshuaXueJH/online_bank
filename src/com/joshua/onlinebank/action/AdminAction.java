package com.joshua.onlinebank.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ibm.xtq.xml.types.UserDefinedAtomicType;
import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Admin;
import com.joshua.onlinebank.domain.Password;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.domain.Status;
import com.joshua.onlinebank.service.AdminService;
import com.joshua.onlinebank.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware, RequestAware {
	private AdminService adminService;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	Map<String, Object> session;
	Map<String, Object> request;

	private Admin admin;
	private Status status;
	private Account account;
	private Password pwd;
	private Personinfo personinfo;

	public Personinfo getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
	}

	public Password getPwd() {
		return pwd;
	}

	public void setPwd(Password pwd) {
		this.pwd = pwd;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void validateLogin() {
		Admin a = adminService.getAdmin(admin.getUsername());
		if (a == null) {
			this.addFieldError("username", "用户名不存在");
		}
		if (!a.getPassword().equals(admin.getPassword())) {
			this.addFieldError("password", "密码不正确");
		}
		admin = a;
	}

	public String login() {
		if (admin != null) {
			session.put("admin", admin);
			return "success";
		}
		return "login";
	}

	public String listUsers() {
		List users = userService.searchPersoninfo(status);
		request.put("users", users);
		return "users";
	}

	public String lock() {
		userService.lock(account.getAccountid());
		return "list";
	}

	public String unlock() {
		userService.unlock(account.getAccountid());
		return "list";
	}

	public void validateChangepwd() {
		admin = (Admin) session.get("admin");
		if (!pwd.getOldpwd().equals(admin.getPassword())) {
			this.addFieldError("pwd.oldpwd", "密码不正确");
		}
		if (!pwd.getNewpwd().equals(pwd.getConfirmpwd())) {
			this.addFieldError("pwd.confirmpwd", "两次密码不一致");
		}
	}

	public String changepwd() {
		admin.setPassword(pwd.getNewpwd());
		if (adminService.modifyAdminAccount(admin)) {
			session.put("admin", admin);
			request.put("message", "密码修改成功");
			return "message";
		}
		request.put("message", "密码修改失败！");
		return "message";
	}

	public String logout() {
		session.remove("admin");
		return "success";
	}

	public String search() {
		List users = userService.searchPersoninfo(personinfo);
		request.put("users", users);
		return "users";
	}

	public void validateOpenaccount() {
		if (userService.getAccount(account.getUsername()) != null) {
			this.addFieldError("username", "用户名已存在");
		}
		if (userService.searchPersoninfo(personinfo).size() > 0) {
			this.addFieldError("personinfo.cardid", "此身份证已被使用，一个身份证只能拥有一个账户");
		}
	}

	public String openaccount() {
		//adminService.openaccount(account, personinfo);
		userService.addAccount(account, personinfo.getCardid());
		account = userService.getAccount(account.getUsername());
		userService.addPeroninfo(personinfo, account);
		request.put("message", "开户成功");
		return "message";
	}

	public String del() {
		userService.del(account);
		return "list";
	}
}
