package model;

import java.util.Calendar;
import java.util.Date;

public class Month {
	private int month;
	private int year;

	public Month(int month, int year) {
		super();
		if (month > 15) {
			throw new IllegalStateException();
		}
		if (year < 100) {
			throw new IllegalStateException();
		}
		this.month = month;
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Month other = (Month) obj;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public Date getEndDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public int getMonth() {
		return month;
	}

	public Date getStartDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public int getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public String toString() {
		return month + " miesiac w " + year;
	}

}
