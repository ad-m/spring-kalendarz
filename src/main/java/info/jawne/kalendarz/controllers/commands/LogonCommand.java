package info.jawne.kalendarz.controllers.commands;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LogonCommand {
	@NotBlank
	@Pattern(regexp = "[A-Za-z0-9]+")
	@Size(min = 5, max = 20)
	String username;

	@NotBlank
	@Size(min = 8, max = 20)
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
