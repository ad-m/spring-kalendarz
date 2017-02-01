package info.jawne.kalendarz.models;

import java.time.Duration;

public class DurationTime {
	public int id;
	public Duration duration;

	public DurationTime(int id, Duration duration) {
		this.id = id;
		this.duration = duration;
	}

	public DurationTime(Duration duration) {
		this.duration = duration;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return String.format("%s dni", this.duration.toDays());
	}
}