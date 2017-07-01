package extract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DB2ConnectionManager;

public class Article {
	private int _id;
	private String _name;
	private float _price;
	private int _productGroupID;
	
	public void setID(int id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setPrice(float price) {
		_price = price;
	}
	
	public void setProductGroupID(int id) {
		_productGroupID = id;
	}

	public int getID() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public float getPrice() {
		return _price;
	}
	
	public int getProductGroupID() {
		return _productGroupID;
	}
	
	
	public static List<Article> allArticles() throws SQLException {
        Connection con = DB2ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM DB2INST1.ARTICLEID";

        int articleID;
        int productGroupID;
        String name;
        float price;

        List<Article> result = new ArrayList<Article>();

        try {
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                articleID = rs.getInt("ARTICLEID");
                productGroupID = rs.getInt("PRODUCTGROUPID");
                name = rs.getString("NAME");
                price = rs.getFloat("PREIS");

                Article article = new Article();
                article.setID(articleID);
                article.setName(name);
                article.setProductGroupID(productGroupID);
                article.setPrice(price);
                result.add(article);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

        return result;
    }
}
