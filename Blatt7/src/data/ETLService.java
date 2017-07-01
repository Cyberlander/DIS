package data;

import java.sql.SQLException;
import java.util.List;

import extract.Article;

public class ETLService {
	private List<Article> _articles;
	private List<String[]> _csvEntries;
	
	public void importCSV() {
		CSVReader reader = new CSVReader("Sales.csv", ";");
		_csvEntries = reader.getEntries();
	}
	
	
	public void importDatabase() {
		try {
			_articles = Article.allArticles();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void transformData() {
		// TODO
	}
	
	public void uploadData() {
		// TODO
	}
}
