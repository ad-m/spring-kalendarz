package info.jawne.kalendarz.controllers;

import java.util.Calendar;
import java.util.Date;

import model.Month;
import model.Week;

public class TestDate {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Week week = new Week(new Month(0, 2016), i);
			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.YEAR, week.getMonth().getYear());
			cal.set(Calendar.WEEK_OF_YEAR, week.getWeek_no() * 7);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date start = ((Calendar) cal.clone()).getTime();
			cal.set(Calendar.DAY_OF_YEAR, week.getWeek_no() * 7 + 7);
			Date end = ((Calendar) cal.clone()).getTime();
			System.out.println(i + "-->" + start + "===>" + end);
		}
		;
	}

}
