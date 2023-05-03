package student.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.JDBCUtil;
import student.model.StudentModel;

public class StudentDAOImp implements StudentDAOInterface<StudentModel>{

	public static StudentDAOImp getInstance() {
		return new StudentDAOImp();

	}
	

	@Override
	public StudentModel selectById(String id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StudentModel s = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM Student WHERE studentId = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String studentId = rs.getString("studentId");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String className = rs.getString("className");
				String faculty = rs.getString("faculty");
				
				s = new StudentModel(name, dateOfBirth, gender, address, phoneNumber, studentId, className, faculty);
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
		return s;
	}

}
