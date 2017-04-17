package com.joshua.onlinebank.action;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Password;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware, RequestAware {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	Map<String, Object> request;
	Map<String, Object> session;

	private Account account;
	private Personinfo personinfo;
	private Password pwd;

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

	public Personinfo getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(Personinfo personinfo) {
		this.personinfo = personinfo;
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
		Account a = userService.getAccount(account.getUsername());
		if (a == null) {
			this.addFieldError("username", "用户名不存在");
		} else if (!account.getPassword().equals(a.getPassword())) {
			this.addFieldError("password", "密码不正确");
		}
		account = a;
	}

	public String login() {
		personinfo = (Personinfo) account.getPersoninfos().iterator().next();
		session.put("user", account);
		session.put("personinfo", personinfo);
		return "success";
	}

	public void validateChangepwd() {
		account = (Account) session.get("user");
		if (!account.getPassword().equals(pwd.getOldpwd())) {
			this.addFieldError("pwd.oldpwd", "密码不正确");
		} else if (!pwd.getNewpwd().equals(pwd.getConfirmpwd())) {
			this.addFieldError("pwd.confirmpwd", "两次密码不一致");
		}
	}

	public String changepwd() {
		account.setPassword(pwd.getNewpwd());
		if (userService.modifyAccount(account)) {
			session.put("user", account);
			request.put("message", "密码修改成功！");
			return "message";
		}
		request.put("message", "密码修改失败！");
		return "message";
	}

	public void validateModifyinfo() {
		if ("".equals(personinfo.getTelephone().trim())) {
			personinfo.setTelephone("电话不详");
		}
		if (!(personinfo.getAge() >= 18 && personinfo.getAge() <= 100)) {
			this.addFieldError("personinfo.age", "年龄不正确");
		}
		if (!Pattern.compile("^\\d{17}(\\d|x)$").matcher(personinfo.getCardid().toString()).matches()) {
			addFieldError("personinfo.cardId", "身份证格式不正确");
		}
		if (!"电话不详".equals(personinfo.getTelephone().trim()) && !Pattern.compile("^(?:1[358]\\d{9}|\\d{3,4}-\\d{8,9})$")
				.matcher(personinfo.getTelephone()).matches()) {
			addFieldError("personinfo.telephone", "电话格式不正确");
		}
	}

	public String modifyinfo() {
		Personinfo per = (Personinfo) session.get("personinfo");
		per.setAddress(personinfo.getAddress());
		per.setAge(personinfo.getAge());
		per.setCardid(personinfo.getCardid());
		per.setRealname(personinfo.getRealname());
		per.setSex(personinfo.getSex());
		per.setTelephone(personinfo.getTelephone());
		/*		Account ac = (Account) session.get("user");
				personinfo.setAccount(ac);
				使用这种方法不诚实，因为此时personinfo的id是null，会报错*/
		if (userService.modifyPersoninfo(per)) {
			session.put("personinfo", per);
			request.put("message", "修改成功");
			return "message";
		}
		request.put("message", "修改失败");
		return "message";
	}

	public String logout() {
		session.remove("user");
		session.remove("personinfo");
		return "success";
	}
}
