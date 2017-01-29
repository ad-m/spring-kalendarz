package info.jawne.kalendarz.dao;

import java.util.List;

import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.User;

abstract public class CategoryDao implements AbstractDao<Category> {
	abstract public List<Category> forUser(User user);

}
