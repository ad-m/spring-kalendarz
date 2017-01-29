package info.jawne.kalendarz.dao;

import java.util.List;



public interface AbstractDao<T> {
	
	List<T> getAll();
	
	T getById(long id);

	void remove(T v);
	void saveOrUpdate(T v);
}