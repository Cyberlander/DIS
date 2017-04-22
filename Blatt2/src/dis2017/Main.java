package dis2017;

import dis2017.data.Makler;

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
				showMaklerMenu();
				break;
			case QUIT:
				return;
			}
		}
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
		Makler m = new Makler();

		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();

		System.out.println("Makler mit der ID " + m.getId() + " wurde erzeugt.");
	}

	public static void updateMakler() {
		int id = FormUtil.readInt("Makler ID");
		Makler makler = Makler.load(id);

		makler.setName(FormUtil.readString("Name"));
		makler.setAddress(FormUtil.readString("Adresse"));
		makler.setLogin(FormUtil.readString("Login"));
		makler.setPassword(FormUtil.readString("Passwort"));
		makler.save();

		System.out.println("Makler mit der ID " + makler.getId() + " wurde aktualisiert.");
	}

	public static void deleteMakler() {
		int id = FormUtil.readInt("Makler ID");
		Makler makler = Makler.load(id);
		System.out.println(makler.getName());
	}
}
