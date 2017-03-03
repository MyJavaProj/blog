package com.blog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.Model.BaseModel;
import com.blog.common.Page;
import com.blog.dao.BaseDao;
import com.blog.service.IBaseService;

//@Service
public class BaseServiceimpl<M,QM extends BaseModel> implements IBaseService<M, QM> {
	private BaseDao dao;
	public void setDao(BaseDao dao){
		this.dao = dao;
	}
	@Override
	public void create(M m) {
		dao.create(m);
	}

	@Override
	public void update(M m) {
		dao.update(m);
	}

	@Override
	public void delete(int uuid) {
		dao.delete(uuid);
	}

	@Override
	public M getByUuid(int uuid) {
		return (M)dao.getByUuid(uuid);
	}

	@Override
	public Page<M> getByConditionPage(QM qm) {
		List<M> result = dao.getByConditionPage(qm);
		qm.getPage().setResult(result);
		return qm.getPage();
	}
	
}
