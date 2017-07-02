package com.exercise;

public class CSVEntry
{
	
	private String _date;
	private String _shop;
	private String _article;
	private int _soldCount;
	private double _sales;
	
	public CSVEntry( String date, String shop, String article, int soldCount, double sales ){
		this._date = date;
		this._shop = shop;
		this._article = article;
		this._soldCount = soldCount;
		this._sales = sales;
	}
	
	public String getDate(){
		return this._date;
	}
	
	public String getShop(){
		return this._shop;
	}
	
	public String getArticle(){
		return this._article;
	}
	
	public int getSoldCount(){
		return this._soldCount;
	}
	
	public double getSales(){
		return this._sales;
	}

}
