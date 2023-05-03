package lendingslip.view;

import java.awt.*;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.text.*;

import admin.model.AdminModel;
import book.model.BookModel;
import lendingslip.controller.AddLendingController;
import lendingslip.controller.EditLSController;
import lendingslip.controller.ValidateFormLD;
import lendingslip.model.LendingSlip;
import lendingslip.model.LendingSlipDetail;
import student.model.StudentModel;

public class EditLendingDiaLog extends JDialog {

	private JTextField jTextStdId;
	private JTextField jTextBookId;
	private JSpinner spinnerAmount;
	private JSpinner spinnerLendD;
	private JSpinner spinnerDueD;
	private JTextField textId;
	private JComboBox<String> statusComboBox;
	private JTextField jTextAdId;
	private JTextArea textNote;
	private JButton buttonAddBook;
	private ArrayList<String> note = new ArrayList<>();
	private SimpleDateFormat dateFormat;
	private JFormattedTextField returnD;
	private JButton buttonDeleteBook;
	private Date returnDate;
	private LendingSlip l2;
	private ArrayList<String> noteCopy;
	private ArrayList<String> listIdBook;
	private boolean isOk = true;

	public EditLendingDiaLog() {
		this.init();
	}

	private void init() {

		JLabel label1 = new JLabel("Sửa thông tin phiếu mượn: ");
		label1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(label1, BorderLayout.NORTH);

		ActionListener action = new EditLSController(this);

		JPanel panelInput = new JPanel();
		panelInput.setLayout(new GridBagLayout());
		JLabel labelId = new JLabel("Mã phiếu:");
		JLabel labelAdId = new JLabel("Mã admin lập phiếu:");
		JLabel labelStdId = new JLabel("Mã sinh viên:");
		JLabel labelLendDate = new JLabel("Ngày mượn:");
		JLabel labelDueDate = new JLabel("Hạn trả:");
		JLabel labelReturnDate = new JLabel("Ngày trả:");
		JLabel labelBookId = new JLabel("Mã sách:");
		JLabel labelAmount = new JLabel("Số lượng:");
		JLabel labelStatus = new JLabel("Trạng thái:");

		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		labelId.setFont(font2);
		labelAdId.setFont(font2);
		labelStdId.setFont(font2);
		labelLendDate.setFont(font2);
		labelDueDate.setFont(font2);
		labelBookId.setFont(font2);
		labelAmount.setFont(font2);
		labelReturnDate.setFont(font2);
		labelStatus.setFont(font2);

		textId = new JTextField();
		textId.setFont(font2);

		jTextAdId = new JTextField();
		jTextAdId.setFont(font2);

		jTextStdId = new JTextField();
		jTextStdId.setFont(font2);

		// Tạo JSpinner và truyền vào SpinnerDateModel cho lend date
		spinnerLendD = new JSpinner(new SpinnerDateModel());
		spinnerLendD.setFont(font2);
		// Đặt định dạng ngày tháng cho JSpinner cho lend date
		JSpinner.DateEditor lendDateEditor = new JSpinner.DateEditor(spinnerLendD, "dd/MM/yyyy");
		spinnerLendD.setEditor(lendDateEditor);

		// Tạo JSpinner và truyền vào SpinnerDateModel cho due date
		spinnerDueD = new JSpinner(new SpinnerDateModel());
		spinnerDueD.setFont(font2);
		// Đặt định dạng ngày tháng cho JSpinner cho due date
		JSpinner.DateEditor dueDateEditor = new JSpinner.DateEditor(spinnerDueD, "dd/MM/yyyy");
		spinnerDueD.setEditor(dueDateEditor);

		// Return Date
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		MaskFormatter maskFormatter;
		try {
			maskFormatter = new MaskFormatter("##/##/####");
			returnD = new JFormattedTextField();
			returnD.setFont(font2);
			returnD.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(dateFormat)));
			maskFormatter.install(returnD);
			
		} catch (ParseException ex) {
			System.out.println("3");
			JOptionPane.showMessageDialog(this, "Ngày trả sách không hợp lệ");
			isOk = false;
		}
		
