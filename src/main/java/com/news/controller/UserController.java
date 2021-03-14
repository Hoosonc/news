package com.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.news.pojo.Admin;
import com.news.pojo.Comment;
import com.news.pojo.Pager;
import com.news.pojo.Users;
import com.news.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 处理用户注册的请求
	@RequestMapping("/reg")
	public ModelAndView reg(@ModelAttribute Users user, HttpSession session,
			ModelAndView mv) {
		user.setStatus(2);
		// 执行添加操作
		int result = userService.addUser(user);
		if (result > 0) {
			// 执行NewsinfoController的list方法
			mv.setViewName("redirect:/newsinfo/list");
		}
		return mv;
	}

	// 处理用户登录的请求
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("loginName") String loginName,
			@RequestParam("loginPwd") String loginPwd, HttpSession session,
			ModelAndView mv) {
		Users u = userService.login(loginName, loginPwd);
		if (u != null && u.getLoginName() != null) {
			// 将用户保存到HttpSession中
			session.setAttribute("u", u);
		}
		// 执行NewsinfoController类中的list方法
		mv.setViewName("redirect:/newsinfo/list");
		return mv;
	}

	// 处理用户注销的请求
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpSession session, ModelAndView mv) {
		session.invalidate();
		mv.setViewName("redirect:/newsinfo/list");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/getUsers")
	public List<Users> getUsers() {
		Users u = new Users();
		u.setId(0);
		u.setLoginName("请选择...");
		List<Users> uList = userService.getAllUsers();
		uList.add(0, u);
		return uList;
	}

	// 后台用户列表显示
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(Integer page, Integer rows,
			@ModelAttribute Users user) {
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		// 封装查询条件
		Map<String, Object> params = new HashMap<>();

		params.put("user", user);
		// 根据查询条件计算用户记录数
		int totalCount = userService.count(params);
		// 根据Map中的条件查询指定页显示的用户列表
		List<Users> users = userService.findUsers(user, pager);
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 向Map类型的对象result中放入键值对，键为“total”,值为totalCount
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为comments
		result.put("rows", users);
		// 通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}

	// 启用或禁用用户
	@RequestMapping(value = "/setIsEnableUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setIsEnableUser(@RequestParam(value = "uids") String uids,
			@RequestParam(value = "flag") String flag) {
		try {
			userService.modifyStatus(uids.substring(0, uids.length() - 1),
					Integer.parseInt(flag));
			return "{\"success\":\"true\",\"message\":\"设置成功！\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"设置失败！\"}";
		}
	}
}
