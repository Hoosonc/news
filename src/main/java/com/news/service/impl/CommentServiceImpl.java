package com.news.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.news.dao.CommentDAO;
import com.news.dao.UserDAO;
import com.news.pojo.Comment;
import com.news.pojo.Newsinfo;
import com.news.pojo.Pager;
import com.news.service.CommentService;

@Service("commentService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAO;

	@Override
	public void addComment(Comment comment) {
		commentDAO.save(comment);
	}

	@Override
	public List<Comment> findComment(Comment comment, Pager pager) {
		/** 当前需要分页的总数据条数 */
		Map<String, Object> params = new HashMap<>();
		params.put("comment", comment);
		int recordCount = commentDAO.count(params);
		pager.setPerPageRows(2);
		pager.setRowCount(recordCount);
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pager", pager);
		}
		List<Comment> comments = commentDAO.selectByPage(params);
		return comments;
	}

	@Override
	public void removeCommentByNid(int nid) {
		commentDAO.deleteByNid(nid);
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return commentDAO.count(params);
	}

	@Override
	public List<Comment> findCommentForBackstage(Comment comment, Pager pager) {
		Map<String,Object> params = new HashMap<>();
		params.put("comment", comment);
		int recordCount = commentDAO.count(params);
		pager.setRowCount(recordCount);		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pager", pager);
	    }		
		List<Comment> commens = commentDAO.selectByPage(params);			
		return commens;
	}

	@Override
	public void modifyStatus(String ids) {
		commentDAO.updateState(ids);	
	}

	@Override
	public void deleteCommentByIds(String ids) {
		commentDAO.deleteByIds(ids);		
	}

}
