package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;


public class ProductFamilyIdTable
{
	List<Entry> entries = null;
	
	public ProductFamilyIdTable() {
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
		int _categoryId;
		String _name;
		
		
		public Entry( int id, int categoryId, String name){
			this._id = id;
			this._categoryId = categoryId;
			this._name = name;
			
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getCategoryId(){
			return this._categoryId;
		}
		
		public String getName(){
			return this._name;
		}
		
	}
}
