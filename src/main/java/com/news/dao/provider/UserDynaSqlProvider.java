package com.news.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.news.pojo.Comment;
import com.news.pojo.Users;

public class UserDynaSqlProvider {
	// 分页动态查询
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("users");
				if (params.get("user") != null) {
					Users user = (Users) params.get("user");
					if (user.getLoginName() != null
							&& !"".equals(user.getLoginName())) {
						WHERE(" LoginName LIKE CONCAT ('%',#{user.loginName},'%') ");
					}
					if (user.getStatus() > 0) {
						WHERE(" Status = #{user.status} ");
					}
				}
			}
		}.toString();
		if (params.get("pager") != null) {
			sql += " limit #{pager.firstLimitParam} , #{pager.perPageRows}  ";
		}
		return sql;
	}

	// 动态查询总记录数
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM("users");
				if (params.get("user") != null) {
					Users user = (Users) params.get("user");
					if (user.getLoginName() != null
							&& !"".equals(user.getLoginName())) {
						WHERE(" LoginName LIKE CONCAT ('%',#{user.loginName},'%') ");
					}
					if (user.getStatus() > 0) {
						WHERE(" Status = #{user.status} ");
					}
				}
			}
		}.toString();
	}
}
