package dao;

import model.User;

abstract public class UserDao implements AbstractDao<User> {

	abstract public User getByUsernameOrNull(String username);

	abstract public boolean checkUser(String username, String password);

	abstract public int countAll();
}
