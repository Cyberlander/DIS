package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryIdTable
{
	List<Entry> entries = null;
		
	public ProductCategoryIdTable() {
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
		String _name;
			
			
		public Entry( int id, String name){
			this._id = id;
			this._name = name;
		}
			
		public int getId() {
			return this._id;
		}			
			
		public String getName(){
			return this._name;
		}
		
	}
			
		
}
