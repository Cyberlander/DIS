package data;

import java.sql.SQLException;
import java.util.List;

import extract.Article;

public class ETLService {
	public void importCSV() {
		CSVReader reader = new CSVReader("Sales.csv", ";");
		reader.getEntries();
	}
	
	
	public void importDatabase() {
		try {
			List<Article> articles = Article.allArticles();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
