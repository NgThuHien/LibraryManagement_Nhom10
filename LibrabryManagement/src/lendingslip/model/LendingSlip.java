package lendingslip.model;

import java.sql.Date;


import java.util.ArrayList;
import java.util.Objects;

import admin.model.AdminModel;
import lendingslip.dao.LendingDAOImp;
import student.model.StudentModel;

public class LendingSlip {
	private int lendingSlipId;
	private AdminModel admin;
	private StudentModel student;
	private Date lendDate;
	private Date dueDate;;
	private Date returnDate;
	private String status;
	
	public LendingSlip(int lendingSlipId, AdminModel admin, StudentModel student, Date lendDate, Date dueDate,
			Date returnDate, String status) {
		super();
		this.lendingSlipId = lendingSlipId;
		this.admin = admin;
		this.student = student;
		this.lendDate = lendDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	
	public LendingSlip(AdminModel admin, StudentModel student, Date lendDate, Date dueDate, Date returnDate,
			String status) {
		super();
		this.admin = admin;
		this.student = student;
		this.lendDate = lendDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}

	public int getLendingSlipId() {
		return lendingSlipId;
	}
	public void setLendingSlipId(int lendingSlipId) {
		this.lendingSlipId = lendingSlipId;
	}
	public AdminModel getAdmin() {
		return admin;
	}
	public void setAdmin(AdminModel admin) {
		this.admin = admin;
	}
	public StudentModel getStudent() {
		return student;
	}
	public void setStudent(StudentModel student) {
		this.student = student;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static void addLD(LendingSlip l) {
		LendingDAOImp.getInstance().insert(l);
	}
	@Override
	public int hashCode() {
		return Objects.hash(admin, dueDate, lendDate, lendingSlipId, returnDate, status, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LendingSlip other = (LendingSlip) obj;
		return Objects.equals(admin, other.admin) && Objects.equals(dueDate, other.dueDate)
				&& Objects.equals(lendDate, other.lendDate) && lendingSlipId == other.lendingSlipId
				&& Objects.equals(returnDate, other.returnDate) && Objects.equals(status, other.status)
				&& Objects.equals(student, other.student);
	}

	public static void updateLS(LendingSlip l) {
		LendingDAOImp.getInstance().update(l);
	}
	public static void deleteLS(LendingSlip l) {
		LendingDAOImp.getInstance().delete(l);
	}
	public static ArrayList<LendingSlip> getAll(){
		return LendingDAOImp.getInstance().selectAll();
	}
	public static LendingSlip getById(int id){
		return LendingDAOImp.getInstance().selectById(id);
	}
	public static ArrayList<LendingSlip> getByStId(String id){
		return LendingDAOImp.getInstance().selectByStudentID(id);
	}

	public static ArrayList<LendingSlip> getByStatus(String stt){
		return LendingDAOImp.getInstance().selectByStatus(stt);
	}

	public static int getIDMax(){
		return LendingDAOImp.getInstance().selectIdMax();
	}
	
	
}
