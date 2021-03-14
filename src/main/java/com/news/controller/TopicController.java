package com.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.pojo.Pager;
import com.news.pojo.Topic;
import com.news.pojo.Users;
import com.news.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;

	// 后台管理新闻主题列表显示
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(Integer page, Integer rows,
			@ModelAttribute Topic topic) {
		// 创建分页对象
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		// 封装查询条件
		Map<String, Object> params = new HashMap<>();
		params.put("topic", topic);
		// 根据查询条件计算所有主题记录数
		int totalCount = topicService.count(params);
		// 根据Map中的条件查询指定页显示的主题列表
		List<Topic> topics = topicService.findTopic(topic, pager);
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 向Map类型的对象result中放入键值对，键为“total”,值为totalCount
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为newsinfos,即当前页显示的新闻列表
		result.put("rows", topics);
		// 通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}

	// 添加主题
	// 新增管理员
	@RequestMapping(value = "/addTopic", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addTopic(Topic topic) {
		try {
			topicService.addTopic(topic);
			return "{\"success\":\"true\",\"message\":\"添加成功\"}";
		} catch (Exception e) {
			return "{\"success\":\"false\",\"message\":\"添加失败\"}";
		}
	}

	// 修改主题
	@RequestMapping(value = "/modifyTopic", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyTopic(Topic topic) {
		try {
			topicService.modify(topic);
			return "{\"success\":\"true\",\"message\":\"修改成功\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"false\",\"message\":\"修改失败\"}";
		}
	}

	// 获取主题列表
	@RequestMapping("/getTopic/{flag}")
	@ResponseBody
	public List<Topic> getTopic(@PathVariable("flag") Integer flag) {
		List<Topic> topicList = topicService.getAllTopic();
		if (flag==1) {
			Topic t = new Topic();
			t.setId(0);
			t.setName("请选择...");
			topicList.add(0, t);
		}		
		return topicList;
	}
}
