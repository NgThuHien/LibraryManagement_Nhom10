package category.dao;

import java.util.ArrayList;


public interface CategoryDAOInterface<T> {
	public void insert(T t);
	public void update(T t);
	public void delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(int id);
	public ArrayList<T> selectByName(String name);

}
