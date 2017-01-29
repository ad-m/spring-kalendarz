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

}
