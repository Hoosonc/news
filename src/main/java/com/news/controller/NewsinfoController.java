package com.news.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.news.pojo.Comment;
import com.news.pojo.Newsinfo;
import com.news.pojo.Pager;
import com.news.pojo.Topic;
import com.news.service.CommentService;
import com.news.service.NewsinfoService;
import com.news.service.TopicService;

@Controller
@RequestMapping("/newsinfo")
public class NewsinfoController {
	@Autowired
	private NewsinfoService newsinfoService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private CommentService commentService;

	// 考虑一下，newsinfo参数是否需要？？？？？
	@RequestMapping("/list")
	public String selectNewsinfo(Integer pageIndex, Integer topicId,
			@ModelAttribute Newsinfo newsinfo, Model model) {
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(1);
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if (pageIndex != null) {
			pager.setCurPage(pageIndex);
		}
		if (topicId != null) {
			Topic topic = new Topic();
			topic.setId(topicId);
			newsinfo.setTopic(topic);
		}
		// 获取所有新闻主题
		List<Topic> topics = topicService.selectAllTopic();
		// 查询新闻信息
		List<Newsinfo> newsinfos = newsinfoService
				.findNewsinfo(newsinfo, pager);
		// 获取前5条国内新闻
		List<Newsinfo> domesticNewsList = newsinfoService.selectTop5ByTid(1);
		// 获取前5条国际新闻
		List<Newsinfo> internationalNewsList = newsinfoService
				.selectTop5ByTid(2);
		// 设置Model数据
		model.addAttribute("newsinfos", newsinfos);
		model.addAttribute("domesticNewsList", domesticNewsList);
		model.addAttribute("internationalNewsList", internationalNewsList);
		model.addAttribute("pager", pager);
		model.addAttribute("topics", topics);
		// 返回新闻首页面
		return "index";
	}

	// 处理阅读新闻请求
	@RequestMapping("/newsread")
	public String newsread(Integer id, Model model,
			@ModelAttribute Comment comment, Integer pageIndex) {
		Newsinfo newsinfo = newsinfoService.selectById(id);
		// 获取前5条国内新闻
		List<Newsinfo> domesticNewsList = newsinfoService.selectTop5ByTid(1);
		// 获取前5条国际新闻
		List<Newsinfo> internationalNewsList = newsinfoService
				.selectTop5ByTid(2);
		// 获取所有新闻主题
		List<Topic> topics = topicService.selectAllTopic();
		model.addAttribute("newsinfo", newsinfo);
		model.addAttribute("domesticNewsList", domesticNewsList);
		model.addAttribute("internationalNewsList", internationalNewsList);
		model.addAttribute("topics", topics);
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(1);
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if (pageIndex != null) {
			pager.setCurPage(pageIndex);
		}
		if (id != null) {
			Newsinfo ni = new Newsinfo();
			ni.setId(id);
			comment.setNewinfo(ni);
			comment.setStatus(2); // 查询已审核评论
		}
		List<Comment> comments = commentService.findComment(comment, pager);
		model.addAttribute("comments", comments);
		model.addAttribute("pager", pager);
		return "news_read";
	}

	// 后台管理新闻列表显示
	@RequestMapping("/newslist")
	@ResponseBody
	public Map<String, Object> newslist(Integer page, Integer rows,
			@ModelAttribute Newsinfo newsinfo, Integer topicId) {
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		// 封装查询条件
		Map<String, Object> params = new HashMap<>();
		if (topicId != null) {
			Topic topic = new Topic();
			topic.setId(topicId);
			newsinfo.setTopic(topic);
		}
		params.put("newsinfo", newsinfo);
		// 根据查询条件计算所有新闻记录数
		int totalCount = newsinfoService.count(params);
		// 根据Map中的条件查询指定页显示的新闻列表
		List<Newsinfo> newsinfos = newsinfoService.findNewsinfoForBackstage(
				newsinfo, pager);
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 向Map类型的对象result中放入键值对，键为“total”,值为totalCount
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为newsinfos,即当前页显示的新闻列表
		result.put("rows", newsinfos);
		// 通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}

	// 添加新闻
	@RequestMapping(value = "/addNewsinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addNewsinfo(Newsinfo ni) {
		try {
			ni.setCreateDate(new Date());
			newsinfoService.addNewsinfo(ni);
			return "{\"success\":\"true\",\"message\":\"新闻添加成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"新闻添加失败\"}";
		}
	}

	// 编辑新闻
	@RequestMapping(value = "/modifyNewsinfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyNewsinfo(Newsinfo ni) {
		try {
			ni.setCreateDate(new Date());
			newsinfoService.modify(ni);
			return "{\"success\":\"true\",\"message\":\"新闻修改成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"新闻修改失败\"}";
		}
	}
	
	// 删除新闻
	@ResponseBody
	@RequestMapping(value = "/deleteNewsinfo", produces = "text/html;charset=UTF-8")
	public String deleteNewsinfo(String id) {
		String str = "";
		try {
			id = id.substring(0, id.length() - 1);
			String[] ids = id.split(",");
			for (String nid : ids) {
				commentService.removeCommentByNid(Integer.parseInt(nid));
				newsinfoService.removeNewsinfoById(Integer.parseInt(nid));
			}
			str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
		} catch (Exception e) {
			e.printStackTrace();
			str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
		}
		return str;
	}

}
