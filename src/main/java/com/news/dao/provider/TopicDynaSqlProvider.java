package com.news.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.news.pojo.Newsinfo;
import com.news.pojo.Topic;

public class TopicDynaSqlProvider {

	// 根据条件动态查询主题总记录数
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("topic");
				if (params.get("topic") != null) {
					Topic topic = (Topic) params.get("topic");
					if (topic.getName() != null && !"".equals(topic.getName())) {
						WHERE("  Name LIKE CONCAT ('%',#{topic.name},'%') ");
					}
				}
			}
		}.toString();
	}

	// 分页动态查询
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("topic");
				if (params.get("topic") != null) {
					Topic topic = (Topic) params.get("topic");
					if (topic.getName() != null && !"".equals(topic.getName())) {
						WHERE("  Name LIKE CONCAT ('%',#{topic.name},'%') ");
					}
				}
			}
		}.toString();
		if (params.get("pager") != null) {
			sql += " limit #{pager.firstLimitParam} , #{pager.perPageRows}  ";
		}
		return sql;
	}
}
