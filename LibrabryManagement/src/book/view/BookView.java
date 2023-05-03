package book.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import book.controller.BookController;
import book.model.BookModel;
import category.model.CategoryModel;
import lendingslip.model.LendingSlipDetail;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.border.TitledBorder;

public class BookView extends JFrame {

	private static JTable table;
	private static ArrayList<BookModel> listBook;
	private JTextField jTextId;
	private JTextField jTextName;
	private JComboBox<String> jCbbCtg;
	private JTextField jTextAuthor;
	private JTextField jTextPublisher;
	private static int selectedIndex;
	private static DefaultTableModel tableModel;
	private ArrayList<LendingSlipDetail> listBL;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookView frame = new BookView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// System.out.println(table.getRowCount());
			}
		});
	}

	public BookView() {
		this.init();
		listBook = BookModel.getAllBook();
		tableModel = (DefaultTableModel) table.getModel();
		this.showListBook(listBook);
	}

	public void init() {

		this.setLayout(new BorderLayout(10, 20));

		Font font1 = new Font("Tahoma", Font.BOLD, 14);
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
//
//		JMenuBar menuBar = new JMenuBar();
//
//		JMenu jMenuBook = new JMenu("Quản lý sách");
//		jMenuBook.setFont(font2);
//		menuBar.add(jMenuBook);
//
//		JMenu jMenuCategory = new JMenu("Quản lý loại sách");
//		jMenuCategory.setFont(font2);
//		menuBar.add(jMenuCategory);
//
//		this.setJMenuBar(menuBar);

		ActionListener action = new BookController(this);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Tìm kiếm", TitledBorder.LEADING,
				TitledBorder.TOP, font1, Color.BLACK));
		panelSearch.setLayout(new GridBagLayout());

		JLabel labelId = new JLabel("Mã sách:");
		JLabel labelName = new JLabel("Tên sách:");
		JLabel labelCtg = new JLabel("Thể loại:");
		JLabel labelAuthor = new JLabel("Tác giả:");
		JLabel labelPublisher = new JLabel("Nhà xuất bản:");
		labelId.setFont(font2);
		labelName.setFont(font2);
		labelCtg.setFont(font2);
		labelAuthor.setFont(font2);
		labelPublisher.setFont(font2);

		jTextId = new JTextField();
		jTextId.setFont(font2);

		jTextName = new JTextField();
		jTextName.setFont(font2);

		jCbbCtg = new JComboBox<String>();
		jCbbCtg.setFont(font2);
		jCbbCtg.addItem("");
		new CategoryModel();
		ArrayList<CategoryModel> listCtg = CategoryModel.getAllCategory();
		for (CategoryModel category : listCtg) {
			jCbbCtg.addItem(category.getCategoryName());
		}
		jCbbCtg.setPrototypeDisplayValue("");
		jCbbCtg.setMaximumRowCount(5);

		jTextAuthor = new JTextField();
		jTextAuthor.setFont(font2);

		jTextPublisher = new JTextField();
		jTextPublisher.setFont(font2);

		JButton buttonSearch = new JButton("Tìm kiếm");
		buttonSearch.setFont(font2);
		buttonSearch.addActionListener(action);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 10, 10, 0);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.05;
		panelSearch.add(labelId, c);
		c.gridy = 1;
		panelSearch.add(labelName, c);
		c.gridy = 2;
		panelSearch.add(labelCtg, c);

		c.insets = new Insets(10, 10, 10, 80);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.45;
		c.fill = GridBagConstraints.BOTH;
		panelSearch.add(jTextId, c);
		c.gridy = 1;
		panelSearch.add(jTextName, c);
		c.gridy = 2;
		panelSearch.add(jCbbCtg, c);

		c.insets = new Insets(10, 0, 10, 10);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.05;
		panelSearch.add(labelAuthor, c);
		c.gridy = 1;
		panelSearch.add(labelPublisher, c);

		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.45;
		c.fill = GridBagConstraints.BOTH;
		panelSearch.add(jTextAuthor, c);
		c.gridy = 1;
		panelSearch.add(jTextPublisher, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panelSearch.add(buttonSearch, c);

		this.getContentPane().add(panelSearch, BorderLayout.NORTH);

		JPanel panelInfor = new JPanel();
		panelInfor.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Danh sách sách",
				TitledBorder.LEADING, TitledBorder.TOP, font1, Color.BLACK));
		panelInfor.setLayout(new BorderLayout());

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã sách", "Tên sách", "Thể loại",
				"Tác giả", "Nhà xuất bản", "Giá tiền", "Số lượng", "Sẵn có" }));
		table.setFont(font2);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);
		panelInfor.add(scrollPane);
		this.getContentPane().add(panelInfor, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));

		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(font2);
		panelButtons.add(buttonAdd);
		buttonAdd.addActionListener(action);

		JButton buttonUpdate = new JButton("Sửa");
		buttonUpdate.setFont(font2);
		panelButtons.add(buttonUpdate);
		buttonUpdate.addActionListener(action);

		JButton buttonDelete = new JButton("Xóa");
		buttonDelete.setFont(font2);
		panelButtons.add(buttonDelete);
		buttonDelete.addActionListener(action);

		JButton buttonReset = new JButton("Đặt lại");
		buttonReset.setFont(font2);
		panelButtons.add(buttonReset);
		buttonReset.addActionListener(action);

		JButton buttonExit = new JButton("Thoát");
		buttonExit.setFont(font2);
		panelButtons.add(buttonExit);
		buttonExit.addActionListener(action);
		this.getContentPane().add(panelButtons, BorderLayout.SOUTH);

		this.setTitle("Quản lý sách");
		this.setSize(1200, 700);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void showListBook(ArrayList<BookModel> listBook) {
		tableModel.setRowCount(0);
		for (BookModel book : listBook) {
			tableModel.addRow(new Object[] { book.getBookId(), book.getBookName(), book.getCategory().getCategoryName(),
					book.getAuthor(), book.getPublisher(), book.getPrice(), book.getAmount(),
					book.getRemainingAmount() });
		}
		tableModel.fireTableDataChanged();
	}

	public static void addBookToTable(BookModel book) {
		tableModel.addRow(new Object[] { book.getBookId(), book.getBookName(), book.getCategory().getCategoryName(),
				book.getAuthor(), book.getPublisher(), book.getPrice(), book.getAmount(), book.getRemainingAmount() });
		tableModel.fireTableDataChanged();
		listBook.add(book);
	}

	public void searchBook() {
		String id = this.jTextId.getText();
		String name = this.jTextName.getText();
		int ctg = this.jCbbCtg.getSelectedIndex();
		String author = this.jTextAuthor.getText();
		String publisher = this.jTextPublisher.getText();

		Set<BookModel> bookNotSatisfied = new HashSet<>();
		if (id.trim().length() > 0) {
			BookModel b_id = BookModel.getBookById(id);
			for (BookModel book : BookModel.getAllBook()) {
				if (!book.equals(b_id)) {
					bookNotSatisfied.add(book);
				}
			}
		}
		if (name.trim().length() > 0) {
			ArrayList<BookModel> b_name = BookModel.getBookName(name);
			for (BookModel book : BookModel.getAllBook()) {
				if (!b_name.contains(book)) {
					bookNotSatisfied.add(book);
				}
			}
		}
		if (ctg > 0) {
			ArrayList<BookModel> b_ctg = BookModel.getBookByCtg(ctg);
			for (BookModel book : BookModel.getAllBook()) {
				if (!b_ctg.contains(book)) {
					bookNotSatisfied.add(book);
				}
			}
		}
		if (author.trim().length() > 0) {
			ArrayList<BookModel> b_au = BookModel.getBookByAuthor(author);
			for (BookModel book : BookModel.getAllBook()) {
				if (!b_au.contains(book)) {
					bookNotSatisfied.add(book);
				}
			}
		}
		if (publisher.trim().length() > 0) {
			ArrayList<BookModel> b_pub = BookModel.getBookByPublisher(publisher);
			for (BookModel book : BookModel.getAllBook()) {
				if (!b_pub.contains(book)) {
					bookNotSatisfied.add(book);
				}
			}
		}

		ArrayList<BookModel> bookSatisfied = new ArrayList<>();
		for (BookModel book : BookModel.getAllBook()) {
			if (!bookNotSatisfied.contains(book)) {
				bookSatisfied.add(book);
			}
		}
		if (bookSatisfied.isEmpty()) {
			tableModel.setRowCount(0);
			JOptionPane.showMessageDialog(this, "Không có sách nào được tìm thấy");
		} else {
			this.showListBook(bookSatisfied);
		}
	}

	public void checkSelected() {
		selectedIndex = table.getSelectedRow();
		//System.out.println(selectedIndex);
		if (listBook.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa có sách nào trong danh sách, vui lòng thêm sách để có thể sửa.");
		} else if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sách để sửa!");
		} else {
			EditBookDialog edit = new EditBookDialog();
			edit.setEditData(listBook.get(selectedIndex));
			edit.setVisible(true);
		}
	}

	public static void removeRowSelect() {
		tableModel.removeRow(selectedIndex);
		tableModel.fireTableDataChanged();
	}

	public static void insertRow(Object[] row) {
		tableModel.insertRow(selectedIndex, row);
		tableModel.fireTableDataChanged();
	}
	
	public static void updateList(BookModel b) {
		listBook.remove(selectedIndex);
		listBook.add(selectedIndex, b);
	}
	
	

	public void deleteBook() {
		selectedIndex = table.getSelectedRow();
		if (listBook.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa có sách nào trong danh sách, vui lòng thêm sách để có thể xóa.");
		} else if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sách để xóa!");
		} else {
			int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?");
			if (choose == JOptionPane.YES_OPTION) {
                boolean flag = checkDelBook(listBook.get(selectedIndex));
                if (flag) {
                    BookModel.deleteBook(listBook.get(selectedIndex));
                    listBook.remove(selectedIndex);
                    tableModel.removeRow(selectedIndex);
                    tableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(this, "Xóa thành công");

                } else {
                    JOptionPane.showMessageDialog(this, 
                    		"Không thể xóa sách vì có sinh viên đang mượn");
                }
			}

		}

	}
	

	public boolean checkDelBook(BookModel b) {
		listBL = LendingSlipDetail.getByBookId(b.getBookId());
		if(!listBL.isEmpty()) {
			return false;
		}else {
			return true;
		}
		  
	}
	public void clearForm() {
		jTextId.setText("");
		jTextName.setText("");
		jCbbCtg.setSelectedIndex(0);
		jTextAuthor.setText("");
		jTextPublisher.setText("");
	}

	public void resetBookView() {
		this.showListBook(listBook);
		this.clearForm();
	}
}
