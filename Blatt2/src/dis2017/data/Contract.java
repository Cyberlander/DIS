package dis2017.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Contract {
	private int _contract_no = -1;
	private String _date;
	private String _place;

	private void setContractNo(int number) {
		_contract_no = number;
	}

	public void setDate(String date) {
		_date = date;
	}

	public void setPlace(String place) {
		_place = place;
	}

	public int getContractNo() {
		return _contract_no;
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
			con.setAutoCommit(false);
			if (getContractNo() == -1) {
				String insertSQL = "INSERT INTO contract(date, place) VALUES (?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, getDate());
				pstmt.setString(2, getPlace());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					_contract_no = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE contract SET date = ?, place = ? WHERE contract_no = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				pstmt.setString(1, getDate());
				pstmt.setString(2, getPlace());
				pstmt.setInt(3, getContractNo());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Contract> getContracts() {
		List<Contract> list = new ArrayList<>();
		String sql = "SELECT * FROM contract";

		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet r = statement.executeQuery(sql);
			while (r.next()) {
				Contract contract = new Contract();
				contract.setContractNo(r.getInt("contract_no"));
				contract.setDate(r.getString("date"));
				contract.setPlace(r.getString("place"));
				list.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void delete() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String sql = "DELETE FROM contract WHERE contract_no=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, getContractNo());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
