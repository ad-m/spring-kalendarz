package info.jawne.kalendarz.dao;

import java.util.Date;
import java.util.List;

import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.User;
import info.jawne.kalendarz.models.Week;

abstract public class EventDao implements AbstractDao<Event> {
	// abstract public List<Event> getAfter(Date date);
	// abstract public List<Event> getBefore(Date date);
	abstract public List<Event> getRange(User user, Date start, Date end);

	abstract public List<Event> getWeek(User user, Week week);

	abstract public List<Event> all(User user);

	abstract public List<Event> find(User user, String string);

	abstract public Event getWithCategory(long id);

	abstract public Date getMax(User user);

	abstract public Date getMin(User user);

	public abstract boolean isDateFree(User user, Date start, Date end);;
}
