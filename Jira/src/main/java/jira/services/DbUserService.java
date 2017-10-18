package jira.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import jira.contracts.IUserService;
import jira.db.HibernateUtils;
import jira.exceptions.InvalidUserException;
import jira.exceptions.ResourceNotFoundException;
import jira.models.User;

@Service
public class DbUserService implements IUserService {

	@Override
	public User signIn(String email, String password, HttpServletRequest request)
			throws ResourceNotFoundException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		if (user != null && user.getPassword().equals(getEncryptedPassword(password))) {
			session.close();
			request.getSession().setAttribute("user_id", user.getId());
			return user;
		}
		session.close();
		throw new ResourceNotFoundException("Invalid email or password");
	}

	@Override
	public User singUp(String email, String password, HttpServletRequest request)
			throws InvalidUserException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		Object object = criteria.uniqueResult();
		if (object != null) {
			session.close();
			throw new InvalidUserException("Email already exists");
		}
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(getEncryptedPassword(password));
		session.save(newUser);
		transaction.commit();
		session.close();
		request.getSession().setAttribute("user_id", newUser.getId());
		return newUser;
	}

	@SuppressWarnings("restriction")
	private final static String getEncryptedPassword(final String clearTextPassword) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(clearTextPassword.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return new sun.misc.BASE64Encoder().encode(md.digest());

	}
}
