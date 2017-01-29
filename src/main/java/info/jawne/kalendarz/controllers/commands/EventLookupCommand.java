package info.jawne.kalendarz.controllers.commands;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Period;

public class EventLookupCommand {
	@NotBlank
	String name;

	@NotBlank
	String description;

	@NotBlank
	Category category;

	@NotBlank
	Date date;

	@NotBlank
	Period period;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}
