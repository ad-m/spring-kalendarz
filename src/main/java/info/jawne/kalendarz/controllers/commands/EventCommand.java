package info.jawne.kalendarz.controllers.commands;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import model.Category;

public class EventCommand {
	@NotBlank
	Date startDate;

	@NotBlank
	Date endDate;

	@NotBlank
	String description;

	Category category;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

}
