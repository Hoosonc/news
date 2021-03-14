package com.news.service;

import java.util.List;
import java.util.Map;

import com.news.pojo.Newsinfo;
import com.news.pojo.Pager;
import com.news.pojo.Users;

public interface NewsinfoService {
	
	/**
	 * 获得所有新闻
	 * @param newsinfo 查询条件
	 * @param pager 分页对象
	 * @return Newsinfo对象的List集合
	 * */
	List<Newsinfo> findNewsinfo(Newsinfo newsinfo,Pager pager);
	
	List<Newsinfo> selectTop5ByTid(int tid);
	
	Newsinfo selectById(int id);
	
	// 后台新闻列表
	List<Newsinfo> findNewsinfoForBackstage(Newsinfo newsinfo,Pager pager);
	
	Integer count(Map<String, Object> params);
	
	public void addNewsinfo(Newsinfo ni);
	
	void modify(Newsinfo ni);
	
	void removeNewsinfoById(int id);
}
