package warehouse;

import java.sql.Date;

public class Fact {
	private int _articleId;

	private int _countryId;
	private int _productCategoryId;
	private int _productFamilyId;
	private int _productGroupId;
	private int _regionId;
	private int _shopId;
	private int _cityId;

	private int _unitsSold;
	private Date _date;

	public void setArticleId(int id) {
		_articleId = id;
	}

	public void setCountryId(int id) {
		_countryId = id;
	}

	public void setProductCategoryId(int id) {
		_productCategoryId = id;
	}

	public void setProductFamilyId(int id) {
		_productFamilyId = id;
	}

	public void setProductGroupId(int id) {
		_productGroupId = id;
	}

	public void setRegionId(int id) {
		_regionId = id;
	}

	public void setShopId(int id) {
		_shopId = id;
	}

	public void setCityId(int id) {
		_cityId = id;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public void setUnitsSold(int units) {
		_unitsSold = units;
	}

	public int getArticleId() {
		return _articleId;
	}

	public int getCountryId() {
		return _countryId;
	}

	public int getProductCategoryId() {
		return _productCategoryId;
	}

	public int getProductFamilyId() {
		return _productFamilyId;
	}

	public int getProductGroupId() {
		return _productGroupId;
	}

	public int getRegionId() {
		return _regionId;
	}

	public int getShopId() {
		return _shopId;
	}

	public int getCityId() {
		return _cityId;
	}

	public Date getDate() {
		return _date;
	}

	public int getUnitsSold() {
		return _unitsSold;
	}
}
