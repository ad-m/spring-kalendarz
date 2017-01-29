package info.jawne.kalendarz.dao;

import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeriodList {
	List<Period> period = new ArrayList<Period>();

	{
		add(Period.of(0, 1, 0));
		add(Period.of(0, 0, 7));
		add(Period.of(0, 0, 3));
		add(Period.of(0, 0, 1));

	}

	private boolean add(Period e) {
		return period.add(e);
	}

	public Period[] toArray() {
		return (Period[]) period.toArray();
	}

	public Iterator<Period> iterator() {
		return period.iterator();
	}

	public List<Period> asList() {
		return period;
	}

}
