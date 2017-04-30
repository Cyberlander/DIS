package de.dis2013.menu;

import java.util.Iterator;
import java.util.Set;

import de.dis2013.data.Makler;

/**
 * Ein kleines Menü, dass alle Makler aus einem Set zur Auswahl anzeigt
 */
public class MaklerSelectionMenu extends Menu {
	public static final int BACK = -1;
	
	public MaklerSelectionMenu(String title, Set<Makler> makler) {
		super(title);
		
		Iterator<Makler> it = makler.iterator();
		while(it.hasNext()) {
			Makler m = it.next();
			addEntry(m.getName(), m.getId());
		}
		addEntry("Zurück", BACK);
	}
}
