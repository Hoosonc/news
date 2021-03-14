package com.news.service;

import java.util.List;
import java.util.Map;

import com.news.pojo.Comment;
import com.news.pojo.Pager;

public interface CommentService {
	public void addComment(Comment comment);

	List<Comment> findComment(Comment comment, Pager pager);

	Integer count(Map<String, Object> params);

	void removeCommentByNid(int nid);

	public List<Comment> findCommentForBackstage(Comment comment, Pager pager);
	
	void modifyStatus(String ids);
	
	void deleteCommentByIds(String ids);
}
