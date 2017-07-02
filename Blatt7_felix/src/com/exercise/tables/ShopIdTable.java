package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;

public class ShopIdTable
{
	List<Entry> entries = null;
	
	public ShopIdTable() {
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
		int _stadtId;
		String _name;
		
		
		public Entry( int id, int stadtId, String name){
			this._id = id;
			this._stadtId = stadtId;
			this._name = name;
			
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getStadtId(){
			return this._stadtId;
		}
		
		public String getName(){
			return this._name;
		}
		
	}
}
