package category.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import category.model.CategoryModel;
import database.JDBCUtil;

public class CategoryDAOImp implements CategoryDAOInterface<CategoryModel> {

	public static CategoryDAOImp getInstance() {
		return new CategoryDAOImp();

	}

	@Override
	public void insert(CategoryModel t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(CategoryModel t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(CategoryModel t) {
		// TODO Auto-generated method stub
	}

	@Override
	public ArrayList<CategoryModel> selectAll() {
		ArrayList<CategoryModel> dataList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM Category";
			pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				CategoryModel c = new CategoryModel(categoryId, categoryName);
				dataList.add(c);
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
	public CategoryModel selectById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		CategoryModel c = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "SELECT * FROM Category WHERE categoryId = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				int categoryId = rs.getInt("categoryId");
				String categoryName = rs.getString("categoryName");
				
				c = new CategoryModel(categoryId, categoryName);
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
		return c;
	}

	
	@Override
	public ArrayList<CategoryModel> selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
