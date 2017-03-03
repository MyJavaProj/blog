package com.blog.service;

import com.blog.Model.BaseModel;
import com.blog.common.Page;

public interface IBaseService <M,QM extends BaseModel>{
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> getByConditionPage(QM mq);
}
