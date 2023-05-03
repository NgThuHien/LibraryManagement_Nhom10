package book.controller;

import javax.swing.JOptionPane;

import book.model.BookModel;

public class ValidateForm {

	public static boolean validateForm(String id, String name, int ctg, String author, String publisher, String p) {

		if (id.isEmpty() || name.isEmpty() || ctg == 0 || author.isEmpty()
				|| publisher.isEmpty() || p.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		} else if (!id.matches("^[a-zA-Z0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ.");
			return false;
		}else if (!name.matches("^[a-zA-Z0-9\\p{L}\s.,?!-]{2,1000}$")) {
			JOptionPane.showMessageDialog(null, "Tên sách không hợp lệ.");
			return false;
		} else if (!author.matches("^[a-zA-Z\\p{L}\s]{2,100}$")) {
			JOptionPane.showMessageDialog(null, "Tên tác giả không hợp lệ.");
			return false;
		} else if (!publisher.matches("^[a-zA-Z\\p{L}\s.-]{2,1000}$")) {
			JOptionPane.showMessageDialog(null, "Tên nhà xuất bản không hợp lệ.");
			return false;
		} else if (!p.matches("^[1-9][0-9]{3,}(\\.[0-9]+)?$")) {
			JOptionPane.showMessageDialog(null, "Giá tiền không hợp lệ.");
			return false;
		} else {
			return true;
		}
	}
}
