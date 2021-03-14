package com.news.service;

import java.util.List;
import java.util.Map;

import com.news.pojo.Pager;
import com.news.pojo.Users;

public interface UserService {
	public int addUser(Users user);

	public Users login(String loginName, String loginPwd);

	public List<Users> getAllUsers();

	List<Users> findUsers(Users user, Pager pager);

	Integer count(Map<String, Object> params);

	void modifyStatus(String ids, int flag);
}
