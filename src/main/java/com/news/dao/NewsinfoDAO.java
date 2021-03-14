package com.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import com.news.dao.provider.NewsinfoDynaSqlProvider;
import com.news.pojo.Newsinfo;

public interface NewsinfoDAO {
	// 根据条件查询新闻总数
	@SelectProvider(type = NewsinfoDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	// 分页动态查询
	@SelectProvider(type = NewsinfoDynaSqlProvider.class, method = "selectWithParam")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "Title", property = "title"),
			@Result(column = "Author", property = "author"),
			@Result(column = "CreateDate", property = "createDate", javaType = java.util.Date.class),
			@Result(column = "Content", property = "content"),
			@Result(column = "Summary", property = "summary"),
			@Result(column = "Tid", property = "topic", one = @One(select = "com.news.dao.TopicDAO.selectById", fetchType = FetchType.EAGER)) })
	List<Newsinfo> selectByPage(Map<String, Object> params);

	// 根据主题获取前5条新闻

	@Select("select * from newsinfo where Tid = #{tid} limit 5")
	List<Newsinfo> selectTop5ByTid(int tid);

	// 根据新闻编号获取新闻对象
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "Title", property = "title"),
			@Result(column = "Author", property = "author"),
			@Result(column = "CreateDate", property = "createDate", javaType = java.util.Date.class),
			@Result(column = "Content", property = "content"),
			@Result(column = "Summary", property = "summary"),
			@Result(column = "Tid", property = "topic", one = @One(select = "com.news.dao.TopicDAO.selectById", fetchType = FetchType.EAGER)) })
	// 根据新闻编号获取新闻对象
	@Select("select * from newsinfo where Id = #{id}")
	Newsinfo selectById(int id);

	// 添加新闻
	@Insert("insert into newsinfo(Title,Author,CreateDate,Content,Summary,Tid) values(#{title},#{author},#{createDate},#{content},#{summary},#{topic.id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Newsinfo ni);

	// 修改新闻
	@Update("update newsinfo set Title=#{title},Author=#{author},CreateDate=#{createDate},Content=#{content},Summary=#{summary},Tid=#{topic.id} where Id=#{id}")
	void edit(Newsinfo ni);
	
	// 删除新闻
	@Delete("delete from newsinfo where id=#{id}")
	void deleteById(int id);
}
