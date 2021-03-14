package com.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.news.dao.provider.CommentDynaSqlProvider;
import com.news.pojo.Comment;

public interface CommentDAO {
	// 添加评论
	@Insert("insert into comment(Nid,Content,Uid,CreateDate,Status) values(#{newinfo.id},#{content},#{user.id},#{createDate},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Comment comment);

	// 根据新闻编号，分页动态查询该新闻的评论
	@SelectProvider(type = CommentDynaSqlProvider.class, method = "selectWithParam")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "Content", property = "content"),
			@Result(column = "CreateDate", property = "createDate", javaType = java.util.Date.class),
			@Result(column = "Status", property = "status"),
			@Result(column = "Uid", property = "user", one = @One(select = "com.news.dao.UserDAO.selectById", fetchType = FetchType.EAGER)) })
	List<Comment> selectByPage(Map<String, Object> params);

	// 根据条件查询评论总数
	@SelectProvider(type = CommentDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	// 根据新闻id删除评论
	@Delete("delete from comment where Nid=#{nid}")
	void deleteByNid(int nid);

	// 评论审核
	@Update("update comment set Status=2 where id in (${ids})")
	void updateState(@Param("ids") String ids);

	// 删除评论
	@Delete("delete from comment where id in (${ids})")
	void deleteByIds(@Param("ids") String ids);
}
