package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class ProductFamily {
	private int _id;
	private int _productCategoryId;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setProductCategoryId(int id) {
		_productCategoryId = id;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public int getProductFamilyId() {
		return _productCategoryId;
	}

	public static List<ProductFamily> allProductFamilies() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.PRODUCTFAMILYID";

		int productFamilyID;
		int productCategoryId;
		String name;

		List<ProductFamily> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				productFamilyID = rs.getInt("PRODUCTFAMILYID");
				productCategoryId = rs.getInt("PRODUCTCATEGORYID");
				name = rs.getString("NAME");

				ProductFamily productFamiliy = new ProductFamily();
				productFamiliy.setId(productFamilyID);
				productFamiliy.setName(name);
				productFamiliy.setProductCategoryId(productCategoryId);
				result.add(productFamiliy);
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
