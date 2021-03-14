package com.news.dao;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.news.pojo.Admin;

public interface AdminDAO {
	// 根据登录名和密码查询用户
	@Select("select * from admin where LoginName = #{loginName} and LoginPwd = #{loginPwd}")
	public Admin selectByLoginNameAndPwd(@Param("loginName") String loginName,
			@Param("loginPwd") String loginPwd);
	
	// 根据管理员id获取管理员对象及关联的功能集合
	@Select("select * from admin where Id=#{id}")
	@Results({
		@Result(id = true, column = "id", property = "id"),
		@Result(column = "LoginName", property = "loginName"),
		@Result(column = "LoginPwd", property = "loginPwd"),
		@Result(column = "id", property = "fs", many = @Many(select = "com.news.dao.FunctionDAO.selectByAdminId", fetchType = FetchType.EAGER)) })
	Admin selectById(Integer id);
}
