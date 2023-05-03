package lendingslip.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import book.view.AddBookDialog;
import book.view.BookView;
import lendingslip.view.AddLendingDialog;

public class AddLendingController implements ActionListener {

	private AddLendingDialog dialog;
	public AddLendingController(AddLendingDialog dialog) {
		this.dialog = dialog;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cm  = e.getActionCommand();
		
		if (cm.equals("Thêm")) {
			this.dialog.addLSlip();
		}else if(cm.equals("Đặt lại")) {
			this.dialog.clearForm();
		}else if(cm.equals("Hủy")) {
			this.dialog.dispose();
		}else if(cm.equals("Thêm sách vào phiếu")) {
			this.dialog.addBookToLS();
		}
	}

}
