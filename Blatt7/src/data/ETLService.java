package data;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			String dateStr = column[0];
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			Date date = null;
			try {
				java.util.Date parsed = format.parse(dateStr);
				date = new Date(parsed.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String shopName = column[1];
			String articleName = column[2];
			int soldUnits = Integer.parseInt(column[3]);
			String volumeStr = column[4].replace(",", ".");
			float volume = Float.parseFloat(volumeStr);

			Article article = findArticle(articleName);
			if (article == null) {
				System.out.println("Article name does not exist: " + articleName);
				continue;
			}

			Shop shop = findShop(shopName);
			if (shop == null) {
				System.out.println("Shop does not exist: " + shopName);
				continue;
			}
			
			ProductGroup productGroup = findProductGroupById(article.getProductGroupID());
			if (productGroup == null) {
				System.err.println("Product group does not exist: " + article.getProductGroupID());
				continue;
			}
			
			ProductFamily productFamily = findProductFamilyById(productGroup.getProductFamilyId());
			if (productFamily == null) {
				System.err.println("Product family does not exist: " + productGroup.getProductFamilyId());
				continue;
			}
			
			City city = findCityById(shop.getCityId());
			if (city == null) {
				System.err.println("City does not exist: " + shop.getCityId());
				continue;
			}
			
			Region region = findRegionById(city.getRegionId());
			if (region == null) {
				System.err.println("Region does not exist: " + city.getRegionId());
				continue;
			}
			
			int articleId = article.getID();
			int countryId = region.getCountryId();
			int productCategoryId = productFamily.getProductCategoryId();
			int productFamilyId = productGroup.getProductFamilyId();
			int productGroupId = productGroup.getId();
			int regionId = region.getId();
			int shopId = shop.getId();
			int cityId = city.getId();
			
			Fact fact = new Fact();
			fact.setArticleId(articleId);
			fact.setCountryId(countryId);
			fact.setProductCategoryId(productCategoryId);
			fact.setProductFamilyId(productFamilyId);
			fact.setProductGroupId(productGroupId);
			fact.setRegionId(regionId);
			fact.setShopId(shopId);
			fact.setCityId(cityId);
			fact.setDate(date);
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

	private Shop findShop(String name) {
		for (Shop shop : _shops) {
			if (shop.getName().equals(name)) {
				return shop;
			}
		}

		return null;
	}

	private ProductGroup findProductGroupById(int id) {
		for (ProductGroup productGroup : _productGroups) {
			if (productGroup.getId() == id) {
				return productGroup;
			}
		}

		return null;
	}
	
	private ProductFamily findProductFamilyById(int id) {
		for (ProductFamily productFamily : _productFamilies) {
			if (productFamily.getId() == id) {
				return productFamily;
			}
		}

		return null;
	}

	private City findCityById(int id) {
		for (City city : _cities) {
			if (city.getId() == id) {
				return city;
			}
		}

		return null;
	}

	private Region findRegionById(int id) {
		for (Region region : _regions) {
			if (region.getId() == id) {
				return region;
			}
		}

		return null;
	}
}
