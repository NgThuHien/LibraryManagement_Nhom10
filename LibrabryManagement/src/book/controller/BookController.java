package book.controller;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import book.view.AddBookDialog;
import book.view.BookView;
import home.view.MainView;

public class BookController implements ActionListener{

	private BookView view;
	
	public BookController(BookView view) {
		super();
		this.view = view;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cm  = e.getActionCommand();
		
		//JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào: " + cm);
		if (cm.equals("Thêm")) {
			AddBookDialog addBook = new AddBookDialog();
			addBook.setVisible(true);
		}else if (cm.equals("Sửa")) {
			this.view.checkSelected();
		}else if (cm.equals("Xóa")) {
			this.view.deleteBook();
		}else if (cm.equals("Tìm kiếm")) {
			this.view.searchBook();
		}else if (cm.equals("Đặt lại")) {
			this.view.resetBookView();
		}else if (cm.equals("Thoát")) {
			this.view.dispose();
			MainView mainView = new MainView();
			mainView.setVisible(true);
		}
		
	}

}
