package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;



public class StadtIdTable
{
	List<Entry> entries = null;
	
	public StadtIdTable() {
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
		int _regionId;
		String _name;
		
		
		public Entry( int id, int regionId, String name){
			this._id = id;
			this._regionId = regionId;
			this._name = name;
			
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getRegionId(){
			return this._regionId;
		}
		
		public String getName(){
			return this._name;
		}
		
	}
}
