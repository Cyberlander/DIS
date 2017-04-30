package de.dis2013.menu;

import java.util.Iterator;
import java.util.Set;

import de.dis2013.data.Wohnung;

/**
 * Ein kleines Menü, dass alle Wohnungen aus einem Set zur Auswahl anzeigt
 */
public class AppartmentSelectionMenu extends Menu {
	public static final int BACK = -1;
	
	public AppartmentSelectionMenu(String title, Set<Wohnung> wohnungen) {
		super(title);
		
		Iterator<Wohnung> it = wohnungen.iterator();
		while(it.hasNext()) {
			Wohnung w = it.next();
			addEntry(w.getStrasse()+" "+w.getHausnummer()+", "+w.getPlz()+" "+w.getOrt(), w.getId());
		}
		addEntry("Zurück", BACK);
	}
}
