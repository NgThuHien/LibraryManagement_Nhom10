package lendingslip.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import admin.model.AdminModel;
import category.model.CategoryModel;
import database.JDBCUtil;
import lendingslip.model.LendingSlip;
import student.model.StudentModel;

public class LendingDAOImp implements LendingDAOInterface<LendingSlip>{
	
	public static LendingDAOImp getInstance() {
		return new LendingDAOImp();

	}

	@Override
	public void insert(LendingSlip t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO LendingSlip" + " VALUES(?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getAdmin().getAdminId());
			pstm.setString(2, t.getStudent().getStudentId());
			pstm.setDate(3, t.getLendDate());
			pstm.setDate(4, t.getDueDate());
			pstm.setDate(5, t.getReturnDate());
			pstm.setString(6, t.getStatus());
			
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
	public void update(LendingSlip t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "UPDATE LendingSlip"
					+ " SET adminId = ?, studentId = ?, lendDate = ?, dueDate = ?, returnDate = ?, status = ?"
					+ " WHERE lendingSlipId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getAdmin().getAdminId());
			pstm.setString(2, t.getStudent().getStudentId());
			pstm.setDate(3, t.getLendDate());
			pstm.setDate(4, t.getDueDate());
			pstm.setDate(5, t.getReturnDate());
			pstm.setString(6, t.getStatus());
			pstm.setInt(7, t.getLendingSlipId());
			
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
	public void delete(LendingSlip t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "DELETE FROM LendingSlip"
						+ " WHERE lendingSlipId = ?";
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, t.getLendingSlipId());
			
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
	public ArrayList<LendingSlip> selectAll() {
		ArrayList<LendingSlip> dataList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT LendingSlip.*, Admin.adminId, Student.studentId"
					+ " FROM LendingSlip INNER JOIN Admin ON LendingSlip.adminId = Admin.adminId"
					+ " INNER JOIN Student ON LendingSlip.studentId = Student.studentId ";
			pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				int adminId = rs.getInt("adminId");
				String studentId = rs.getString("studentId");
				Date lendDate = rs.getDate("lendDate");
				Date dueDate = rs.getDate("dueDate");
				Date returnDate = rs.getDate("returnDate");
				String status = rs.getString("status");

				AdminModel ad = AdminModel.getAdminById(adminId);
				StudentModel std = StudentModel.getStudentById(studentId);
				LendingSlip ls = new LendingSlip(lendingSlipId, ad, std, lendDate, dueDate, returnDate, status);
				dataList.add(ls);
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
	public LendingSlip selectById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		LendingSlip l = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM LendingSlip WHERE lendingSlipId = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				int adminId = rs.getInt("adminId");
				String studentId = rs.getString("studentId");
				Date lendDate = rs.getDate("lendDate");
				Date dueDate = rs.getDate("dueDate");
				Date returnDate = rs.getDate("returnDate");
				String status = rs.getString("status");
				
				AdminModel ad = AdminModel.getAdminById(adminId);
				StudentModel std = StudentModel.getStudentById(studentId);
				l = new LendingSlip(lendingSlipId, ad, std, lendDate, dueDate, returnDate, status);
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
		return l;
	}

	@Override
	public ArrayList<LendingSlip> selectByStudentID(String stdID) {
		ArrayList<LendingSlip> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM LendingSlip WHERE studentId = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, stdID);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				int adminId = rs.getInt("adminId");
				String studentId = rs.getString("studentId");
				Date lendDate = rs.getDate("lendDate");
				Date dueDate = rs.getDate("dueDate");
				Date returnDate = rs.getDate("returnDate");
				String status = rs.getString("status");
				
				AdminModel ad = AdminModel.getAdminById(adminId);
				StudentModel std = StudentModel.getStudentById(studentId);
				LendingSlip l = new LendingSlip(lendingSlipId, ad, std, lendDate, dueDate, returnDate, status);
			dataList.add(l);
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
	public int selectIdMax() {
		int idMax = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = " SELECT MAX(lendingSlipId) AS idMax FROM LendingSlip; ";
			pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				idMax = rs.getInt("idMax");
				
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
		return idMax;
	}

	@Override
	public ArrayList<LendingSlip> selectByStatus(String stt) {
		ArrayList<LendingSlip> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM LendingSlip WHERE status LIKE ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + stt + "%");

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int lendingSlipId = rs.getInt("lendingSlipId");
				int adminId = rs.getInt("adminId");
				String studentId = rs.getString("studentId");
				Date lendDate = rs.getDate("lendDate");
				Date dueDate = rs.getDate("dueDate");
				Date returnDate = rs.getDate("returnDate");
				String status = rs.getString("status");
				
				AdminModel ad = AdminModel.getAdminById(adminId);
				StudentModel std = StudentModel.getStudentById(studentId);
				LendingSlip l = new LendingSlip(lendingSlipId, ad, std, lendDate, dueDate, returnDate, status);
			dataList.add(l);
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
