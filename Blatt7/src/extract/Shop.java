package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class Shop {
	private int _id;
	private int _cityId;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setCityId(int id) {
		_cityId = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getId() {
		return _id;
	}

	public int getCityId() {
		return _cityId;
	}

	public String getName() {
		return _name;
	}

	public static List<Shop> allShops() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.SHOPID";

		int shopId;
		int cityId;
		String name;

		List<Shop> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				shopId = rs.getInt("SHOPID");
				cityId = rs.getInt("STADTID");
				name = rs.getString("NAME");

				Shop land = new Shop();
				land.setId(shopId);
				land.setCityId(cityId);
				land.setName(name);
				result.add(land);
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
