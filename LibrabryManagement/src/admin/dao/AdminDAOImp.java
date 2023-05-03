package admin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.model.AdminModel;
import book.model.BookModel;
import category.model.CategoryModel;
import database.JDBCUtil;

public class AdminDAOImp implements AdminDAOInterface<AdminModel>{

	public static AdminDAOImp getInstance() {
		return new AdminDAOImp();

	}
	@Override
	public void insert(AdminModel t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "INSERT INTO Admin" + " VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, t.getName());
			pstm.setDate(2, t.getDateOfBirth());
			pstm.setString(3, t.getGender());
			pstm.setString(4, t.getAddress());
			pstm.setString(5, t.getPhoneNumber());
			pstm.setString(6, t.getAccountName());
			pstm.setString(7, t.getPassword());
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
	public void update(AdminModel t) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "UPDATE Admin"
					+ " SET name = ?, dateOfBirth = ?,gender = ?, address = ?,phoneNumber = ?,  accountName = ?,  password = ?"
					+ " WHERE adminId = ?";
			pstm = conn.prepareStatement(sql);

			
			pstm.setString(1, t.getName());
			pstm.setDate(2, t.getDateOfBirth());
			pstm.setString(3, t.getGender());
			pstm.setString(4, t.getAddress());
			pstm.setString(5, t.getPhoneNumber());
			pstm.setString(6, t.getAccountName());
			pstm.setString(7, t.getPassword());
			pstm.setInt(8, t.getAdminId());
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

	

	public AdminModel selectById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		AdminModel ad = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM Admin WHERE adminId = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int adminId = rs.getInt("adminId");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String accountName = rs.getString("accountName");
				String password = rs.getString("password");
				
				ad = new AdminModel(name, dateOfBirth, gender, address, phoneNumber, adminId, accountName, password);
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
		return ad;
	}
	@Override
	public ArrayList<AdminModel> selectByName(String n) {
		ArrayList<AdminModel> dataList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM Admin WHERE name LIKE ?";

			pstm = conn.prepareStatement(sql);
			pstm.setNString(1, "%" + n + "%");

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int adminId = rs.getInt("adminId");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String accountName = rs.getString("accountName");
				String password = rs.getString("password");
				
				AdminModel ad = new AdminModel(name, dateOfBirth, gender, address, phoneNumber, adminId, accountName, password);
				dataList.add(ad);
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
	public AdminModel selectByAccAndPw(String ac, String pw) {
		Connection conn = null;
		PreparedStatement pstm = null;
		AdminModel ad = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM Admin WHERE accountName=? AND password=?";
			pstm = conn.prepareStatement(sql);
			pstm.setNString(1, "%" + "%");
			pstm.setString(1, ac);
			pstm.setString(2, pw);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int adminId = rs.getInt("adminId");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String accountName = rs.getString("accountName");
				String password = rs.getString("password");
				
				ad = new AdminModel(name, dateOfBirth, gender, address, phoneNumber, adminId, accountName, password);
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
		return ad;
	}

}
