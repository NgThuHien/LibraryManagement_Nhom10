package book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import book.view.AddBookDialog;
import book.view.BookView;

public class AddBookController implements ActionListener {

	private AddBookDialog dialog;
	public AddBookController(AddBookDialog dialog) {
		this.dialog = dialog;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cm  = e.getActionCommand();
		
		if (cm.equals("Thêm")) {
			this.dialog.addBook();
		}else if(cm.equals("Đặt lại")) {
			this.dialog.clearForm();
		}
	}

}
