package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class City {
	private int _id;
	private int _regionId;
	private String _name;

	public void setId(int id) {
		_id = id;
	}

	public void setRegionId(int id) {
		_regionId = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getId() {
		return _id;
	}

	public int getRegionId() {
		return _regionId;
	}

	public String getName() {
		return _name;
	}

	public static List<City> allCities() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.STADTID";

		int stadtId;
		int regionId;
		String name;

		List<City> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				stadtId = rs.getInt("STADTID");
				regionId = rs.getInt("REGIONID");
				name = rs.getString("NAME");

				City land = new City();
				land.setId(stadtId);
				land.setRegionId(regionId);
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
