package com.gongxm.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	/*private static SessionFactory factory;
	static {
		// 1. �������Ͱ�ȫ��׼����ע���࣬���ǵ�ǰӦ�õĵ������󣬲����޸ģ���������Ϊfinal
		// ��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		// 2. ���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
		factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static Session getSession() {
		return factory.openSession();
	}*/
	
	
	private static Configuration config;
	private static SessionFactory sessionFactory;
	
	static{
		
		config=new Configuration().configure();
		sessionFactory=config.buildSessionFactory();
	} 
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession(){
		
		return sessionFactory.getCurrentSession();
	}
}
