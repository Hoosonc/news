package com.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.news.pojo.Functions;

public interface FunctionDAO {
	@Select("select * from functions where id in (select fid from powers where aid=#{id} ) ")
	public List<Functions> selectByAdminId(Integer aid);
}
