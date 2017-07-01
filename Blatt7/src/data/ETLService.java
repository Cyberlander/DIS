package data;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import extract.Article;
import extract.City;
import extract.Country;
import extract.ProductCategory;
import extract.ProductFamily;
import extract.ProductGroup;
import extract.Region;
import extract.Shop;
import warehouse.Fact;
import warehouse.Warehouse;

public class ETLService {
	private List<Fact> _facts;
	private List<Article> _articles;
	private List<Country> _countries;
	private List<ProductCategory> _productCategories;
	private List<ProductFamily> _productFamilies;
	private List<ProductGroup> _productGroups;
	private List<Region> _regions;
	private List<Shop> _shops;
	private List<City> _cities;
	private List<String[]> _csvEntries;

	public void importCSV(File file) {
		CSVReader reader = new CSVReader(file.getAbsolutePath(), ";");
		_csvEntries = reader.getEntries();
	}

	public void importDatabase() {
		try {
			_articles = Article.allArticles();
			_countries = Country.allCountries();
			_productCategories = ProductCategory.allProductCategories();
			_productFamilies = ProductFamily.allProductFamilies();
			_productGroups = ProductGroup.allProductGroups();
			_regions = Region.allRegions();
			_shops = Shop.allShops();
			_cities = City.allCities();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void extractFacts() {
		_facts = new ArrayList<Fact>();

		for (String[] column : _csvEntries) {
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
		warehouse.uploadFacts(_facts);
	}

	private Article findArticle(String name) {
		for (Article article : _articles) {
			if (article.getName().equals(name)) {
				return article;
			}
		}

		return null;
	}
}
