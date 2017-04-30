package de.dis2013.data;

import java.util.Date;

import de.dis2013.util.Helper;

/**
 * Vertrags-Bean
 */
public abstract class Vertrag {
	private int vertragsnummer = -1;
	private Date datum;
	private String ort;
	static int currentId = 0;
	int id;
	Person vertragspartner;
	
	public Vertrag() {
		this.id = currentId++;
	}
	
	public int getVertragsnummer() {
		return vertragsnummer;
	}
	public void setVertragsnummer(int vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getVertragspartner() {
		return vertragspartner;
	}

	public void setVertragspartner(Person vertragspartner) {
		this.vertragspartner = vertragspartner;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((getDatum() == null) ? 0 : getDatum().hashCode());
		result = prime * result + ((getOrt() == null) ? 0 : getOrt().hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Vertrag))
			return false;
	
		Vertrag other = (Vertrag)obj;
	
		if(other.getVertragsnummer() != getVertragsnummer() ||
				!Helper.compareObjects(this.getDatum(), other.getDatum()) ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()))
		{
			return false;
		}
		
		return true;
	}
}
