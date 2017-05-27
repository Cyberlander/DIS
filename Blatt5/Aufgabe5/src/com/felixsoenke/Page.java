package com.felixsoenke;

public class Page
{
	int _pageId;
	int _lsn;
	String _data;
	
	public Page(){
		
	}
	
	public Page( int pageId, int lsn, String data ) {
		this._pageId = pageId;
		this._lsn = lsn;
		this._data = data;
	}
	
	public void setPageID( int id ){
		this._pageId = id;
	}
	
	public int getPageID(){
		return this._pageId;
	}
	
	public void setLSN( int lsn ) {
		this._lsn = lsn;
	}
	
	public int getLSN(){
		return this._lsn;
	}
	
	public void setData( String data ){
		this._data = data;
	}
	
	public String getData(){
		return this._data;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append( "PAGE-ID: " );
		sb.append( getPageID() );
		sb.append( "\n" );
		sb.append( "LSN    : ");
		sb.append( getLSN() );
		sb.append( "\n" );
		sb.append( "DATA   : " );
		sb.append( getData() );
		sb.append( "\n" );
		
		return sb.toString();
	}

}
