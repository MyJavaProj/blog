package com.blog.service;

import java.util.List;

import com.blog.Model.BlogInfoQueryModel;
import com.blog.entity.BlogInfo;

public interface BlogInfoService {

	List<BlogInfo>getBlogInfoList();
	
	void create(BlogInfo model);
	void update(BlogInfo model);
//	void delete(BlogInfo model);
	int delete(long blogInfoId);
	
	BlogInfo getByUuid(int uuid);

	List<BlogInfo> getByConditionPage(BlogInfoQueryModel qm);
}
