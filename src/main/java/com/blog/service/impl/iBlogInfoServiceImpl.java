package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blog.Model.BlogInfoQueryModel;
import com.blog.common.Page;
import com.blog.dao.BaseDao;
import com.blog.dao.IBlogInfoDao;
import com.blog.entity.BlogInfo;
import com.blog.service.IBlogInfoService;

@Component
public class iBlogInfoServiceImpl extends BaseServiceimpl<BlogInfo,BlogInfoQueryModel> implements IBlogInfoService {
	@Autowired
	private IBlogInfoDao iBlogInfoDao;

	////	public void setiBlogInfoDao(IBlogInfoDao iBlogInfoDao) {
////		this.iBlogInfoDao = iBlogInfoDao;
////		super.setDao(iBlogInfoDao);
////	}
//	
////	private BaseDao dao;
//	public void setDao(BaseDao dao){
//		this.iBlogInfoDao = dao;
//	}
//	@Override
//	public void create(BlogInfo m) {
//		dao.create(m);
//	}
//
//	@Override
//	public void update(BlogInfo m) {
//		dao.update(m);
//	}
//
//	@Override
//	public void delete(int uuid) {
//		dao.delete(uuid);
//	}
//
//	@Override
//	public BlogInfo getByUuid(int uuid) {
//		return (BlogInfo)dao.getByUuid(uuid);
//	}
//
//	@Override
//	public Page<BlogInfo> getByConditionPage(BlogInfoQueryModel qm) {
//		List<BlogInfo> result = dao.getByConditionPage(qm);
//		qm.getPage().setResult(result);
//		return qm.getPage();
//	}

}
