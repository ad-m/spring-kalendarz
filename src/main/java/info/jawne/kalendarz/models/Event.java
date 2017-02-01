package info.jawne.kalendarz.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "eventEnd", nullable = false)
	private Date eventEnd;

	@Column(name = "eventStart", nullable = false)
	private Date eventStart;

	public Event(User user, Category category, String description, Date eventEnd, Date eventStart) {
		super();
		this.user = user;
		this.category = category;
		this.description = description;
		this.eventEnd = eventEnd;
		this.eventStart = eventStart;
	}

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Category getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public long getId() {
		return id;
	}

	public int getYear() {
		LocalDate localDate = getEventStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.getYear();
	}

	public int getWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getEventStart());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public User getUser() {
		return user;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", description=" + description + ", eventStart=" + eventStart + ", eventEnd="
				+ eventEnd + "]";
	}

	public Duration getDuration() {
		ZonedDateTime duration_start = ZonedDateTime.ofInstant(this.getEventStart().toInstant(),
				ZoneId.systemDefault());
		ZonedDateTime duration_end = ZonedDateTime.ofInstant(this.getEventEnd().toInstant(), ZoneId.systemDefault());
		return Duration.between(duration_start, duration_end);
	}

}
