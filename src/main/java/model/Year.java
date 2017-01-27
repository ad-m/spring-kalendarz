package model;

public class Year {
	private int year;

	public Year(int year) {
		super();
		if (year < 100) {
			throw new IllegalStateException();
		}
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
		Year other = (Year) obj;
		if (year != other.year)
			return false;
		return true;
	}

	public int getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		return result;
	}

	@Override
	public String toString() {
		return new Integer(year).toString();
	}

}
