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

	public Person(int id, String firstName, String name, String address) {
		this._id = id;
		this._firstName = firstName;
		this._name = name;
		this._address = address;
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

		try

		{
			if (getId() == -1) {
				String insertSQL = "INSERT INTO person(name, address) VALUES (?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und fC<hre Anfrage aus
				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.executeUpdate();

				// Hole die Id des engefC<gten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_id = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE person SET name = ?, address = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setInt(5, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (

		SQLException e)
		{
			e.printStackTrace();
		}
	}
}
