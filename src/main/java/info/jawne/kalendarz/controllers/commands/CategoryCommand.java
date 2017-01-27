package info.jawne.kalendarz.controllers.commands;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryCommand {
	@NotBlank
	String name;

	@NotBlank
	String description;

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

}
