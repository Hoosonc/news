package com.news.controller;

import java.util.Date;
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
import com.news.pojo.Comment;
import com.news.pojo.Pager;
import com.news.pojo.Users;
import com.news.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping("/addComment")
	public ModelAndView addComment(@ModelAttribute Comment comment,
			HttpSession session, ModelAndView mv) {
		Users u = (Users) session.getAttribute("u");
		comment.setUser(u);
		comment.setCreateDate(new Date());
		comment.setStatus(1); // 初始时，评论状态为未审核，前端页面不显示该评论
		// 执行添加操作
		commentService.addComment(comment);
		// 执行NewsinfoController的list方法
		mv.setViewName("redirect:/newsinfo/list");
		return mv;
	}

	// 后台管理评论列表显示
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(Integer page, Integer rows,
			@ModelAttribute Comment comment, Integer userId) {
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		// 封装查询条件
		Map<String, Object> params = new HashMap<>();

		if (userId != null) {
			Users user = new Users();
			user.setId(userId);
			comment.setUser(user);
		}

		params.put("comment", comment);
		// 根据查询条件计算评论记录数
		int totalCount = commentService.count(params);
		// 根据Map中的条件查询指定页显示的评论列表
		List<Comment> comments = commentService.findCommentForBackstage(
				comment, pager);
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 向Map类型的对象result中放入键值对，键为“total”,值为totalCount
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为comments
		result.put("rows", comments);
		// 通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}

	// 评论审核
	@RequestMapping(value = "/commentAudit", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String commentAudit(@RequestParam(value = "ids") String ids) {
		try {
			commentService.modifyStatus(ids.substring(0, ids.length() - 1));
			return "{\"success\":\"true\",\"message\":\"审核成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"审核失败\"}";
		}
	}

	// 删除审核
	@RequestMapping(value = "/deleteComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteComment(@RequestParam(value = "ids") String ids) {
		try {
			commentService.deleteCommentByIds(ids.substring(0, ids.length() - 1));
			return "{\"success\":\"true\",\"message\":\"删除成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"删除失败\"}";
		}
	}
}
