package com.news.service;

import java.util.List;
import java.util.Map;

import com.news.pojo.Pager;
import com.news.pojo.Topic;

public interface TopicService {
	List<Topic> selectAllTopic();
	List<Topic> findTopic(Topic topic,Pager pager);
	Integer count(Map<String, Object> params);
	public int addTopic(Topic topic);
	void modify(Topic topic);
	public List<Topic> getAllTopic();
}
