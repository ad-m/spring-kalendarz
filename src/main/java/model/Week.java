package model;

import java.util.Calendar;
import java.util.Date;

public class Week {
	private Month month;
	int week_no; // 1-5

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getWeek_no() {
		return week_no;
	}

	public void setWeek_no(int week_no) {
		this.week_no = week_no;
	}

	public Week(Month month, int week_no) {
		this.month = month;
		this.week_no = week_no;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + week_no;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Week other = (Week) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (week_no != other.week_no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (week_no + 1) + " tydzień " + new Year(month.getYear()).toString() + " roku";
	}

	public Date getStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, this.getMonth().getYear());
		cal.set(Calendar.WEEK_OF_YEAR, this.getWeek_no() * 7);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public Date getEnd() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, this.getMonth().getYear());
		cal.set(Calendar.WEEK_OF_YEAR, this.getWeek_no() * 7);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_YEAR, 7);
		return cal.getTime();

	}
}
