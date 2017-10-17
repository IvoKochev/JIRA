package jira.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import jira.models.Issue;
import jira.models.Project;
import jira.models.Sprint;
import jira.models.User;
import jira.models.UserHasIssuse;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Issue.class);
			configuration.addAnnotatedClass(Project.class);
			configuration.addAnnotatedClass(Sprint.class);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(UserHasIssuse.class);
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
