package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class ProductGroup {
	private int _id;
	private int _productFamilyId;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setProductFamilyId(int id) {
		_productFamilyId = id;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public int getProductFamilyId() {
		return _productFamilyId;
	}

	public static List<ProductGroup> allProductGroups() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.PRODUCTGROUPID";

		int productGroupId;
		int productFamilyId;
		String name;

		List<ProductGroup> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				productGroupId = rs.getInt("PRODUCTGROUPID");
				productFamilyId = rs.getInt("PRODUCTFAMILYID");
				name = rs.getString("NAME");

				ProductGroup productGroup = new ProductGroup();
				productGroup.setId(productGroupId);
				productGroup.setName(name);
				productGroup.setProductFamilyId(productFamilyId);
				result.add(productGroup);
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
