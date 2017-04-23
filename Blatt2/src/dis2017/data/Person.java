package dis2017.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Person {
	private int _id = -1;
	private String _firstName;
	private String _name;
	private String _address;

	public int getId() {
		return _id;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getName() {
		return _name;
	}

	public String getAddress() {
		return _address;
	}

	public void save() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			if (getId() == -1) {
				String insertSQL = "INSERT INTO person(name, address) VALUES (?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_id = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE person SET name = ?, address = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setInt(3, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}
}
