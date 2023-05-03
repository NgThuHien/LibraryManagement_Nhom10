package lendingslip.controller;

import java.util.Date;
import javax.swing.JOptionPane;

import admin.model.AdminModel;
import book.model.BookModel;
import student.model.StudentModel;


public class ValidateFormLD {
	public static boolean validateForm(String adId, String studentId, String bookId, Date lendDate, Date dueDate, int amount) {
		if (studentId.isEmpty() || bookId.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin.");
			return false;
		}else if (!studentId.matches("^[a-zA-Z0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã sinh viên không hợp lệ.");
			return false;
		}else if (!bookId.matches("^[a-zA-Z0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ.");
			return false;
		}else if (!adId.matches("^[0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã admin không hợp lệ.");
			return false;
		} else if (StudentModel.getStudentById(studentId) == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã " + studentId);
			return false;
		} else if (BookModel.getBookById(bookId) == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại sách có mã " + bookId);
			return false;
		} else if (AdminModel.getAdminById(Integer.valueOf(adId)) == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại admin có mã là  " + adId);
			return false;
		} else if (lendDate.after(dueDate)) {
			JOptionPane.showMessageDialog(null, "Ngày hạn trả phải sau ngày mượn ");
			return false;
		} else if (amount > 5) {
			JOptionPane.showMessageDialog(null, "Chỉ được mượn tối đa 5 quyển sách.");
			return false;
		}else if (BookModel.getBookById(bookId).getRemainingAmount() < amount) {
			JOptionPane.showMessageDialog(null, "Số lượng sách sẵn có không đủ");
			return false;
		}else {
			return true;
		}
	}
}

//public class ValidateFormLD {
//	public static boolean validateForm(String adId, String studentId, Date lendDate, Date dueDate) {
//		if (studentId.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Vui lòng đầy đủ thông tin.");
//			return false;
//		}else if (!studentId.matches("^[a-zA-Z0-9]{1,15}$")) {
//			JOptionPane.showMessageDialog(null, "Mã sinh viên không hợp lệ.");
//			return false;
//		}else if (!adId.matches("^[0-9]{1,15}$")) {
//			JOptionPane.showMessageDialog(null, "Mã admin không hợp lệ.");
//			return false;
//		} else if (StudentModel.getStudentById(studentId) == null) {
//			JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã " + studentId);
//			return false;
//		} else if (AdminModel.getAdminById(Integer.valueOf(adId)) == null) {
//			JOptionPane.showMessageDialog(null, "Không tồn tại admin có mã là  " + adId);
//			return false;
//		} else if (lendDate.after(dueDate)) {
//			JOptionPane.showMessageDialog(null, "Ngày hạn trả phải sau ngày mượn ");
//			return false;
//		}else {
//			return true;
//		}
//	}
//}