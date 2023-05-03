package lendingslip.dao;

import java.util.ArrayList;


public interface LendingDAOInterface<T> {
	public void insert(T t);
	public void update(T t);
	public void delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(int id);
	public int selectIdMax();
	public ArrayList<T> selectByStatus(String status);
	public ArrayList<T> selectByStudentID(String stdID);

}
