package lendingslip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.model.BookModel;
import database.JDBCUtil;
import lendingslip.model.LendingSlip;
import lendingslip.model.LendingSlipDetail;

public class LDetailDAOImp implements LDetailDAOInterface<LendingSlipDetail> {

	public static LDetailDAOImp getInstance() {
		return new LDetailDAOImp();

	}

	@Override
	public void insert(LendingSlipDetail t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO LendingSlipDetail" + " VALUES(?, ?, ?)";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getLendingSlip().getLendingSlipId());
			pstm.setString(2, t.getBook().getBookId());
			pstm.setInt(3, t.getAmount());

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
	public void update(LendingSlipDetail t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "UPDATE LendingSlipDetail" + " SET amount = ?" + " WHERE lendingSlipId = ? AND bookId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getAmount());
			pstm.setInt(2, t.getLendingSlip().getLendingSlipId());
			pstm.setString(3, t.getBook().getBookId());

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
	
//	@Override
//	public void updateByLSId(LendingSlipDetail t) {
//		Connection conn = null;
//		PreparedStatement pstm = null;
//		try {
//			conn = JDBCUtil.getConnection();
//
//			String sql = "UPDATE LendingSlipDetail" + " SET amount = ?, bookId = ?" + " WHERE lendingSlipId = ? ";
//			pstm = conn.prepareStatement(sql);
//
//			pstm.setInt(1, t.getAmount());
//			pstm.setString(2, t.getBook().getBookId());
//			pstm.setInt(3, t.getLendingSlip().getLendingSlipId());
//
//			pstm.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (pstm != null) {
//				try {
//					pstm.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	@Override
	public void delete(LendingSlipDetail t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM LendingSlipDetail" + " WHERE lendingSlipId = ? AND bookId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getLendingSlip().getLendingSlipId());
			pstm.setString(2, t.getBook().getBookId());

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
	public ArrayList<LendingSlipDetail> selectByLSId(int id) {
		ArrayList<LendingSlipDetail> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * " + " FROM LendingSlipDetail" + " WHERE lendingSlipId = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				String bookId = rs.getString("bookId");
				int amount = rs.getInt("amount");

				LendingSlipDetail ld = new LendingSlipDetail(LendingSlip.getById(lendingSlipId),
						BookModel.getBookById(bookId), amount);
				dataList.add(ld);
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
	public ArrayList<LendingSlipDetail> selectByBookId(String id) {
		ArrayList<LendingSlipDetail> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * " + " FROM LendingSlipDetail" + " WHERE bookId = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				String bookId = rs.getString("bookId");
				int amount = rs.getInt("amount");

				LendingSlipDetail ld = new LendingSlipDetail(LendingSlip.getById(lendingSlipId),
						BookModel.getBookById(bookId), amount);
				dataList.add(ld);
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
	public LendingSlipDetail selectByPK(int LendId, String bId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		LendingSlipDetail ld = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * " + " FROM LendingSlipDetail" + " WHERE lendingSlipId = ? AND bookId = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, LendId);
			pstm.setString(2, bId);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				String bookId = rs.getString("bookId");
				int amount = rs.getInt("amount");

				ld = new LendingSlipDetail(LendingSlip.getById(lendingSlipId), BookModel.getBookById(bookId), amount);

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
		return ld;
	}

	@Override
	public void deleteById(int LendId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM LendingSlipDetail" + " WHERE lendingSlipId = ? ";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, LendId );
			//pstm.setString(2, t.getBook().getBookId());

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

	

}
