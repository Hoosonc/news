package com.news.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.news.dao.provider.UserDynaSqlProvider;
import com.news.pojo.Newsinfo;
import com.news.pojo.Users;

public interface UserDAO {
	// 添加用户
	@Insert("insert into users(LoginName,LoginPwd,Status) values(#{loginName},#{loginPwd},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Users user);

	// 根据登录名和密码查询合法用户
	@Select("select * from users where LoginName = #{loginName} and LoginPwd = #{loginPwd} and Status=2")
	public Users selectByLoginNameAndPwd(@Param("loginName") String loginName,
			@Param("loginPwd") String loginPwd);

	// 根据用户编号获取用户对象
	@Select("select * from users where Id = #{id}")
	Users selectById(int id);

	// 获取所有用户
	@Select("select * from users")
	List<Users> selectAll();

	// 根据登录名，分页动态查询用户
	@SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
	List<Users> selectByPage(Map<String, Object> params);

	// 根据条件查询用户总数
	@SelectProvider(type = UserDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	// 更新用户状态
	@Update("update users set Status=${flag} where id in (${ids})")
	void updateState(@Param("ids") String ids, @Param("flag") int flag);	
	
}
