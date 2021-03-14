package com.news.service;

import com.news.pojo.Admin;

public interface AdminService {
	public Admin login(String loginName,String loginPwd); 
	public Admin getAdminAndFunctions(Integer id);
}
