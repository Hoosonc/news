package com.news.dao.provider;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.news.pojo.Newsinfo;

public class NewsinfoDynaSqlProvider {
	// 根据条件动态查询新闻总记录数
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("newsinfo");
				if (params.get("newsinfo") != null) {
					Newsinfo newsinfo = (Newsinfo) params.get("newsinfo");
					if (newsinfo.getTopic() != null
							&& newsinfo.getTopic().getId() != 0) {
						WHERE(" Tid = #{newsinfo.topic.id} ");
					}
					if (newsinfo.getTitle() != null
							&& !newsinfo.getTitle().equals("")) {
						WHERE("  Title LIKE CONCAT ('%',#{newsinfo.title},'%') ");
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
				FROM("newsinfo");
				if (params.get("newsinfo") != null) {
					Newsinfo newsinfo = (Newsinfo) params.get("newsinfo");
					if (newsinfo.getTopic() != null
							&& newsinfo.getTopic().getId() != 0) {
						WHERE(" Tid = #{newsinfo.topic.id} ");
					}
					if (newsinfo.getTitle() != null
							&& !newsinfo.getTitle().equals("")) {
						WHERE("  Title LIKE CONCAT ('%',#{newsinfo.title},'%') ");
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
