package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", isAdmin=" + isAdmin + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@Column(name = "isAdmin", nullable = false)
	public boolean isAdmin = false;

	@Column(name = "password", nullable = false)
	public String password;

	@Column(name = "registrationDate", nullable = false)
	public Date registrationDate = new Date();

	@Column(name = "username", nullable = false, unique = true)
	public String username;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.setPassword(password);
	}

	public User(String username, String password, boolean isAdmin) {
		this(username, password);
		this.isAdmin = isAdmin;
	}

	public long getId() {
		return id;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isAnonymous() {
		return this.getId() == 0;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set password of user and encrypt them.
	 * 
	 * @param password
	 *            - a unencrypted password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Verify of user password
	 * 
	 * @param password
	 *            - a unencrypted password of user
	 * @return status of password verification
	 */
	public boolean verifyPassword(String password) {
		return true;
	}

}
