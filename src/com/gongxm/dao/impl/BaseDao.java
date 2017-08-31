package com.gongxm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.gongxm.dao.Dao;

public class BaseDao<T> extends HibernateDaoSupport implements Dao<T> {
	private Class<?> clazz;
	protected HibernateTemplate ht;

	public BaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<?>) type.getActualTypeArguments()[0];
	}

	@Autowired
	@Qualifier("sessionFactory")
	public void setSuperSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
		this.ht = getHibernateTemplate();
	}

	@Override
	public void add(T t) {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession();
		 * Transaction tx = session.beginTransaction(); session.save(t); tx.commit(); }
		 * catch (Exception e) { e.printStackTrace();
		 * session.getTransaction().rollback(); } finally { if (session != null) {
		 * session.close(); } }
		 */

		 ht.save(t);
	}

	@Override
	public void update(T t) {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession();
		 * Transaction tx = session.beginTransaction(); session.update(t); tx.commit();
		 * } catch (Exception e) { e.printStackTrace();
		 * session.getTransaction().rollback(); } finally { if (session != null) {
		 * session.close(); } }
		 */
		 ht.update(t);
	}

	/**
	 * 删除元素
	 */
	@Override
	public void delete(Serializable id) {
		/*
		 * Session session = null; try { session = HibernateUtil.getSession();
		 * Transaction tx = session.beginTransaction(); session.delete(id); tx.commit();
		 * } catch (Exception e) { e.printStackTrace();
		 * session.getTransaction().rollback(); } finally { if (session != null) {
		 * session.close(); } }
		 */

		 ht.delete(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findOne(Serializable id) {
		/*
		 * Session session = HibernateUtil.getSession(); T bean = (T) session.get(clazz,
		 * id); return bean;
		 */
		return  (T) ht.get(clazz, id);
	}

}
