package jira.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import jira.contracts.IUserService;
import jira.db.HibernateUtils;
import jira.exceptions.EmailException;
import jira.exceptions.UserException;
import jira.models.User;

@Service
public class DbUserService implements IUserService {
	static int userId;

	@Override
	public User signIn(String email, String password) throws UserException, NoSuchAlgorithmException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		if (user != null && user.getPassword().equals(getEncryptedPassword(password))) {
			DbUserService.userId = 1;
			session.close();
			DbUserService.userId = user.getId();
			return user;
		}
		session.close();
		throw new UserException("Invalid email or password");
	}

	@Override
	public User singUp(String email, String password) throws EmailException, NoSuchAlgorithmException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		Object object = criteria.uniqueResult();
		if (object != null) {
			throw new EmailException("Email already exists");
		}
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(getEncryptedPassword(password));
		session.save(newUser);
		transaction.commit();
		session.close();
		return newUser;
	}

	@SuppressWarnings("restriction")
	private final static String getEncryptedPassword(final String clearTextPassword) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(clearTextPassword.getBytes());
		return new sun.misc.BASE64Encoder().encode(md.digest());

	}

}
