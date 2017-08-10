package com.gongxm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gongxm.dao.Dao;
import com.gongxm.utils.HibernateUtil;

public class BaseDao<T> implements Dao<T> {
	private Class<?> clazz;

	public BaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<?>) type.getActualTypeArguments()[0];
	}

	@Override
	public void add(T t) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();// 回滚
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(T t) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();// 回滚
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 根据主键删除
	 */
	@Override
	public void delete(Serializable id) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();// 回滚
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	/**
	 * 根据主键查询
	 */
	public T findOne(Serializable id) {
		Session session = HibernateUtil.getSession();
		T bean = (T) session.get(clazz, id);
		return bean;
	}

}
