package info.jawne.kalendarz.controllers.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.validation.constraints.NotNull;

import info.jawne.kalendarz.models.DurationTime;

public class EventLookupCommand {

	@NotNull
	Date date;

	@NotNull
	Duration durationEvent;

	@NotNull
	DurationTime durationLookup;

	public DurationTime getDurationLookup() {
		return durationLookup;
	}

	public void setDurationLookup(DurationTime durationLookup) {
		this.durationLookup = durationLookup;
	}

	public Duration getDurationEvent() {
		return durationEvent;
	}

	public void setDurationEvent(Duration durationEvent) {
		this.durationEvent = durationEvent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStart() {
		return Date.from(getDate().toInstant().minus(durationLookup.getDuration()));
	}

	public Date getEnd() {
		return Date.from(getDate().toInstant().plus(durationLookup.getDuration()));
	};
}
