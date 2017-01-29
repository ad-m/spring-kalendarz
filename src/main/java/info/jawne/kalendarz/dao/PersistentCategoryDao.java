package info.jawne.kalendarz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.User;

public class PersistentCategoryDao extends CategoryDao {
	private SessionFactory sessionFactory; // Uchwyt do obiektu fabryki sesji

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getAll() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM " + Category.class.getName()).getResultList();
		}
	}

	@Override
	public Category getById(long id) {
		try (Session session = sessionFactory.openSession()) {
			return (Category) session.get(Category.class.getName(), id);
		}
	}

	@Override
	public void remove(Category obj) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.createQuery(
					"UPDATE " + Event.class.getName() + " set category_id = NULL WHERE category_id = :cat_id ")
					.setParameter("cat_id", obj.getId()).executeUpdate();
			session.delete(obj);
			session.getTransaction().commit();
		}
	}

	@Override
	public void saveOrUpdate(Category v) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> forUser(User user) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM " + Category.class.getName() + " WHERE user_id = :user_id")
					.setParameter("user_id", user.getId()).getResultList();

		}
	}

}
