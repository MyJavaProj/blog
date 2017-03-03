package com.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.Model.BlogInfoQueryModel;
import com.blog.common.Page;
import com.blog.dao.BaseTest;
import com.blog.entity.BlogInfo;

public class BlogInfoServiceTest extends BaseTest {
	@Autowired
	private BlogInfoService blogInfoService;

//	@Autowired
//	private IBlogInfoService iblogInfoService ;
//	
//	@Test
//	public void create() {
//		BlogInfo m = new BlogInfo();
//		m.setMark("mark");
//		m.setTitle("title");
//		m.setContent("content");
//		iblogInfoService.create(m);
//		
//		System.out.println("====create=====");
//	}

	@Test
	public void queryServiceAll() {
		List<BlogInfo> blogInfoList = blogInfoService.getBlogInfoList();
		for (BlogInfo model : blogInfoList) {
			System.out.println("=======");
			System.out.println(model);
		}
		
		BlogInfo m = new BlogInfo();
		m.setMark("mark");
		m.setTitle("title");
		m.setContent("content");
		blogInfoService.create(m);
		System.out.println("====create=====:"+m);
		
//		m.setTitle("===1===");
//		m.setBlogInfoId(1011);
//		blogInfoService.update(m);
		
//		blogInfoService.delete(1012);
	}
}
