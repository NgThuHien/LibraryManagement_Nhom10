package admin.dao;

import java.util.ArrayList;

public interface AdminDAOInterface<T> {
	public void insert(T t);
	public void update(T t);
	//public void delete(T t);
	//public ArrayList<T> getBy();
	public T selectByAccAndPw(String ac, String pw);
	public T selectById(int id);
	public ArrayList<T> selectByName(String name);

}
