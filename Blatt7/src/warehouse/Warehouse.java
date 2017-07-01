package warehouse;
import java.sql.Connection;

import data.DB2ConnectionManager;

public class Warehouse {
	public void createTables() {
		Connection connection = DB2ConnectionManager.getInstance().getConnection();
	}
}
