package com.news.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.news.dao.TopicDAO;
import com.news.pojo.Newsinfo;
import com.news.pojo.Pager;
import com.news.pojo.Topic;
import com.news.service.TopicService;

@Service("topicService")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDAO topicDAO;
	
	@Transactional(readOnly=true)
	@Override
	public List<Topic> selectAllTopic() {
		return topicDAO.selectAllTopic();
	}

	@Override
	public List<Topic> findTopic(Topic topic, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("topic", topic);
		int recordCount = topicDAO.count(params);
		pager.setRowCount(recordCount);		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pager", pager);
	    }		
		List<Topic> topics = topicDAO.selectByPage(params);			
		return topics;
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return topicDAO.count(params);
	}

	@Override
	public int addTopic(Topic topic) {
		return topicDAO.save(topic);
	}

	@Override
	public void modify(Topic topic) {
		topicDAO.edit(topic);
	}

	@Override
	public List<Topic> getAllTopic() {
		return topicDAO.selectAllTopic();
	}

}
