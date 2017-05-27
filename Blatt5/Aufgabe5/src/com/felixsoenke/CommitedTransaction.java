package com.felixsoenke;

import java.util.*;

public class CommitedTransaction
{
	private int lsn;
	private Map<Integer, String> bufferedData;
	
	public CommitedTransaction() {
		bufferedData = new HashMap<>();
	}
	
	public void addBufferedData( int pageid, String data) {
		bufferedData.put( pageid, data );
	}
	
	

}
