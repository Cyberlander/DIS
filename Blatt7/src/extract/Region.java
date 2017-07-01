package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class Region {
	private int _id;
	private int _countryId;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setCountryId(int id) {
		_countryId = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getId() {
		return _id;
	}

	public int getCountryId() {
		return _countryId;
	}

	public String getName() {
		return _name;
	}

	public static List<Region> allRegions() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.REGIONID";

		int regionId;
		int landId;
		String name;

		List<Region> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				regionId = rs.getInt("REGIONID");
				landId = rs.getInt("LANDID");
				name = rs.getString("NAME");

				Region land = new Region();
				land.setId(regionId);
				land.setCountryId(landId);
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
