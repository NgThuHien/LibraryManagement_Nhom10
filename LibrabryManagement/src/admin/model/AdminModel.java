package admin.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

import admin.dao.AdminDAOImp;
import student.model.Person;

public class AdminModel extends Person{
	private int adminId;;
	private String accountName;
	private String password;
	
	public AdminModel(String name, Date dateOfBirth, String gender, String address,
			String phoneNumber, int adminId,String accountName, String password) {
		super(name, dateOfBirth, gender, address, phoneNumber);
		this.adminId = adminId;
		this.accountName = accountName;
		this.password = password;
	}
	
	public int getAdminId() {
		return adminId;
	}
//	public AdminModel(int adminId,String accountName, String password) {
//		this.adminId = adminId;
//		this.accountName = accountName;
//		this.password = password;
//	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static AdminModel getAdminById(int id) {
		return AdminDAOImp.getInstance().selectById(id);
	}
	public static ArrayList<AdminModel> getAdminByName(String name) {
		return AdminDAOImp.getInstance().selectByName(name);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(accountName, adminId, password);
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
		AdminModel other = (AdminModel) obj;
		return Objects.equals(accountName, other.accountName) && adminId == other.adminId
				&& Objects.equals(password, other.password);
	}
}
