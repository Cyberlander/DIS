package de.dis2013.data;

import java.util.Date;

import de.dis2013.util.Helper;

/**
 * Mietvertrags-Bean
 */
public class Mietvertrag extends Vertrag {
	private Date mietbeginn;
	private int dauer;
	private int nebenkosten;
	private Wohnung wohnung;

	public Mietvertrag() {
		super();
	}
	
	public Date getMietbeginn() {
		return mietbeginn;
	}
	public void setMietbeginn(Date mietbeginn) {
		this.mietbeginn = mietbeginn;
	}
	public int getDauer() {
		return dauer;
	}
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	public int getNebenkosten() {
		return nebenkosten;
	}
	public void setNebenkosten(int nebenkosten) {
		this.nebenkosten = nebenkosten;
	}
	
	public Wohnung getWohnung() {
		return wohnung;
	}

	public void setWohnung(Wohnung wohnung) {
		this.wohnung = wohnung;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + ((getMietbeginn() == null) ? 0 : getMietbeginn().hashCode());
		result = prime * result + getDauer();
		result = prime * result + getNebenkosten();
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Mietvertrag))
			return false;
	
		Mietvertrag other = (Mietvertrag)obj;
	
		if(other.getVertragsnummer() != getVertragsnummer() ||
				!Helper.compareObjects(this.getDatum(), other.getDatum()) ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				other.getDauer() != getDauer() ||
				other.getNebenkosten() != getNebenkosten() ||
				!Helper.compareObjects(other.getMietbeginn(), getMietbeginn()))
		{
			return false;
		}
		
		return true;
	}
}
