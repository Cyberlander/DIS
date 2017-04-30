package de.dis2013.data;

import de.dis2013.util.Helper;

/**
 * Haus-Bean
 */
public class Haus extends Immobilie {
	private int stockwerke;
	private int kaufpreis;
	private boolean garten;
	
	public Haus() {
		super();
	}
	
	public int getStockwerke() {
		return stockwerke;
	}
	public void setStockwerke(int stockwerke) {
		this.stockwerke = stockwerke;
	}
	public int getKaufpreis() {
		return kaufpreis;
	}
	public void setKaufpreis(int kaufpreis) {
		this.kaufpreis = kaufpreis;
	}
	public boolean isGarten() {
		return garten;
	}
	public void setGarten(boolean garten) {
		this.garten = garten;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getStockwerke();
		result = prime * result + getKaufpreis();
		result = prime * result + ((isGarten()) ? 1 : 0);
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Haus))
			return false;
	
		Haus other = (Haus)obj;
	
		if(other.getId() != getId() ||
				other.getPlz() != getPlz() ||
				other.getFlaeche() != getFlaeche() ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				!Helper.compareObjects(this.getStrasse(), other.getStrasse()) ||
				!Helper.compareObjects(this.getHausnummer(), other.getHausnummer()) ||
				getStockwerke() != other.getStockwerke() ||
				getKaufpreis() != other.getKaufpreis() ||
				isGarten() != other.isGarten())
		{
			return false;
		}
		
		return true;
	}
}
