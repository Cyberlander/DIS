package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;

import com.exercise.tables.ArticleIdTable.Entry;

public class FactTable
{
	List<Entry> entries = null;
	
	public FactTable() {
		entries = new ArrayList<>();
	}
	
	public List<Entry> getEntryList(){
		return this.entries;
	}
	
	public void addEntry( Entry entry ){
		entries.add( entry );
	}
	
	public class Entry {
		
		int _taid;
		int _articleId;
		int _productGroupId;
		int _productFamilyId;
		int _productCategoryId;
		int _shopId;
		int _stadtId;
		int _regionId;
		int _landId;
		double _sales;
		
		
		public void setTaid( int taid ){
			this._taid = taid;
		}
		public int getTaid(){
			return this._taid;
		}
		
		public void setArticleId( int articleId ){
			this._articleId = articleId;
		}
		public int getArticleId(){
			return this._articleId;
		}
		
		public void setProductGroupId( int productGroupId ){
			this._productGroupId = productGroupId;
		}
		public int getProductGroupId(){
			return this._productGroupId;
		}
		
		public void setProductFamilyId( int productFamilyId ){
			this._productFamilyId = productFamilyId;
		}
		public int getProductFamilyId(){
			return this._productFamilyId;
		}
		
		public void setProductCategoryId( int productCategoryId ){
			this._productCategoryId = productCategoryId;
		}
		public int getProductCategoryId(){
			return this._productCategoryId;
		}
		
		public void setShopId( int shopId ){
			this._shopId = shopId;
		}
		public int getShopid(){
			return this._shopId;
		}
		
		public void setStadtId( int stadtId ){
			this._stadtId = stadtId;
		}
		public int getStadtId(){
			return this._stadtId;
		}		
		
		public void setRegionId( int regionId ){
			this._regionId = regionId;
		}
		public int getRegionId(){
			return this._regionId;
		}
		
		public void setLandId( int landId ){
			this._landId = landId;
		}
		public int getLandId(){
			return this._landId;
		}
		
		public void setSales( double sales ){
			this._sales = sales;
		}
		public double getSales(){
			return this._sales;
		}
		
		
		
		
		
	}
}
