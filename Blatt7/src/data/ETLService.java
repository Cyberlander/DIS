package data;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import extract.Article;
import warehouse.Fact;
import warehouse.Warehouse;

public class ETLService {
	private List<Fact> _facts;
	private List<Article> _articles;
	private List<String[]> _csvEntries;

	public void importCSV(File file) {
		CSVReader reader = new CSVReader(file.getAbsolutePath(), ";");
		_csvEntries = reader.getEntries();
	}

	public void importDatabase() {
		try {
			_articles = Article.allArticles();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void extractFacts() {
		_facts = new ArrayList<Fact>();
		
		for(String[] column : _csvEntries) {
			String date = column[0];
			String storeName = column[1];
			String articleName = column[2];
			int soldUnits = Integer.parseInt(column[3]);
			String volumeStr = column[4].replace(",", ".");
			float volume = Float.parseFloat(volumeStr);
			
			Article article = findArticle(articleName);
			if (article == null) {
				System.out.println("Article name does not exist: " + articleName);
				continue;
			}
			
			Fact fact = new Fact();
			fact.setArticleId(article.getID());
			fact.setUnitsSold(soldUnits);
			
			_facts.add(fact);
		}
	}

	public void uploadData() {
		Warehouse warehouse = new Warehouse();
		// TODO upload data
	}
	
	private Article findArticle(String name) {
		for(Article article : _articles) {
			if (article.getName().equals(name)) {
				return article;
			}
		}
		
		return null;
	}
}
