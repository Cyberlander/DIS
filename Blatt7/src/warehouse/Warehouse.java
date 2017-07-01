package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import data.DB2ConnectionManager;

public class Warehouse {
	public void uploadFacts(List<Fact> facts) {
		Connection connection = DB2ConnectionManager.getInstance().getConnection();
		
		try {
			String insertSQL = "INSERT INTO fact_sales_1(article_id,unit_sold) VALUES (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			
			for(Fact fact : facts) {
				pstmt.setInt(1, fact.getArticleId());
				pstmt.setInt(2, fact.getUnitsSold());
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
