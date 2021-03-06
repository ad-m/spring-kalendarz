package info.jawne.kalendarz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.User;
import info.jawne.kalendarz.models.Week;

public class PersistentEventDao extends EventDao {
	private SessionFactory sessionFactory; // Uchwyt do obiektu fabryki sesji

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAll() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM " + Event.class.getName()).getResultList();
		}
	}

	@Override
	public Event getById(long id) {
		try (Session session = sessionFactory.openSession()) {
			return (Event) session.get(Event.class.getName(), id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getRange(User user, Date start, Date end) {
		System.out.println(start);
		System.out.println(end);
		try (Session session = sessionFactory.openSession()) {

			return session
					.createQuery("FROM " + Event.class.getName() + " ee WHERE ee.eventStart <= :end "
							+ "AND ee.eventEnd >= :start AND ee.user.id = :user_id order by ee.eventStart")
					.setParameter("start", start).setParameter("end", end).setParameter("user_id", user.getId())
					.getResultList();
		}
	}

	@Override
	public void remove(Event obj) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		}
	}

	@Override
	public void saveOrUpdate(Event v) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(v);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> all(User user) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM " + Event.class.getName() + " ee WHERE ee.user.id = :user_id")
					.setParameter("user_id", user.getId()).getResultList();

		}
	}

	@Override
	public List<Event> getWeek(User user, Week week) {
		return getRange(user, week.getStart(), week.getEnd());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> find(User user, String query) {
		try (Session session = sessionFactory.openSession()) {
			return session
					.createQuery("FROM " + Event.class.getName()
							+ " ee WHERE ee.user.id = :user_id AND ee.description LIKE :query")
					.setParameter("user_id", user.getId()).setParameter("query", "%" + query + "%").getResultList();

		}
	}

	@Override
	public Date getMax(User user) {
		return all(user).stream().max((p1, p2) -> p1.getEventStart().compareTo(p2.getEventStart())).get()
				.getEventStart();

	}

	@Override
	public Date getMin(User user) {
		return all(user).stream().min((p1, p2) -> p1.getEventStart().compareTo(p2.getEventStart())).get()
				.getEventStart();
	}

	@Override
	public Event getWithCategory(long id) {
		try (Session session = sessionFactory.openSession()) {
			return (Event) session
					.createQuery("FROM " + Event.class.getName() + " as e left join fetch e.category where e.id = :id")
					.setParameter("id", id).getSingleResult();
		}
	}

	@Override
	public boolean isDateFree(User user, Date start, Date end) {
		try (Session session = sessionFactory.openSession()) {
			@SuppressWarnings("unchecked")
			Query<Long> query = session
					.createQuery("select count(*) FROM " + Event.class.getName()
							+ " as e where e.user.id = :user and  eventEnd >= :start AND eventStart <= :end")
					.setParameter("user", user.getId()).setParameter("start", start).setParameter("end", end);
			return (query.getSingleResult()) == 0;
		}
	};
}
