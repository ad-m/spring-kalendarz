package controllers;

import java.beans.PropertyEditorSupport;

import dao.CategoryDao;
import model.Category;

public class CategoryEditor extends PropertyEditorSupport {

	public CategoryEditor(CategoryDao category_dao) {
		this.category_dao = category_dao;
	}

	CategoryDao category_dao;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		Category a = new Category();
		a.setId(Integer.parseInt(text));
		setValue(a);
	}

	@Override
	public String getAsText() { // Zamiana obiektu na ³añcuch znaków
		Category accessory = (Category) getValue();
		return accessory == null ? null : Long.toString(accessory.getId());
	}
}