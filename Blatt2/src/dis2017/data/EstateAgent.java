package dis2017.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dis2017.data.DB2ConnectionManager;

/**
 * Makler-Bean
 * 
 * Beispiel-Tabelle: CREATE TABLE makler(id INTEGER NOT NULL GENERATED ALWAYS AS
 * IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY KEY, name
 * varchar(255), address varchar(255), login varchar(40) UNIQUE, password
 * varchar(40));
 */
public class EstateAgent {
	private int id = -1;
	private String name;
	private String address;
	private String login;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Lädt einen Makler aus der Datenbank
	 * 
	 * @param id
	 *            ID des zu ladenden Maklers
	 * @return Makler-Instanz
	 */
	public static EstateAgent load(int id) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {

			String selectSQL = "SELECT * FROM estate_agent WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				EstateAgent ts = new EstateAgent();
				System.out.println(id);
				ts.setId(id);
				ts.setName(rs.getString("name"));
				ts.setAddress(rs.getString("address"));
				ts.setLogin(rs.getString("login"));
				ts.setPassword(rs.getString("password"));

				rs.close();
				pstmt.close();
				return ts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean authenticate() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			String selectSQL = "SELECT * FROM estate_agent WHERE login = ? AND password = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, getLogin());
			pstmt.setString(2, getPassword());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("id"));
				setName(rs.getString("name"));
				setAddress(rs.getString("address"));

				rs.close();
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Speichert den Makler in der Datenbank. Ist noch keine ID vergeben worden,
	 * wird die generierte Id von DB2 geholt und dem Model übergeben.
	 */
	public void save() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			if (getId() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit spC$ter generierte IDs zurC<ckgeliefert werden!
				String insertSQL = "INSERT INTO estate_agent(name, address, login, password) VALUES (?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und fC<hre Anfrage aus
				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setString(3, getLogin());
				pstmt.setString(4, getPassword());
				pstmt.executeUpdate();

				// Hole die Id des engefC<gten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					setId(rs.getInt(1));
				}

				rs.close();
				pstmt.close();
			} else {
				String updateSQL = "UPDATE estate_agent SET name = ?, address = ?, login = ?, password = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);
				
				System.out.println("id: " + getId());

				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setString(3, getLogin());
				pstmt.setString(4, getPassword());
				pstmt.setInt(5, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String sql = "DELETE FROM estate_agent WHERE name=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, getName());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<EstateAgent> getEstateAgents() throws SQLException {
		List<EstateAgent> list = new ArrayList<>();
		String sql = "SELECT * FROM estate_agent";

		Connection con = DB2ConnectionManager.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet r = statement.executeQuery(sql);
		while (r.next()) {
			EstateAgent estateAgent = new EstateAgent();
			estateAgent.setName(r.getString("name"));
			estateAgent.setAddress(r.getString("address"));
			estateAgent.setLogin(r.getString("login"));
			estateAgent.setPassword(r.getString("password"));

			list.add(estateAgent);
		}
		return list;
	}
}
