//package com.jira.db;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//
////import com.jira.model.Attachment;
//import com.jira.model.Issue;
//import com.jira.model.Project;
//import com.jira.model.Sprint;
//import com.jira.model.User;
//
//import org.hibernate.HibernateException;
//
//public class HibernateUtils {
//	@Autowired
//	private static SessionFactory sessionFactory;
//
//	static {
//		try {
//			Configuration configuration = new Configuration().configure();
//			configuration.addAnnotatedClass(Issue.class);
//			configuration.addAnnotatedClass(Project.class);
//			configuration.addAnnotatedClass(Sprint.class);
//			configuration.addAnnotatedClass(User.class);
//			//configuration.addAnnotatedClass(Attachment.class);
//			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//			serviceRegistryBuilder.applySettings(configuration.getProperties());
//			StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//		} catch (HibernateException ex) {
//			System.out.println(ex);
//		}
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//}
