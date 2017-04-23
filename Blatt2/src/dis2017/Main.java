package dis2017;

import dis2017.data.EstateAgent;

/**
 * Hauptklasse
 */
public class Main {
	/**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		showMainMenu();
	}

	/**
	 * Zeigt das Hauptmenü
	 */
	public static void showMainMenu() {
		// Menüoptionen
		final int MENU_MAKLER = 0;
		final int QUIT = 1;

		// Erzeuge Menü
		Menu mainMenu = new Menu("Hauptmenü");
		mainMenu.addEntry("Makler-Verwaltung", MENU_MAKLER);
		mainMenu.addEntry("Beenden", QUIT);

		// Verarbeite Eingabe
		while (true) {
			int response = mainMenu.show();

			switch (response) {
			case MENU_MAKLER:
				boolean authenticated = passwordMenu();
				if(authenticated){
					showMaklerMenu();
				}
				break;
			case QUIT:
				return;
			}
		}
	}

	private static boolean passwordMenu() {
		String password = FormUtil.readString("Password");
		System.out.println(password);
		if(!password.equals("1234")){
			System.out.println("Wrong password");
			return false;
		}
		return true;
	}

	/**
	 * Zeigt die Maklerverwaltung
	 */
	public static void showMaklerMenu() {
		// Menüoptionen
		final int BACK = 0;
		final int NEW_MAKLER = 1;
		final int UPDATE_MAKLER = 2;
		final int DELETE_MAKLER = 3;

		// Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("Makler-Verwaltung");
		maklerMenu.addEntry("Neuer Makler", NEW_MAKLER);
		maklerMenu.addEntry("Makler aktualisieren", UPDATE_MAKLER);
		maklerMenu.addEntry("Makler löschen", DELETE_MAKLER);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);

		// Verarbeite Eingabe
		while (true) {
			int response = maklerMenu.show();

			switch (response) {
			case NEW_MAKLER:
				newMakler();
				break;
			case UPDATE_MAKLER:
				updateMakler();
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
	 * Legt einen neuen Makler an, nachdem der Benutzer die entprechenden Daten
	 * eingegeben hat.
	 */
	public static void newMakler() {
		EstateAgent m = new EstateAgent();

		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();

		System.out.println("Makler mit der ID " + m.getId() + " wurde erzeugt.");
	}

	public static void updateMakler() {
		int id = FormUtil.readInt("Makler ID");
		EstateAgent makler = EstateAgent.load(id);

		makler.setName(FormUtil.readString("Name"));
		makler.setAddress(FormUtil.readString("Adresse"));
		makler.setLogin(FormUtil.readString("Login"));
		makler.setPassword(FormUtil.readString("Passwort"));
		makler.save();

		System.out.println("Makler mit der ID " + makler.getId() + " wurde aktualisiert.");
	}

	public static void deleteMakler() {
		int id = FormUtil.readInt("Makler ID");
		EstateAgent makler = EstateAgent.load(id);
		makler.delete();

		System.out.println("Makler mit der ID " + makler.getId() + " wurde gelöscht.");
	}
}
