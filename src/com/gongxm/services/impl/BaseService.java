package com.gongxm.services.impl;

import java.io.Serializable;

import com.gongxm.dao.Dao;
import com.gongxm.services.Service;

public abstract class BaseService<T> implements Service<T> {

	@Override
	public void add(T t) {
		getDao().add(t);
	}

	@Override
	public void update(T t) {
		getDao().update(t);
	}

	@Override
	public void delete(Serializable id) {
		getDao().delete(id);
	}

	@Override
	public T findOne(Serializable id) {
		return getDao().findOne(id);
	}

	public abstract Dao<T> getDao();
}
