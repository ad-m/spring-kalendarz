package info.jawne.kalendarz.dao;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import info.jawne.kalendarz.models.DurationTime;

public class DurationTimeDao {
	List<DurationTime> list = new ArrayList<DurationTime>();
	int id = 1;
	{
		add(new DurationTime(Duration.ofDays(1)));
		add(new DurationTime(Duration.ofDays(3)));
		add(new DurationTime(Duration.ofDays(7)));
	}

	public boolean add(DurationTime e) {
		e.setId(id++);
		return list.add(e);
	}

	public DurationTime findById(int id) {
		return list.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	public List<DurationTime> getAll() {
		return list;

	}
}
