package com.news.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.news.dao.NewsinfoDAO;
import com.news.pojo.Newsinfo;
import com.news.pojo.Pager;
import com.news.service.NewsinfoService;

@Service("newsinfoService")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class NewsinfoServiceImpl implements NewsinfoService {

	@Autowired
	NewsinfoDAO newsinfoDAO;
	
	@Override
	public List<Newsinfo> findNewsinfo(Newsinfo newsinfo, Pager pager) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("newsinfo", newsinfo);
		int recordCount = newsinfoDAO.count(params);
		pager.setPerPageRows(10);
		pager.setRowCount(recordCount);		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pager", pager);
	    }		
		List<Newsinfo> newsinfos = newsinfoDAO.selectByPage(params);			
		return newsinfos;
	}

	@Override
	public List<Newsinfo> selectTop5ByTid(int tid) {		
		return newsinfoDAO.selectTop5ByTid(tid);
	}

	@Override
	public Newsinfo selectById(int id) {
		return newsinfoDAO.selectById(id);
	}

	@Override
	public List<Newsinfo> findNewsinfoForBackstage(Newsinfo newsinfo,
			Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("newsinfo", newsinfo);
		int recordCount = newsinfoDAO.count(params);
		pager.setRowCount(recordCount);		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pager", pager);
	    }		
		List<Newsinfo> newsinfos = newsinfoDAO.selectByPage(params);			
		return newsinfos;
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return newsinfoDAO.count(params);
	}

	@Override
	public void addNewsinfo(Newsinfo ni) {
		newsinfoDAO.save(ni);		
	}

	@Override
	public void modify(Newsinfo ni) {
		newsinfoDAO.edit(ni);
	}

	@Override
	public void removeNewsinfoById(int id) {
		newsinfoDAO.deleteById(id);
	}
	

}
