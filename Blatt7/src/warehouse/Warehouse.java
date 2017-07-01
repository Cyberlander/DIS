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
			String insertSQL = "INSERT INTO fact_sales_1(article_id,country_id,product_category_id,product_family_id,product_group_id,region_id,shop_id,city_id,date,unit_sold) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

			for (Fact fact : facts) {
				pstmt.setInt(1, fact.getArticleId());
				pstmt.setInt(2, fact.getCountryId());
				pstmt.setInt(3, fact.getProductCategoryId());
				pstmt.setInt(4, fact.getProductFamilyId());
				pstmt.setInt(5, fact.getProductGroupId());
				pstmt.setInt(6, fact.getRegionId());
				pstmt.setInt(7, fact.getShopId());
				pstmt.setInt(8, fact.getCityId());
				pstmt.setDate(9, fact.getDate());
				pstmt.setInt(10, fact.getUnitsSold());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
