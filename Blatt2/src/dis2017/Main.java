package dis2017;

import javax.naming.AuthenticationException;

import dis2017.data.Estate;
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
	private static void showMainMenu() {
		final int QUIT = 0;
		final int MENU_MAKLER = 1;
		final int MENU_ESTATES = 2;

		// Erzeuge Menü
		Menu mainMenu = new Menu("Main menu");
		mainMenu.addEntry("Estate Agent Management", MENU_MAKLER);
		mainMenu.addEntry("Estate Management", MENU_ESTATES);
		mainMenu.addEntry("Quit", QUIT);

		// Verarbeite Eingabe
		while (true) {
			int response = mainMenu.show();

			switch (response) {
			case MENU_MAKLER:
				boolean authenticated = passwordMenu();
				if (authenticated) {
					showMaklerMenu();
				}
				break;
			case MENU_ESTATES:
				try {
					EstateAgent estateAgent = authenticateAgent();
					showEstateMenu(estateAgent);
				} catch (AuthenticationException e) {
					System.out.println("Authentication failed");
				}
				break;
			case QUIT:
				return;
			}
		}
	}

	private static EstateAgent authenticateAgent() throws AuthenticationException {
		String login = FormUtil.readString("Login");
		String password = FormUtil.readString("Password");

		EstateAgent estateAgent = new EstateAgent();
		estateAgent.setLogin(login);
		estateAgent.setPassword(password);
		boolean authenticated = estateAgent.authenticate();

		if (authenticated) {
			return estateAgent;
		}

		throw new AuthenticationException();
	}

	private static boolean passwordMenu() {
		String password = FormUtil.readString("Password");
		System.out.println(password);
		if (!password.equals("1234")) {
			System.out.println("Wrong password");
			return false;
		}
		return true;
	}

	/**
	 * Zeigt die Maklerverwaltung
	 */
	private static void showMaklerMenu() {
		// Menüoptionen
		final int BACK = 0;
		final int NEW_MAKLER = 1;
		final int UPDATE_MAKLER = 2;
		final int DELETE_MAKLER = 3;

		// Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("Estate Agent Management");
		maklerMenu.addEntry("New Agent", NEW_MAKLER);
		maklerMenu.addEntry("Update Agent", UPDATE_MAKLER);
		maklerMenu.addEntry("Delete Agent", DELETE_MAKLER);
		maklerMenu.addEntry("Back to main menu", BACK);

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

	private static void showEstateMenu(EstateAgent agent) {
		final int BACK = 0;
		final int NEW_ESTATE = 1;
		final int UPDATE_ESTATE = 2;
		final int DELETE_ESTATE = 3;

		Menu estateMenu = new Menu("Estate Managment");
		estateMenu.addEntry("New Estate", NEW_ESTATE);
		estateMenu.addEntry("Update Estate", UPDATE_ESTATE);
		estateMenu.addEntry("Delete Estate", DELETE_ESTATE);
		estateMenu.addEntry("Back to main menu", BACK);

		while (true) {
			int response = estateMenu.show();

			switch (response) {
			case NEW_ESTATE:
				newEstate();
				break;
			case UPDATE_ESTATE:
				updateEstate();
				break;
			case DELETE_ESTATE:
				deleteEstate();
				break;
			case BACK:
				return;
			}
		}
	}

	private static void newMakler() {
		EstateAgent m = new EstateAgent();

		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();

		System.out.println("Makler mit der ID " + m.getId() + " wurde erzeugt.");
	}

	private static void updateMakler() {
		int id = FormUtil.readInt("Makler ID");
		EstateAgent makler = EstateAgent.load(id);

		makler.setName(FormUtil.readString("Name"));
		makler.setAddress(FormUtil.readString("Adresse"));
		makler.setLogin(FormUtil.readString("Login"));
		makler.setPassword(FormUtil.readString("Passwort"));
		makler.save();

		System.out.println("Makler mit der ID " + makler.getId() + " wurde aktualisiert.");
	}

	private static void deleteMakler() {
		int id = FormUtil.readInt("Makler ID");
		EstateAgent makler = EstateAgent.load(id);
		makler.delete();

		System.out.println("Makler mit der ID " + makler.getId() + " wurde gelöscht.");
	}

	private static void newEstate() {
		Estate estate = new Estate();
		estate.setCity(FormUtil.readString("City"));
		estate.setStreet(FormUtil.readString("Street"));
		estate.setStreetNumber(FormUtil.readString("Street Number"));
		estate.setSquareArea(FormUtil.readInt("Square Area"));
		estate.save();

		System.out.println("Estate with ID " + estate.getId() + " was created.");
	}

	private static void updateEstate() {
		int id = FormUtil.readInt("Estate ID");
		Estate estate = Estate.load(id);
		estate.setCity(FormUtil.readString("City"));
		estate.setStreet(FormUtil.readString("Street"));
		estate.setStreetNumber(FormUtil.readString("Street Number"));
		estate.setSquareArea(FormUtil.readInt("Square Area"));
		estate.save();

		System.out.println("Estate with the id " + estate.getId() + " was updated.");
	}

	private static void deleteEstate() {
		int id = FormUtil.readInt("Estate ID");
		Estate estate = Estate.load(id);
		estate.delete();

		System.out.println("Estate with the id " + estate.getId() + " was deleted.");
	}
}
