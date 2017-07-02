package com.exercise.tables;

import java.util.*;

public class ArticleIdTable
{
	List<Entry> entries = null;
	
	public ArticleIdTable() {
		entries = new ArrayList<>();
	}
	
	public List<Entry> getEntryList(){
		return this.entries;
	}
	
	public void addEntry( Entry entry ){
		entries.add( entry );
	}
	
	public class Entry {
		
		int _id;
		int _productGroup;
		String _name;
		double _preis;
		
		public Entry( int id, int productGroup, String name, double preis){
			this._id = id;
			this._productGroup = productGroup;
			this._name = name;
			this._preis = preis;
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getProductGroupId(){
			return this._productGroup;
		}
		
		public String getName(){
			return this._name;
		}
		
		public double getPreis(){
			return this._preis;
		}
	}

}
