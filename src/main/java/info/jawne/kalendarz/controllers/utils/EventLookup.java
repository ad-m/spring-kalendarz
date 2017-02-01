package info.jawne.kalendarz.controllers.utils;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.User;

public class EventLookup {
	public class EventMatch {
		Event event;
		Duration free_time;
		public Integer id;

		public Event getEvent() {
			return event;
		}

		public Duration getFree_time() {
			return free_time;
		}

		public Integer getId() {
			return id;
		}

		public EventMatch(Integer id, Event event, Duration free_time) {
			this.id = id;
			this.event = event;
			this.free_time = free_time;
		}

	}

	EventDao event_dao;
	Date start;
	Date end;
	Duration duration;
	User user;

	public EventLookup(EventDao event_dao, User user, Date start, Date end, Duration duration) {
		this.event_dao = event_dao;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.user = user;
	}

	private Duration duration(Event a, Event b) {
		start = a.getEventEnd();
		end = b.getEventStart();
		ZonedDateTime duration_start = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
		ZonedDateTime duration_end = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
		return Duration.between(duration_start, duration_end);
	};

	public List<EventMatch> getList() {
		List<Event> list = event_dao.getRange(user, start, end);
		List<EventMatch> result = new ArrayList<EventMatch>();

		int id = 0;
		if (list.size() < 2) {
			return result;
		}
		list.sort((Event o1, Event o2) -> o1.getEventStart().compareTo(o2.getEventEnd()));
		for (int i = 0; i < list.size() - 1; i++) {
			Duration event_duration = duration(list.get(i), list.get(i + 1));
			if (event_duration.compareTo(this.duration) > 0) {
				result.add(new EventMatch(id++, list.get(i), event_duration));
			}
		}

		return result;
	}

}