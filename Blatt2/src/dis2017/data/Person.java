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

	private void setId(int id) {
		_id = id;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setAddress(String address) {
		_address = address;
	}

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
				String insertSQL = "INSERT INTO person(first_name, name, address) VALUES (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1,  getFirstName());
				pstmt.setString(2, getName());
				pstmt.setString(3, getAddress());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_id = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE person SET first_name = ?, name = ?, address = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				pstmt.setString(1,  getFirstName());
				pstmt.setString(2, getName());
				pstmt.setString(3, getAddress());
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