//		// Tạo JSpinner và truyền vào SpinnerDateModel cho return date
//		JSpinner spinnerReturnD = new JSpinner(new SpinnerDateModel());
//		spinnerReturnD.setFont(font2);
//		
//		JSpinner.DateEditor returnDateEditor = new JSpinner.DateEditor(spinnerReturnD, "dd/MM/yyyy");
//		spinnerReturnD.setEditor(returnDateEditor);

		jTextBookId = new JTextField();
		jTextBookId.setFont(font2);

		spinnerAmount = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
		// chỉnh cho giá trị sang bên trái.
		((JSpinner.DefaultEditor) spinnerAmount.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
		spinnerAmount.setFont(font2);

		statusComboBox = new JComboBox<String>();
		statusComboBox.addItem("Đang mượn");
		statusComboBox.addItem("Đã trả");
		statusComboBox.addItem("Quá hạn");
		statusComboBox.setFont(font2);

		textNote = new JTextArea("");
		textNote.setFont(font2);
		textNote.setWrapStyleWord(true);
		textNote.setLineWrap(true);
		textNote.setEditable(false);
		textNote.setBorder(null);
		textNote.setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 15, 10, 15);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		panelInput.add(labelId, c);
		c.gridy = 1;
		panelInput.add(labelAdId, c);
		c.gridy = 2;
		panelInput.add(labelStdId, c);
		c.gridy = 3;
		panelInput.add(labelLendDate, c);
		c.gridy = 4;
		panelInput.add(labelDueDate, c);
		c.gridy = 5;
		panelInput.add(labelReturnDate, c);
		c.gridy = 6;
		panelInput.add(labelBookId, c);
		c.gridy = 7;
		panelInput.add(labelAmount, c);
		c.gridy = 8;
		panelInput.add(labelStatus, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.BOTH;
		panelInput.add(textId, c);
		c.gridy = 1;
		panelInput.add(jTextAdId, c);
		c.gridy = 2;
		panelInput.add(jTextStdId, c);
		c.gridy = 3;
		panelInput.add(spinnerLendD, c);
		c.gridy = 4;
		panelInput.add(spinnerDueD, c);
		c.gridy = 5;
		panelInput.add(returnD, c);
		c.gridy = 6;
		panelInput.add(jTextBookId, c);
		c.gridy = 7;
		panelInput.add(spinnerAmount, c);
		c.gridy = 8;
		panelInput.add(statusComboBox, c);
		c.gridy = 9;
		panelInput.add(textNote, c);

		c.gridy = 10;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		buttonAddBook = new JButton("Thêm sách vào phiếu");
		buttonAddBook.setFont(font2);
		buttonAddBook.addActionListener(action);
		panelInput.add(buttonAddBook, c);
		c.gridy = 11;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		buttonDeleteBook = new JButton("Xóa sách khỏi phiếu");
		buttonDeleteBook.setFont(font2);
		buttonDeleteBook.addActionListener(action);
		panelInput.add(buttonDeleteBook, c);

		this.getContentPane().add(BorderLayout.CENTER, panelInput);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

		JButton buttonSave = new JButton("Lưu");
		buttonSave.setFont(font2);
		panelButtons.add(buttonSave);
		buttonSave.addActionListener(action);

		JButton buttonExit = new JButton("Hủy");
		buttonExit.setFont(font2);
		panelButtons.add(buttonExit);
		buttonExit.addActionListener(action);

		this.getContentPane().add(BorderLayout.SOUTH, panelButtons);

		this.setTitle("Sửa phiếu mượn");
		this.setSize(500, 700);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new EditLendingDiaLog();
	}

	public void setData(LendingSlip lendingSlip) {
		textId.setText(lendingSlip.getLendingSlipId() + "");
		this.textId.setEditable(false);
		jTextAdId.setText(lendingSlip.getAdmin().getAdminId() + "");
		jTextStdId.setText(lendingSlip.getStudent().getStudentId());
		spinnerLendD.setValue(lendingSlip.getLendDate());
		spinnerDueD.setValue(lendingSlip.getDueDate());
		if (lendingSlip.getReturnDate() != null) {
			returnD.setText(dateFormat.format(lendingSlip.getReturnDate()));
		}
		// dateFormat.parse(lendingSlip.getReturnDate());
		// returnD.setValue(dateFormat.format(lendingSlip.getReturnDate()));
		ArrayList<LendingSlipDetail> listLSD = LendingSlipDetail.getByLSId(lendingSlip.getLendingSlipId());
		listIdBook = new ArrayList<>();

		for (LendingSlipDetail lendingSlipDetail : listLSD) {
			listIdBook.add(lendingSlipDetail.getBook().getBookId());
			note.add(lendingSlipDetail.getBook().getBookId() + ", Số lượng: " + lendingSlipDetail.getAmount());
			jTextBookId.setText(lendingSlipDetail.getBook().getBookId());
		}
		String text = "";
		for (String string : note) {
			text += "Mã sách: " + string + "\n";
		}
		textNote.setText(text);
		noteCopy = new ArrayList<>(note);
		statusComboBox.setSelectedItem(lendingSlip.getStatus());
		// System.out.println(((java.util.Date) returnD.getValue()).getTime());
	}

	public void editLSlip() {
		//isOk = true;
		int id = Integer.valueOf(this.textId.getText().trim());
		String adId = this.jTextAdId.getText().trim();
		String studentId = this.jTextStdId.getText().trim();
		String bookId = this.jTextBookId.getText().trim();
		java.sql.Date lendDate = new Date(((java.util.Date) spinnerLendD.getValue()).getTime());
		java.sql.Date dueDate = new Date(((java.util.Date) spinnerDueD.getValue()).getTime());

		if (returnD.getValue() == null) {
			returnDate = null;
			System.out.println("1");
		} else {
			try {
				java.util.Date dateUtil = dateFormat.parse(returnD.getText());
				System.out.println(returnD.getText());
				returnDate = new Date(dateUtil.getTime());
				System.out.println(returnDate);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Ngày trả sách không hợp lệ");
				System.out.println("4");
				isOk = false;
			}

			// returnDate = new Date(((java.util.Date) returnD.getValue()).getTime());
		}
		int amount = (int) this.spinnerAmount.getValue();
		String status = (String) statusComboBox.getSelectedItem();

		isOk = ValidateFormLD.validateForm(adId, studentId, bookId, lendDate, dueDate, amount);
		if (returnDate != null) {
			System.out.println("2");
			if (lendDate.after(returnDate)) {

				JOptionPane.showMessageDialog(this, "Ngày trả phải sau ngày mượn ");
				isOk = false;
			}
		}
		if (isOk) {
			int adminId = Integer.valueOf(adId);
			l2 = new LendingSlip(id, AdminModel.getAdminById(adminId), StudentModel.getStudentById(studentId), lendDate,
					dueDate, returnDate, status);
			LendingSlip.updateLS(l2);

			if (note.isEmpty()) {
				LendingSlipDetail ld = new LendingSlipDetail(l2, BookModel.getBookById(bookId), amount);
				LendingSlipDetail.updateLSD(ld);
			} else {
				if (!(note.containsAll(noteCopy) && noteCopy.containsAll(note))) {
					for (String string : note) {
						String[] str = string.split(", Số lượng: ");
						BookModel b = BookModel.getBookById(str[0].trim());
						int a = Integer.valueOf(str[1].trim());
						LendingSlipDetail ld = new LendingSlipDetail(l2, b, a);
						if (!listIdBook.contains(str[0])) {
							LendingSlipDetail.addLD(ld);
						} else {
							LendingSlipDetail.updateLSD(ld);
						}
					}
				}
			}

			Object[] row = new Object[] { l2.getLendingSlipId(), l2.getAdmin().getName(),
					l2.getStudent().getStudentId(), l2.getLendDate(), l2.getDueDate(), l2.getReturnDate(),
					l2.getStatus() };

			LendingView.removeRowSelect();
			LendingView.insertRow(row);
			LendingView.updateList(l2);
			JOptionPane.showMessageDialog(this, "Sửa phiếu mượn thành công.");
		}
	}

	public void deleteBook() {
		this.textNote.setText("");
		int id = Integer.valueOf(this.textId.getText().trim());
		for (String string : note) {
			String[] str = string.split(", Số lượng: ");
			BookModel b = BookModel.getBookById(str[0].trim());
			int a = Integer.valueOf(str[1].trim());
			if (LendingSlipDetail.getByPK(id, b.getBookId()) != null) {
				LendingSlipDetail ld = new LendingSlipDetail(LendingSlip.getById(id), b, a);
				LendingSlipDetail.deleteLSD(ld);
				LendingView.deleteListDetail(ld);
			}
		}
		note.clear();

	}

	public void addBookToLS() {
		String bookId = this.jTextBookId.getText().trim();
		int amount = (int) this.spinnerAmount.getValue();
		boolean isOK = true;
		if (!bookId.matches("^[a-zA-Z0-9]{1,15}$")) {
			JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ.");
			isOK = false;
		} else if (BookModel.getBookById(bookId) == null) {
			JOptionPane.showMessageDialog(null, "Không tồn tại sách có mã " + bookId);
			isOK = false;
		} else if (amount > 5) {
			JOptionPane.showMessageDialog(null, "Chỉ được mượn tối đa 5 quyển sách.");
			isOK = false;
		} else if (BookModel.getBookById(bookId).getRemainingAmount() < amount) {
			JOptionPane.showMessageDialog(null, "Số lượng sách sẵn có không đủ");
			isOK = false;
		}
		if (isOK) {
			int S = 0;
			ArrayList<String> listId = new ArrayList<>();
			for (String string : note) {
				String[] str = string.split(", Số lượng: ");
				listId.add(str[0].trim());
				int a = Integer.valueOf(str[1].trim());
				S += a;
			}
			if (listId.contains(bookId)) {
				JOptionPane.showMessageDialog(this, "Sách này đã có trong phiếu mượn này rồi.");
			} else if ((amount + S) > 5) {
				JOptionPane.showMessageDialog(this,
						"Chỉ được mượn tối đa 5 quyển sách, " + "phiếu mượn này đã cho mượn " + S + " sách rồi.");
			} else {
				String n = bookId + ", Số lượng: " + amount;
				note.add(n);
				String text = "";
				for (String string : note) {
					text += "Mã sách: " + string + "\n";
				}
				textNote.setText(text);
			}
		}
	}

}
