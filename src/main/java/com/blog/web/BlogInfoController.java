package com.blog.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.dao.BlogInfoDao;
import com.blog.entity.BlogInfo;
import com.blog.service.BlogInfoService;

@Controller // @Service @Componet
//@RequestMapping("/blog") // url:/模块/资源/{id}/细分 /seckill/list
public class BlogInfoController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BlogInfoService blogInfoService;

	@Resource(name="blogInfoDao")
	private BlogInfoDao blogInfoDao;
	
	@RequestMapping(value = "/toAdd",method = RequestMethod.GET)
	public String toAdd(){
		return "add";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("cm")BlogInfo model){
		
		blogInfoService.create(model);
		return "success";
	}
	
//	@RequestMapping(value = "/toDelete",method = RequestMethod.GET)
//	public String toDelete(@ModelAttribute("id") long blogInfoId){
//		blogInfoService.delete(blogInfoId);
//		return "success";
//	}
	@RequestMapping(value = "/delete/{blogInfoId}",method = RequestMethod.GET)
	public String delete(@PathVariable("blogInfoId") long blogInfoId){
		blogInfoService.delete(blogInfoId);
		return "success";
	}
	
	@RequestMapping(value = "/toUpdate/{blogInfoId}",method = RequestMethod.GET)
	public String toUpdate(Model mvcModel,@PathVariable("blogInfoId") long blogInfoId){
		BlogInfo model = blogInfoDao.queryById(blogInfoId);
		model.setBlogInfoId(blogInfoId);
		mvcModel.addAttribute("model", model);
		return "update";
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(@ModelAttribute("model")BlogInfo model){
		blogInfoService.update(model);
		return "success";
	}
	
	
	@RequestMapping(value = "/{blogInfoId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("blogInfoId") Long blogInfoId, Model model) {
		if (blogInfoId == null) {
			return "redirect:/blog/list";
		}
		return "detail";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<BlogInfo> list = blogInfoService.getBlogInfoList();
		model.addAttribute("list", list);
		return "list";// WEB-INF/jsp/"list".jsp
	}
}
