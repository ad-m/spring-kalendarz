package info.jawne.kalendarz.models;

import java.util.Calendar;
import java.util.Date;

public class Week {
	private int year;
	private int week_no;

	public Week(int year, int week_no) {
		this.year = year;
		this.week_no = week_no;
	}

	public int getYear() {
		return year;
	}

	public int getWeek_no() {
		return week_no;
	}

	private Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, this.year);
		cal.set(Calendar.WEEK_OF_YEAR, this.week_no);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public Date getStart() {
		Calendar cal = getCalendar();
		return cal.getTime();
	}

	public Date getEnd() {
		Calendar cal = getCalendar();
		cal.add(Calendar.DAY_OF_YEAR, 7);
		return cal.getTime();
	}

	public Week getNext() {
		Calendar cal = getCalendar();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return new Week(cal.get(Calendar.YEAR), cal.get(Calendar.WEEK_OF_YEAR));
	}

	public Week getPrev() {
		Calendar cal = getCalendar();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		return new Week(cal.get(Calendar.YEAR), cal.get(Calendar.WEEK_OF_YEAR));
	}

	@Override
	public String toString() {
		return "tydzień " + week_no + " roku " + year;
	}

}
