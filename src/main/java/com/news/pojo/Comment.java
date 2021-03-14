package com.news.pojo;

import java.beans.Transient;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {
	private int id;
	private Newsinfo newinfo;
	private String content;
	private Users user;
	private Date createDate;
	private int status;	
	private Date commentTimeFrom;
	private Date commentTimeTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Newsinfo getNewinfo() {
		return newinfo;
	}

	public void setNewinfo(Newsinfo newinfo) {
		this.newinfo = newinfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCommentTimeFrom() {
		return commentTimeFrom;
	}

	public void setCommentTimeFrom(Date commentTimeFrom) {
		this.commentTimeFrom = commentTimeFrom;
	}

	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCommentTimeTo() {
		return commentTimeTo;
	}

	public void setCommentTimeTo(Date commentTimeTo) {
		this.commentTimeTo = commentTimeTo;
	}	
	

}
