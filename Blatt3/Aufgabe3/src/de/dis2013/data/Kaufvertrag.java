package de.dis2013.data;

import de.dis2013.util.Helper;

/**
 * Kaufvertrags-Bean
 */
public class Kaufvertrag extends Vertrag {
	private int anzahlRaten;
	private int ratenzins;
	private Haus haus;
	
	public Kaufvertrag() {
		super();
	}
	
	public int getAnzahlRaten() {
		return anzahlRaten;
	}
	public void setAnzahlRaten(int anzahlRaten) {
		this.anzahlRaten = anzahlRaten;
	}
	public int getRatenzins() {
		return ratenzins;
	}
	public void setRatenzins(int ratenzins) {
		this.ratenzins = ratenzins;
	}
	
	public Haus getHaus() {
		return haus;
	}

	public void setHaus(Haus haus) {
		this.haus = haus;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getAnzahlRaten();
		result = prime * result + getRatenzins();
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Kaufvertrag))
			return false;
	
		Kaufvertrag other = (Kaufvertrag)obj;
	
		if(other.getVertragsnummer() != getVertragsnummer() ||
				!Helper.compareObjects(this.getDatum(), other.getDatum()) ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				other.getAnzahlRaten() != getAnzahlRaten() ||
				other.getRatenzins() != getRatenzins())
		{
			return false;
		}
		
		return true;
	}
}
