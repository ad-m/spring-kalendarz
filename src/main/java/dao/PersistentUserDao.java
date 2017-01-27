package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.User;

public class PersistentUserDao extends UserDao {
	private SessionFactory sessionFactory; // Uchwyt do obiektu fabryki sesji

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM " + User.class.getName()).getResultList();
		}
	}

	@Override
	public User getById(long id) {
		try (Session session = sessionFactory.openSession()) {
			return (User) session.get(User.class.getName(), id);
		}
	}

	@Override
	public User getByUsernameOrNull(String username) {
		try {
			try (Session session = sessionFactory.openSession()) {
				return (User) session.createQuery("FROM " + User.class.getName() + " WHERE username = :username")
						.setParameter("username", username).getSingleResult();

			}
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void remove(User obj) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		}
	}

	@Override
	public void saveOrUpdate(User v) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(v);
			session.getTransaction().commit();
		}
	}

	// Setter umożliwiający wstrzyknięcie obiektu sesji przez kontener IoC
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean checkUser(String username, String password) {
		User user = getByUsernameOrNull(username);
		if (user == null) {
			return false;
		}
		return true;

		// return user.verifyPassword(password);
	}

	@Override
	public int countAll() {
		return getAll().size();
	}

}
