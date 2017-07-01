package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class ProductCategory {
	private int _id;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public static List<ProductCategory> allProductCategories() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.PRODUCTCATEGORYID";

		int productCategoryId;
		String name;

		List<ProductCategory> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				productCategoryId = rs.getInt("PRODUCTCATEGORYID");
				name = rs.getString("NAME");

				ProductCategory productCategory = new ProductCategory();
				productCategory.setId(productCategoryId);
				productCategory.setName(name);
				result.add(productCategory);
			}

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

		return result;
	}
}
