package category.view;

import javax.swing.*;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import book.controller.BookController;
import category.model.CategoryModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale.Category;
import java.util.Set;

import javax.swing.border.TitledBorder;

public class CategoryView extends JFrame {

	private JTable table;
	private ArrayList<CategoryModel> listCtg;
	private JTextField jTextId;
	private JTextField jTextName;
	
	private static DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryView frame = new CategoryView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryView() {
		this.init();
		listCtg = CategoryModel.getAllCategory();
		tableModel = (DefaultTableModel) table.getModel();
		this.showListCtg(listCtg);
	}

	public void init() {

		this.setLayout(new BorderLayout(10, 20));

		Font font1 = new Font("Tahoma", Font.BOLD, 14);
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu jMenu_qlySach = new JMenu("Quản lý sách");
		jMenu_qlySach.setFont(font2);
		menuBar.add(jMenu_qlySach);

		JMenu mnNewMenu_1 = new JMenu("Quản lý loại sách");
		menuBar.add(mnNewMenu_1);


		this.setJMenuBar(menuBar);

		//ActionListener action = new BookController(this);

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Tìm kiếm", TitledBorder.LEADING,
				TitledBorder.TOP, font1, Color.BLACK));
		panelSearch.setLayout(new GridBagLayout());
		JLabel labelId = new JLabel("Mã loại sách:");
		JLabel labelName = new JLabel("Tên loại sách:");
		
		labelId.setFont(font2);
		labelName.setFont(font2);
		
		jTextId = new JTextField();
		jTextId.setFont(font2);
		jTextName = new JTextField();
		jTextName.setFont(font2);
		
		JButton buttonSearch = new JButton("Tìm kiếm");
		buttonSearch.setFont(font2);
		//buttonSearch.addActionListener(action);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 10, 10, 0);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		panelSearch.add(labelId, c);
		c.gridy = 1;
		panelSearch.add(labelName, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.BOTH;
		panelSearch.add(jTextId, c);
		c.gridy = 1;
		panelSearch.add(jTextName, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panelSearch.add(buttonSearch, c);

		this.getContentPane().add(panelSearch, BorderLayout.NORTH);

		JPanel panelInfor = new JPanel();
		panelInfor.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Danh sách loại sách",
				TitledBorder.LEADING, TitledBorder.TOP, font1, Color.BLACK));
		panelInfor.setLayout(new BorderLayout());

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã loại sách", "Tên loại sách", "Số lượng"}));
		table.setFont(font2);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPane = new JScrollPane(table);
		panelInfor.add(scrollPane);
		this.getContentPane().add(panelInfor, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));

		JButton buttonAdd = new JButton("Thêm");
		buttonAdd.setFont(font2);
		panelButtons.add(buttonAdd);
		//buttonAdd.addActionListener(action);

		JButton buttonUpdate = new JButton("Sửa");
		buttonUpdate.setFont(font2);
		panelButtons.add(buttonUpdate);
		//buttonUpdate.addActionListener(action);

		JButton buttonDelete = new JButton("Xóa");
		buttonDelete.setFont(font2);
		panelButtons.add(buttonDelete);
		//buttonDelete.addActionListener(action);
		this.getContentPane().add(panelButtons, BorderLayout.SOUTH);

		this.setTitle("Quản lý sách");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setVisible(true);
	}

	public void showListCtg(ArrayList<CategoryModel> lisCtg) {
		tableModel.setRowCount(0);
		for (CategoryModel ctg : lisCtg) {
			tableModel.addRow(new Object[] { ctg.getCategoryId(), ctg.getCategoryName() });
		}
		tableModel.fireTableDataChanged();
	}
//
//	public static void addBookToTable(BookModel book) {
//		tableModel.addRow(new Object[] { book.getBookId(), book.getBookName(), book.getCategory().getCategoryName(),
//				book.getAuthor(), book.getPublisher(), book.getPrice(), book.getAmount() });
//		tableModel.fireTableDataChanged();
//	}
//
//	public void searchBook() {
//		String id = this.jTextId.getText();
//		String name = this.jTextName.getText();
//		int ctg = this.jCbbCtg.getSelectedIndex();
//		String author = this.jTextAuthor.getText();
//		String publisher = this.jTextPublisher.getText();
//
//		Set<BookModel> bookNotSatisfied = new HashSet<>();
//		if (id.trim().length() > 0) {
//			BookModel b_id = BookModel.getBookById(id);
//			for (BookModel book : BookModel.getAllBook()) {
//				if (!book.equals(b_id)) {
//					bookNotSatisfied.add(book);
//				}
//			}
//		}
//		if (name.trim().length() > 0) {
//			ArrayList<BookModel> b_name = BookModel.getBookName(name);
//			for (BookModel book : BookModel.getAllBook()) {
//				if (!b_name.contains(book)) {
//					bookNotSatisfied.add(book);
//				}
//			}
//		}
//		if (ctg > 0) {
//			ArrayList<BookModel> b_ctg = BookModel.getBookByCtg(ctg);
//			for (BookModel book : BookModel.getAllBook()) {
//				if (!b_ctg.contains(book)) {
//					bookNotSatisfied.add(book);
//				}
//			}
//		}
//		if (author.trim().length() > 0) {
//			ArrayList<BookModel> b_au = BookModel.getBookByAuthor(author);
//			for (BookModel book : BookModel.getAllBook()) {
//				if (!b_au.contains(book)) {
//					bookNotSatisfied.add(book);
//				}
//			}
//		}
//		if (publisher.trim().length() > 0) {
//			ArrayList<BookModel> b_pub = BookModel.getBookByPublisher(publisher);
//			for (BookModel book : BookModel.getAllBook()) {
//				if (!b_pub.contains(book)) {
//					bookNotSatisfied.add(book);
//				}
//			}
//		}
//		
//		ArrayList<BookModel> bookSatisfied = new ArrayList<>();
//		for (BookModel book : BookModel.getAllBook()) {
//			if (!bookNotSatisfied.contains(book)) {
//				bookSatisfied.add(book);
//			}
//		}
//		this.showListBook(bookSatisfied);
//	}
}
