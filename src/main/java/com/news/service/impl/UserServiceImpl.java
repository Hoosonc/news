package com.news.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.news.dao.UserDAO;
import com.news.pojo.Comment;
import com.news.pojo.Pager;
import com.news.pojo.Users;
import com.news.service.UserService;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;
	
	@Override
	public int addUser(Users user) {
		return userDAO.save(user);
	}

	@Override
	public Users login(String loginName, String loginPwd) {
		return userDAO.selectByLoginNameAndPwd(loginName, loginPwd);
	}

	@Override
	public List<Users> getAllUsers() {
		return userDAO.selectAll();
	}

	@Override
	public List<Users> findUsers(Users user, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDAO.count(params);
		pager.setRowCount(recordCount);		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pager", pager);
	    }		
		List<Users> users = userDAO.selectByPage(params);			
		return users;
	}

	@Override
	public Integer count(Map<String, Object> params) {		
		return userDAO.count(params);
	}

	@Override
	public void modifyStatus(String ids, int flag) {
		userDAO.updateState(ids, flag);
	}
	
}
