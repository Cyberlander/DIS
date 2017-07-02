package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;

public class RegionIdTable
{
	List<Entry> entries = null;
	
	public RegionIdTable() {
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
		int _landId;
		String _name;
		
		
		public Entry( int id, int landId, String name){
			this._id = id;
			this._landId = landId;
			this._name = name;
			
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getLandId(){
			return this._landId;
		}
		
		public String getName(){
			return this._name;
		}
		
	}
}
