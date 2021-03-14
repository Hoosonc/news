package com.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.news.dao.AdminDAO;
import com.news.pojo.Admin;
import com.news.service.AdminService;

@Service("adminService")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public Admin login(String loginName, String loginPwd) {
		return adminDAO.selectByLoginNameAndPwd(loginName, loginPwd);
	}

	@Override
	public Admin getAdminAndFunctions(Integer id) {		
		return adminDAO.selectById(id);
	}
	

}
