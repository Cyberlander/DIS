package com.felixsoenke;

public class LogEntry
{
	private int lsn;
	private int taid;
	private int pageid;
	private String data;
	
	public LogEntry( int lsn, int taid, int pageid, String data ){
		this.lsn = lsn;
		this.taid = taid;
		this.pageid = pageid;
		this.data = data;
	}
	
	public int getLSN(){
		return lsn;
	}
	public int getTaid(){
		return taid;
	}
	public int getPageId(){
		return pageid;
	}
	public String getData(){
		return data;
	}
	

}
