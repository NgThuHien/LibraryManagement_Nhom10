package lendingslip.dao;

import java.util.ArrayList;

public interface LDetailDAOInterface<T> {
	public void insert(T t);
	public void update(T t);
	//public void updateByLSId(T t);
	public void delete(T t);
	public void deleteById(int LendId);
	public T selectByPK(int LendId, String bookId);
	public ArrayList<T> selectByLSId(int id);
	public ArrayList<T> selectByBookId(String id);

}
