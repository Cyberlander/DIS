package dis2017.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Estate {
	private int _id = -1;
	private String _city;
	private int _postalCode;
	private String _street;
	private String _streetNumber;
	private int _squareArea;
	private EstateAgent _estateAgent;

	public void setId(int id) {
		_id = id;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setPostalCode(int code) {
		_postalCode = code;
	}

	public void setStreet(String street) {
		_street = street;
	}

	public void setStreetNumber(String streetNumber) {
		_streetNumber = streetNumber;
	}

	public void setSquareArea(int area) {
		_squareArea = area;
	}

	public void setEstateAgent(EstateAgent agent) {
		_estateAgent = agent;
	}

	public int getId() {
		return _id;
	}

	public String getCity() {
		return _city;
	}

	public int getPostalCode() {
		return _postalCode;
	}

	public String getStreet() {
		return _street;
	}

	public String getStreetNumber() {
		return _streetNumber;
	}

	public int getSquareArea() {
		return _squareArea;
	}

	public EstateAgent getEstateAgent() {
		return _estateAgent;
	}

	public static Estate load(int id) {
		try {
			Connection con = DB2ConnectionManager.getInstance().getConnection();

			String selectSQL = "SELECT * FROM estate WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Estate ts = new Estate();
				ts.setId(id);
				ts.setCity(rs.getString("city"));
				ts.setPostalCode(rs.getInt("postal_code"));
				ts.setStreet(rs.getString("street"));
				ts.setStreetNumber(rs.getString("street_number"));
				ts.setSquareArea(rs.getInt("square_area"));
				int estateAgentId = rs.getInt("estate_agent");
				ts.setEstateAgent(EstateAgent.load(estateAgentId));

				rs.close();
				pstmt.close();
				return ts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			con.setAutoCommit(false);
			if (getId() == -1) {
				String insertSQL = "INSERT INTO estate(city,postal_code,street,street_number,square_area,estate_agent) VALUES (?,?,?,?,?,?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, getCity());
				pstmt.setInt(2, getPostalCode());
				pstmt.setString(3, getStreet());
				pstmt.setString(4, getStreetNumber());
				pstmt.setInt(5, getSquareArea());
				pstmt.setInt(6, getEstateAgent().getId());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_id = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE estate SET city = ?, postal_code = ?, street = ?, street_number = ?, square_area = ?, estate_agent = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				pstmt.setString(1, getCity());
				pstmt.setInt(2, getPostalCode());
				pstmt.setString(3, getStreet());
				pstmt.setString(4, getStreetNumber());
				pstmt.setInt(5, getSquareArea());
				pstmt.setInt(6, getEstateAgent().getId());
				pstmt.setInt(7, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String sql = "DELETE FROM estate WHERE id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
