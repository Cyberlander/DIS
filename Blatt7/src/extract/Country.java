package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class Country {
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

	public static List<Country> allCountries() throws SQLException {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM DB2INST1.LANDID";

		int countryId;
		String name;

		List<Country> result = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				countryId = rs.getInt("LANDID");
				name = rs.getString("NAME");

				Country country = new Country();
				country.setId(countryId);
				country.setName(name);
				result.add(country);
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
