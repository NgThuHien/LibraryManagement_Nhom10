package book.dao;

import java.util.ArrayList;


public interface BookDAOInterface<T> {
	public void insert(T t);
	public void update(T t);
	public void delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(String id);
	public ArrayList<T> selectByCtg(int ctgId);
	public ArrayList<T> selectByName(String name);
	public ArrayList<T> selectByAuthor(String au);
	public ArrayList<T> selectByPublisher(String pub);

}
