package student.dao;


public interface StudentDAOInterface<T> {
//	public void insert(T t);
//	public void update(T t);
//	public void delete(T t);
	public T selectById(String id);

}
