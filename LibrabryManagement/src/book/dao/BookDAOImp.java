package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.model.BookModel;
import category.model.CategoryModel;
import database.JDBCUtil;

public class BookDAOImp implements BookDAOInterface<BookModel> {

	public static BookDAOImp getInstance() {
		return new BookDAOImp();

	}

	@Override
	public void insert(BookModel t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO Book" + " VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, t.getBookId());
			pstm.setString(2, t.getBookName());
			pstm.setInt(3, t.getCategory().getCategoryId());
			pstm.setString(4, t.getAuthor());
			pstm.setString(5, t.getPublisher());
			pstm.setDouble(6, t.getPrice());
			pstm.setInt(7, t.getAmount());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(BookModel t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "UPDATE Book"
					+ " SET bookName = ?, categoryId = ?, author = ?, publisher = ?, price = ?, amount = ?"
					+ " WHERE bookId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, t.getBookName());
			pstm.setInt(2, t.getCategory().getCategoryId());
			pstm.setString(3, t.getAuthor());
			pstm.setString(4, t.getPublisher());
			pstm.setDouble(5, t.getPrice());
			pstm.setInt(6, t.getAmount());
			pstm.setString(7, t.getBookId());
			
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(BookModel t) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM Book"
						+ " WHERE bookId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, t.getBookId());
			
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ArrayList<BookModel> selectAll() {
		ArrayList<BookModel> dataList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";
			pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");

				CategoryModel c = new CategoryModel(categoryId, categoryName);
				BookModel b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
				dataList.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

	@Override
	public BookModel selectById(String id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		BookModel b = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ " WHERE Book.bookId = ?" + "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");
				CategoryModel c = new CategoryModel(categoryId, categoryName);
				b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	@Override
	public ArrayList<BookModel> selectByName(String name) {
		ArrayList<BookModel> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ " WHERE Book.bookName LIKE ?"
					+ "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";

			pstm = conn.prepareStatement(sql);
			pstm.setNString(1, "%" + name + "%");

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");
				CategoryModel c = new CategoryModel(categoryId, categoryName);
				BookModel b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
				dataList.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

	@Override
	public ArrayList<BookModel> selectByAuthor(String au) {
		ArrayList<BookModel> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ " WHERE author LIKE ?" + "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";

			pstm = conn.prepareStatement(sql);
			pstm.setNString(1, "%" + au + "%");

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");

				CategoryModel c = new CategoryModel(categoryId, categoryName);
				BookModel b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
				dataList.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

	@Override
	public ArrayList<BookModel> selectByPublisher(String pub) {
		ArrayList<BookModel> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ " WHERE publisher LIKE ?" + "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";

			pstm = conn.prepareStatement(sql);
			pstm.setNString(1, "%" + pub + "%");

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");

				CategoryModel c = new CategoryModel(categoryId, categoryName);
				BookModel b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
				dataList.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

	@Override
	public ArrayList<BookModel> selectByCtg(int ctgId) {
		ArrayList<BookModel> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT Book.*, Category.categoryName,"
					+ " Book.amount - ISNULL(SUM(CASE WHEN LendingSlip.status = N'Đã trả' THEN 0 "
					+ " ELSE LendingSlipDetail.amount END), 0) AS remainingAmount" + "	FROM Book"
					+ "	LEFT JOIN Category ON Category.categoryId = Book.categoryId"
					+ "	LEFT JOIN LendingSlipDetail ON Book.bookId = LendingSlipDetail.bookId"
					+ "	LEFT JOIN LendingSlip ON LendingSlipDetail.lendingSlipId = LendingSlip.lendingSlipId"
					+ " WHERE Book.categoryId = ?"
					+ "	GROUP BY Book.bookId, Book.bookName, Book.author, Book.publisher,"
					+ " Book.price, Book.amount, Book.categoryId, Category.categoryName";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ctgId);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String bookId = rs.getString("bookId");
				String bookName = rs.getString("bookName");
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				double price = rs.getDouble("price");
				int amount = rs.getInt("amount");
				int remainingAmount = rs.getInt("remainingAmount");

				CategoryModel c = new CategoryModel(categoryId, categoryName);
				BookModel b = new BookModel(bookId, bookName, c, author, publisher, price, amount, remainingAmount);
				dataList.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dataList;
	}

}
