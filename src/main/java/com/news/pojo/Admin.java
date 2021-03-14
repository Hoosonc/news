package com.news.pojo;

import java.util.List;

public class Admin {
	private int id;
	private String loginName;
	private String loginPwd;

	// 管理员和功能是多对多关系
	private List<Functions> fs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public List<Functions> getFs() {
		return fs;
	}

	public void setFs(List<Functions> fs) {
		this.fs = fs;
	}

}
