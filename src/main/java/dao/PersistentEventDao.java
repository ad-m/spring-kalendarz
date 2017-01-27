package dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Event;
import model.Month;
import model.User;
import model.Week;
import model.Year;

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
					.createQuery("FROM " + Event.class.getName() + " ee WHERE ee.eventStart > :start "
							+ "AND ee.eventEnd < :end AND ee.user.id = :user_id")
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

	@Override
	public List<Month> getMonthList(User user) {

		Set<Month> months = new HashSet<Month>();
		for (Event event : all(user)) {
			LocalDate localDate = event.getEventStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Month month = new Month(localDate.getMonthValue(), localDate.getYear());
			if (!months.contains(month)) {
				months.add(month);
			}
		}
		return new ArrayList<Month>(months);
	}

	@Override
	public List<Year> getYearList(User user) {

		return all(user).stream().map(i -> i.getYear()).map(i -> i.getYear()).distinct().map(i -> new Year(i))
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> getMonth(User user, Month month) {
		return getRange(user, month.getStartDate(), month.getEndDate());
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
	public List<Month> getMonthList(User user, Year year) {
		Set<Month> months = new HashSet<Month>();
		for (Event event : getYear(user, year)) {
			LocalDate localDate = event.getEventStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Month month = new Month(localDate.getMonthValue(), localDate.getYear());
			if (!months.contains(month)) {
				months.add(month);
			}
		}
		return new ArrayList<Month>(months);
	}

	@Override
	public List<Event> getYear(User user, Year year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year.getYear());
		cal.set(Calendar.DAY_OF_YEAR, 1);
		Date start = cal.getTime();

		// set date to last day of 2014
		cal.set(Calendar.YEAR, year.getYear());
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve

		Date end = cal.getTime();
		return getRange(user, start, end);
	}

	@Override
	public List<Week> getWeekList(User user, Month month) {
		Set<Week> weeks = new HashSet<Week>();
		System.out.println(getMonth(user, month));
		for (Event event : getMonth(user, month)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(event.getEventStart());
			System.out.println(event.getEventStart() + "==>" + cal.get(Calendar.WEEK_OF_YEAR));
			Week week = new Week(month, cal.get(Calendar.WEEK_OF_YEAR));
			if (!weeks.contains(week)) {
				weeks.add(week);
			}
		}
		System.out.print(weeks);
		return new ArrayList<Week>(weeks);
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
}
