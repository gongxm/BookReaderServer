package com.gongxm.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	/*private static SessionFactory factory;
	static {
		// 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
		// 在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		// 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
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
