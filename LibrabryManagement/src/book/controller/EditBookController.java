package book.controller;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import book.view.EditBookDialog;

public class EditBookController implements ActionListener {

	private EditBookDialog dialog;
	public EditBookController(EditBookDialog dialog) {
		this.dialog = dialog;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cm  = e.getActionCommand();
		
		if (cm.equals("Lưu")) {
			this.dialog.editBook();
		}else if(cm.equals("Hủy")) {
			this.dialog.dispose();
		}
	}

}
