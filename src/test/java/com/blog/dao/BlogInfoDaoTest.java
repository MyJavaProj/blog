package com.blog.dao;

import com.blog.entity.BlogInfo;
import com.blog.service.BlogInfoService;

import java.util.List;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

//import org.junit.runner.RunWith;
import javax.annotation.Resource;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class BlogInfoDaoTest extends BaseTest{
	@Resource(name="blogInfoDao")
	private BlogInfoDao blogInfoDao;
	
//	@Resource(name="iBlogInfoDao")
//	private IBlogInfoDao iBlogInfoDao;
//	
//	@Test
//	public void create(){
//		BlogInfo m = new BlogInfo();
//		m.setMark("mark");
//		m.setTitle("title");
//		m.setContent("content");
//		iBlogInfoDao.create(m);
//		System.out.println("====create=====");
//	}
	
	@Test
	public void queryDaoAll(){
		int off=0,limit=10;
		List<BlogInfo> blogInfoList = blogInfoDao.queryAll(off, limit);
		for(BlogInfo model :blogInfoList){
			System.out.println("=======");
			System.out.println(model);
		}
	}
	
	@Test
	public void queryById(){
		long blogInfoId = 1000L;
		BlogInfo model = blogInfoDao.queryById(blogInfoId);
		System.out.println(model);
	}
}
