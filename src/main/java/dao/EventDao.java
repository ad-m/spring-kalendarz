package dao;

import java.util.Date;
import java.util.List;

import model.Event;
import model.Month;
import model.User;
import model.Week;
import model.Year;

abstract public class EventDao implements AbstractDao<Event> {
	// abstract public List<Event> getAfter(Date date);
	// abstract public List<Event> getBefore(Date date);
	abstract public List<Event> getRange(User user, Date start, Date end);

	abstract public List<Event> getYear(User user, Year year);

	abstract public List<Event> getMonth(User user, Month month);

	abstract public List<Event> getWeek(User user, Week week);

	abstract public List<Event> all(User user);

	abstract public List<Event> find(User user, String string);

	abstract public List<Year> getYearList(User user);

	abstract public List<Month> getMonthList(User user);

	abstract public List<Month> getMonthList(User user, Year year);

	abstract public List<Week> getWeekList(User user, Month month);

}
