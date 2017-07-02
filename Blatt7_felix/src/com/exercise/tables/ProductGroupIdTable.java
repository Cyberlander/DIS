package com.exercise.tables;

import java.util.ArrayList;
import java.util.List;


public class ProductGroupIdTable
{
	List<Entry> entries = null;
	
	public ProductGroupIdTable() {
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
		int _familyId;
		String _name;
		
		
		public Entry( int id, int familyId, String name){
			this._id = id;
			this._familyId = familyId;
			this._name = name;
			
		}
		
		public int getId() {
			return this._id;
		}
		
		public int getFamilyId(){
			return this._familyId;
		}
		
		public String getName(){
			return this._name;
		}
		
	}
}
