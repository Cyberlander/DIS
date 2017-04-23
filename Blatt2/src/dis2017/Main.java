package dis2017;

import java.util.List;

import javax.naming.AuthenticationException;

import dis2017.data.Contract;
import dis2017.data.Estate;
import dis2017.data.EstateAgent;

public class Main {
	public static void main(String[] args) {
		showMainMenu();
	}

	private static void showMainMenu() {
		final int QUIT = 0;
		final int MENU_ESTATE_AGENT = 1;
		final int MENU_ESTATES = 2;
		final int MENU_CONTRACTS = 3;

		Menu mainMenu = new Menu("Main menu");
		mainMenu.addEntry("Estate Agent Management", MENU_ESTATE_AGENT);
		mainMenu.addEntry("Estate Management", MENU_ESTATES);
		mainMenu.addEntry("Contracts Management", MENU_CONTRACTS);
		mainMenu.addEntry("Quit", QUIT);

		while (true) {
			int response = mainMenu.show();

			switch (response) {
			case MENU_ESTATE_AGENT:
				boolean authenticated = passwordMenu();
				if (authenticated) {
					showEstateAgentMenu();
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
			case MENU_CONTRACTS:
				showContractsMenu();
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

	private static void showEstateAgentMenu() {
		final int BACK = 0;
		final int NEW_ESTATE_AGENT = 1;
		final int UPDATE_ESTATE_AGENT = 2;
		final int DELETE_ESTATE_AGENT = 3;

		Menu estateAgentMenu = new Menu("Estate Agent Management");
		estateAgentMenu.addEntry("New Agent", NEW_ESTATE_AGENT);
		estateAgentMenu.addEntry("Update Agent", UPDATE_ESTATE_AGENT);
		estateAgentMenu.addEntry("Delete Agent", DELETE_ESTATE_AGENT);
		estateAgentMenu.addEntry("Back to main menu", BACK);

		while (true) {
			int response = estateAgentMenu.show();

			switch (response) {
			case NEW_ESTATE_AGENT:
				newEstateAgent();
				break;
			case UPDATE_ESTATE_AGENT:
				updateEstateAgent();
				break;
			case DELETE_ESTATE_AGENT:
				deleteEstateAgent();
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
				newEstate(agent);
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

	private static void showContractsMenu() {
		final int BACK = 0;
		final int SIGN_CONTRACT = 1;
		final int LIST_CONTRACTS = 2;

		Menu contractsMenu = new Menu("Contract Management");
		contractsMenu.addEntry("Sign Contract", SIGN_CONTRACT);
		contractsMenu.addEntry("List Contracts", LIST_CONTRACTS);
		contractsMenu.addEntry("Back to main menu", BACK);

		while (true) {
			int response = contractsMenu.show();

			switch (response) {
			case SIGN_CONTRACT:
				signContract();
				break;
			case LIST_CONTRACTS:
				listContracts();
				break;
			case BACK:
				return;
			}
		}
	}

	private static void newEstateAgent() {
		EstateAgent estateAgent = new EstateAgent();

		estateAgent.setName(FormUtil.readString("Name"));
		estateAgent.setAddress(FormUtil.readString("Address"));
		estateAgent.setLogin(FormUtil.readString("Login"));
		estateAgent.setPassword(FormUtil.readString("Password"));
		estateAgent.save();

		System.out.println("Estate agent with the ID " + estateAgent.getId() + " was created.");
	}

	private static void updateEstateAgent() {
		int id = FormUtil.readInt("Estate agent ID");
		EstateAgent estateAgent = EstateAgent.load(id);

		estateAgent.setName(FormUtil.readString("Name"));
		estateAgent.setAddress(FormUtil.readString("Adresse"));
		estateAgent.setLogin(FormUtil.readString("Login"));
		estateAgent.setPassword(FormUtil.readString("Password"));
		estateAgent.save();

		System.out.println("Estate agent with the ID " + estateAgent.getId() + " was updated.");
	}

	private static void deleteEstateAgent() {
		int id = FormUtil.readInt("Estate Agent ID");
		EstateAgent estateAgent = EstateAgent.load(id);
		estateAgent.delete();

		System.out.println("Estate agent with the ID " + estateAgent.getId() + " was deleted.");
	}

	private static void newEstate(EstateAgent agent) {
		Estate estate = new Estate();
		estate.setCity(FormUtil.readString("City"));
		estate.setPostalCode(FormUtil.readInt("Postal code"));
		estate.setStreet(FormUtil.readString("Street"));
		estate.setStreetNumber(FormUtil.readString("Street Number"));
		estate.setSquareArea(FormUtil.readInt("Square Area"));
		estate.setEstateAgent(agent);
		estate.save();

		System.out.println("Estate with ID " + estate.getId() + " was created.");
	}

	private static void updateEstate() {
		int id = FormUtil.readInt("Estate ID");
		Estate estate = Estate.load(id);
		estate.setCity(FormUtil.readString("City"));
		estate.setPostalCode(FormUtil.readInt("Postal code"));
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

	private static void signContract() {
		Contract contract = new Contract();
		contract.setDate(FormUtil.readString("Date"));
		contract.setPlace(FormUtil.readString("Place"));
		contract.save();

		System.out.println("Contract with ID " + contract.getContractNo() + " was created.");
	}

	private static void listContracts() {
		List<Contract> contracts = Contract.getContracts();

		for (Contract contract : contracts) {
			System.out.println("Contract-No: " + contract.getContractNo() + " Place: " + contract.getPlace());
		}
	}
}
