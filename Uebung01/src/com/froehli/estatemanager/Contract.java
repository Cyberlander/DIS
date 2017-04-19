package com.froehli.estatemanager;

public class Contract
{
	int _contractNo;
	String _date;
	String _place;
	
	public Contract( int contractNo, String date, String place ) {
		this._contractNo = contractNo;
		this._date = date;
		this._place = place;
	}
	
	public int getContractNo() {
		return this._contractNo;
	}
	
	public String getDate() {
		return this._date;
	}
	
	public String getPlace() {
		return this._place;
	}
	

}
