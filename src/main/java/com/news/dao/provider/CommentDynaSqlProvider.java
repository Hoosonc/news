package com.news.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.news.pojo.Comment;
import com.news.pojo.Users;

public class CommentDynaSqlProvider {
	// 分页动态查询
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("comment");
				if (params.get("comment") != null) {
					Comment comment = (Comment) params.get("comment");
					if (comment.getNewinfo() != null
							&& comment.getNewinfo().getId() > 0) {
						WHERE(" Nid = #{comment.newinfo.id} ");
					}
					if (comment.getStatus() > 0) {
						WHERE(" Status = #{comment.status} ");
					}
					if (comment.getUser() != null
							&& comment.getUser().getId() > 0) {
						WHERE(" Uid = #{comment.user.id} ");
					}
					if (comment.getCommentTimeFrom() != null
							&& !"".equals(comment.getCommentTimeFrom())) {
						WHERE(" CreateDate >= #{comment.commentTimeFrom} ");
					}
					if (comment.getCommentTimeTo() != null
							&& !"".equals(comment.getCommentTimeTo())) {
						WHERE(" CreateDate < #{comment.commentTimeTo} ");
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
				FROM("comment");
				if (params.get("comment") != null) {
					Comment comment = (Comment) params.get("comment");
					if (comment.getNewinfo() != null
							&& comment.getNewinfo().getId() > 0) {
						WHERE(" Nid = #{comment.newinfo.id} ");
					}
					if (comment.getStatus() > 0) {
						WHERE(" Status = #{comment.status} ");
					}
					if (comment.getUser() != null
							&& comment.getUser().getId() > 0) {
						WHERE(" Uid = #{comment.user.id} ");
					}
					if (comment.getCommentTimeFrom() != null
							&& !"".equals(comment.getCommentTimeFrom())) {
						WHERE(" CreateDate >= #{comment.commentTimeFrom} ");
					}
					if (comment.getCommentTimeTo() != null
							&& !"".equals(comment.getCommentTimeTo())) {
						WHERE(" CreateDate < #{comment.commentTimeTo} ");
					}
				}
			}
		}.toString();
	}

}
