package dis2017.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Contract {
	private int _id = -1;
	private String _date;
	private String _place;

	private void setId(int id) {
		_id = id;
	}

	private void setDate(String date) {
		_date = date;
	}

	private void setPlace(String place) {
		_place = place;
	}

	public int getId() {
		return _id;
	}

	public String getDate() {
		return _date;
	}

	public String getPlace() {
		return _place;
	}

	public void save() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			if (getId() == -1) {
				String insertSQL = "INSERT INTO contract(date, place) VALUES (?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, getDate());
				pstmt.setString(2, getPlace());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_id = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE contract SET date = ?, place = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				pstmt.setString(1, getDate());
				pstmt.setString(2, getPlace());
				pstmt.setInt(3, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Contract> getContracts(Connection connection) {
		List<Contract> list = new ArrayList<>();
		String sql = "SELECT * FROM contract";

		try {
			Statement statement = connection.createStatement();
			ResultSet r = statement.executeQuery(sql);
			while (r.next()) {
				Contract contract = new Contract();
				contract.setId(r.getInt("id"));
				contract.setDate(r.getString("date"));
				contract.setPlace(r.getString("place"));
				list.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
