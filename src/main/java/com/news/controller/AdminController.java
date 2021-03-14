package com.news.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.news.pojo.Admin;
import com.news.pojo.Functions;
import com.news.pojo.TreeNode;
import com.news.service.AdminService;
import com.news.util.JsonFactory;

@SessionAttributes(value = { "admininfo" })
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	// 处理管理员登录请求
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(@RequestParam("loginName") String loginName,
			@RequestParam("loginPwd") String loginPwd, ModelMap model) {
		Admin admininfo = adminService.login(loginName, loginPwd);
		if (admininfo != null && admininfo.getLoginName() != null) {
			// 验证通过后，再判断是否已为该管理员分配功能权限
			if (adminService.getAdminAndFunctions(admininfo.getId()).getFs()
					.size() > 0) {
				// 验证通过且已分配功能权限，则将admininfo对象存入model中
				model.put("admininfo", admininfo);
				// 以JSON格式向页面发送成功信息
				return "{\"success\":\"true\",\"message\":\"登录成功\"}";
			} else {
				return "{\"success\":\"false\",\"message\":\"您没有权限，请联系超级管理员设置权限！\"}";
			}
		} else
			return "{\"success\":\"false\",\"message\":\"登录失败\"}";
	}
	
	// 给后台管理页面中树型菜单提供数据源
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeNode> getTree(
			@RequestParam(value = "adminid") String adminid) {
		// 根据管理员id号获取AdminInfo对象及关联的Functions对象集合
		Admin admininfo = adminService.getAdminAndFunctions(Integer
				.parseInt(adminid));
		List<TreeNode> nodes = new ArrayList<TreeNode>();		
		List<Functions> functionsList = admininfo.getFs();	
		Collections.sort(functionsList);
		// 将排序后的Functions对象集合转换到List<TreeNode>类型的列表nodes中
		for (Functions f : functionsList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(f.getId());
			treeNode.setFid(f.getParentid());
			treeNode.setText(f.getName());
			nodes.add(treeNode);
		}
		// 调用自定义工具类JsonFactory的buildtree方法，为nodes列表中个TreeNode元素中的children赋值(即该节点包含的子节点)
		List<TreeNode> treeNodes = JsonFactory.buildtree(nodes, 0);
		// 以JSON格式向页面返回绑定tree所需的数据
		return treeNodes;
	}
	
	// 处理注销请求
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout(SessionStatus status) {
		// @SessionAttributes清除
		status.setComplete();
		return "{\"success\":\"true\",\"message\":\"注销成功\"}";
	}
}
