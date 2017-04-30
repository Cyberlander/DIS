package de.dis2013.editor;

import de.dis2013.core.ImmoService;
import de.dis2013.data.Makler;
import de.dis2013.menu.MaklerSelectionMenu;
import de.dis2013.menu.Menu;
import de.dis2013.util.FormUtil;

/**
 * Klasse für die Menüs zur Verwaltung von Immobilien
 */
public class MaklerEditor {
	///Immobilienservice, der genutzt werden soll
	private ImmoService service;
	
	public MaklerEditor(ImmoService service) {
		this.service = service;
	}
	
	/**
	 * Zeigt die Maklerverwaltung
	 */
	public void showMaklerMenu() {
		//Menüoptionen
		final int NEW_MAKLER = 0;
		final int EDIT_MAKLER = 1;
		final int DELETE_MAKLER = 2;
		final int BACK = 3;
		
		//Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("Makler-Verwaltung");
		maklerMenu.addEntry("Neuer Makler", NEW_MAKLER);
		maklerMenu.addEntry("Makler bearbeiten", EDIT_MAKLER);
		maklerMenu.addEntry("Makler löschen", DELETE_MAKLER);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_MAKLER:
					newMakler();
					break;
				case EDIT_MAKLER:
					editMakler();
					break;
				case DELETE_MAKLER:
					deleteMakler();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Legt einen neuen Makler an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	public void newMakler() {
		Makler m = new Makler();
		
		m.setName(FormUtil.readString("Name"));
		m.setAdresse(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPasswort(FormUtil.readString("Passwort"));
		service.addMakler(m);
		
		System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
	}
	
	/**
	 * Berarbeitet einen Makler, nachdem der Benutzer ihn ausgewählt hat
	 */
	public void editMakler() {
		//Menü zum selektieren des Maklers
		Menu maklerSelectionMenu = new MaklerSelectionMenu("Makler editieren", service.getAllMakler());
		int id = maklerSelectionMenu.show();
		
		//Falls nicht "zurück" gewählt, Makler bearbeiten
		if(id != MaklerSelectionMenu.BACK) {
			//Makler laden
			Makler m = service.getMaklerById(id);
			System.out.println("Makler "+m.getName()+" wird bearbeitet. Leere Felder bleiben unverändert.");
			
			//Neue Daten abfragen
			String new_name = FormUtil.readString("Name ("+m.getName()+")");
			String new_address = FormUtil.readString("Adresse ("+m.getAdresse()+")");
			String new_login = FormUtil.readString("Login ("+m.getLogin()+")");
			String new_password = FormUtil.readString("Passwort ("+m.getPasswort()+")");
			
			//Neue Daten setzen
			if(!new_name.equals(""))
				m.setName(new_name);
			if(!new_address.equals(""))
				m.setAdresse(new_address);
			if(!new_login.equals(""))
				m.setLogin(new_login);
			if(!new_password.equals(""))
				m.setPasswort(new_password);
		}
	}
	
	/**
	 * Löscht einen Makler, nachdem der Benutzer
	 * ihn ausgewählt hat.
	 */
	public void deleteMakler() {
		//Menü zum selektieren des Maklers
		Menu maklerSelectionMenu = new MaklerSelectionMenu("Makler löschen", service.getAllMakler());
		int id = maklerSelectionMenu.show();
		
		//Makler löschen falls nicht "zurück" ausgewählt wurde
		if(id != MaklerSelectionMenu.BACK) {
			Makler m = service.getMaklerById(id);
			service.deleteMakler(m);
		}
	}
}
