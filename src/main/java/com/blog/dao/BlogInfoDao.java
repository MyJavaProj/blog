package com.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.blog.Model.BlogInfoQueryModel;
import com.blog.entity.BlogInfo;

public interface BlogInfoDao {
	int saveUpdate(BlogInfo model);
	
	int delete(long blogInfoId);
	
	
	List<BlogInfo>queryAll(@Param("off")int off,@Param("limit")int limit);
	BlogInfo queryById(@Param("blogInfoId")long blogInfoId);
	
	/**
	 * 条件查询
	 * @param model
	 * @return
	 */
	List<BlogInfo> query(BlogInfo model);
	
	void create(BlogInfo mdoel);
	void update(BlogInfo mdoel);

	BlogInfo getByUuid(int uuid);

	List<BlogInfo> getByConditionPage(BlogInfoQueryModel qm);
	
	List<BlogInfo> getSplitPages(int index,int count);
}
