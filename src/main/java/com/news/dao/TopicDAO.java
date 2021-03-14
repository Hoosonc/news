package com.news.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import com.news.dao.provider.TopicDynaSqlProvider;
import com.news.pojo.Topic;

public interface TopicDAO {
	// 根据id查询新闻主题
	@Select("select * from topic where Id = #{id}")
	Topic selectById(int id);

	// 查询所有新闻主题
	@Select("select * from topic")
	List<Topic> selectAllTopic();

	// 分页动态查询
	@SelectProvider(type = TopicDynaSqlProvider.class, method = "selectWithParam")
	List<Topic> selectByPage(Map<String, Object> params);

	// 根据条件动态查询主题总记录数
	@SelectProvider(type = TopicDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	// 添加主题
	@Insert("insert into topic(Name) values(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Topic topic);

	// 修改主题
	@Update("update topic set Name=#{name} where Id=#{id}")
	void edit(Topic topic);
}
