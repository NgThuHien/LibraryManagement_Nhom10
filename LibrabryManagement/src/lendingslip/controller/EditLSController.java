package lendingslip.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import lendingslip.view.EditLendingDiaLog;

public class EditLSController implements ActionListener {

	private EditLendingDiaLog dialog;
	public EditLSController(EditLendingDiaLog dialog) {
		this.dialog = dialog;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cm  = e.getActionCommand();
		
		if (cm.equals("Lưu")) {
			this.dialog.editLSlip();
		}else if(cm.equals("Hủy")) {
			this.dialog.dispose();
		}else if(cm.equals("Thêm sách vào phiếu")) {
			this.dialog.addBookToLS();
		}
		else if(cm.equals("Xóa sách khỏi phiếu")) {
			this.dialog.deleteBook();
		}
	}

}
