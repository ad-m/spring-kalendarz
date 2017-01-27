package dao;

import java.util.List;

import model.Category;
import model.User;

abstract public class CategoryDao implements AbstractDao<Category> {
	abstract public List<Category> forUser(User user);

}
