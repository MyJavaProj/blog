package com.blog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.Model.BlogInfoQueryModel;
import com.blog.dao.BlogInfoDao;
import com.blog.entity.BlogInfo;
import com.blog.service.BlogInfoService;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BlogInfoDao blogInfoDao;
	
	@Override
	public List<BlogInfo> getBlogInfoList() {
//		List<BlogInfo> blogInfo = blogInfoDao.queryAll(0, 10);
		
		BlogInfo model = null;
		List<BlogInfo> blogInfo = blogInfoDao.query(model);
		System.out.println("blogInfo size:" + blogInfo.size());
		logger.debug("===logger first in use==");
		return blogInfo;
	}

	@Override
	@Transactional
	public void create(BlogInfo model) {
		blogInfoDao.create(model);
	}

	@Override
	@Transactional
	public void update(BlogInfo model) {
		blogInfoDao.update(model);
	}

	@Override
	public BlogInfo getByUuid(int uuid) {
		return blogInfoDao.getByUuid(uuid);
	}

	@Override
	public List<BlogInfo> getByConditionPage(BlogInfoQueryModel qm) {
		return blogInfoDao.getByConditionPage(qm);
	}

	@Override
	public List<BlogInfo> getSplitPages(int index,int count){
		
		return blogInfoDao.getSplitPages(index,count);
	}
//	@Override
//	public void delete(BlogInfo model) {
//		
//	}

	@Override
	public int delete(long blogInfoId) {
		return blogInfoDao.delete(blogInfoId);
	}

}