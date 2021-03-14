package com.news.pojo;

import java.util.HashSet;
import java.util.Set;

public class Topic {
	private int id;
	private String name;
	private Set newsinfos = new HashSet();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getNewsinfos() {
		return newsinfos;
	}

	public void setNewsinfos(Set newsinfos) {
		this.newsinfos = newsinfos;
	}

}
