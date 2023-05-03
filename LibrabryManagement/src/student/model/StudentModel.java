package student.model;

import java.sql.Date;
import java.util.Objects;

import student.dao.StudentDAOImp;

public class StudentModel extends Person{
	private String studentId;
	private String className;
	private String faculty;
	
	public StudentModel(String name, Date dateOfBirth, String gender, String address, String phoneNumber, String studentId,
			String className, String faculty) {
		super(name, dateOfBirth, gender, address, phoneNumber);
		this.studentId = studentId;
		this.className = className;
		this.faculty = faculty;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public static StudentModel getStudentById(String id) {
		return StudentDAOImp.getInstance().selectById(id);
	}

	@Override
	public String toString() {
		return "Mã sinh viên: " + studentId + ", " + super.toString() + ", Lớp: " + className + ", Khoa: " + faculty ;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(className, faculty, studentId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentModel other = (StudentModel) obj;
		return Objects.equals(className, other.className) && Objects.equals(faculty, other.faculty)
				&& Objects.equals(studentId, other.studentId);
	}
	
	
}
