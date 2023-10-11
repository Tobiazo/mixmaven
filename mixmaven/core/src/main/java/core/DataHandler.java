package core;

import core.json.UtilityJson;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * Datahandler contains the File object and drinks field. Drinks is a list containing all Drink
 * objects
 */
public class DataHandler {

	private static File dataFile;
	private static List<Drink> drinks = new ArrayList<>();

	/**
	 * Loads the saved drinks from file.
	 *
	 * @param file
	 * @return the loaded list of drinks
	 */
	public static List<Drink> loadDrinks(File file) {
		dataFile = file;
		drinks = UtilityJson.loadObjectFromJson(file);
		// try {
		// } catch (NullPointerException e) {
		// 	e.printStackTrace();
		// }
		return new ArrayList<>(drinks);
	}

	/**
	 * @return currently loaded drinks
	 */
	public static List<Drink> getDrinks() {
		if (drinks.size() == 0) loadDrinks(dataFile);
		return new ArrayList<>(drinks);
	}

	/**
	 * Adds drink to list and saves to file.
	 *
	 * @param drink
	 * @param file
	 */
	public static void addDrink(Drink drink, File file) {
		drinks.add(drink);
		saveDrinks(file);
	}

	/**
	 * Add drink to the saved file.
	 *
	 * @param drink
	 */
	public static void addDrink(Drink drink) {
		addDrink(drink, dataFile);
	}
	/**
	 * Removes the drink at index.
	 * @param i
	 */
	public static void removeDrink(int i) {
		drinks.remove(i);
		saveDrinks(dataFile);
	}
	/**
	 * Replaces the drink at index i with the given Drink.
	 * @param i
	 * @param drink
	 */
	public static void replaceDrink(int i, Drink drink) {
		drinks.set(i, drink);
		saveDrinks(dataFile);
	}

	/**
	 * Saves all drinks in list to file.
	 *
	 * @param file
	 */
	private static void saveDrinks(File file) {
		UtilityJson.saveObjectToJsonFile(drinks, file);
	}

	public static void saveNewUser(File file, User user) {
		UtilityJson.saveObjectToJsonFile(user, file);
	}

	public static boolean validateLogin(File file, String username, String password) {
		User user = UtilityJson.loadUserFromJson(file, username);
		return user.checkPassword(password);
	}


}
