package student.model;

import java.sql.Date;
import java.util.Objects;

public class Person {
	private String name;
	private Date dateOfBirth;
	private String gender;
	private String address;
	private String phoneNumber;
	public Person(String name, Date dateOfBirth, String gender, String address, String phoneNumber) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, dateOfBirth, gender, name, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "Họ tên: " + name;
	}
	
	
}
