package core;

import java.util.List;

import java.util.ArrayList;
import java.io.File;

import core.json.UtilityJson;

/* Datahandler contains the File object and drinks field. Drinks is a list
 * containing all Drink objects.
 */
public class DataHandler {

	private static List<Drink> drinks = new ArrayList<>();
	private static File staticFile;
	
	/**
	 * Loads the saved drinks from file.
	 * @param file
	 * @return List<Drink> The loaded list of drinks
	 */
	public static List<Drink> loadDrinks(File file) {
		staticFile = file;
		try {
			drinks = UtilityJson.loadObjectFromJson(file);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return drinks;
	}
  	
	/**
	 * @return List<Drink> This returns currently loaded drinks
	 */
	public static List<Drink> getDrinks() {
		if (drinks.size() == 0) loadDrinks(staticFile);
		return drinks;
	}

	/**
	 * Adds drink to list and saves to file
	 * @param drink
	 * @param file
	 */
	public static void addDrink(Drink drink, File file){
		drinks.add(drink);
		saveDrinks(file);
	}

	/**
	 * Add drink to the saved file 
	 * @param drink
	 */
	public static void addDrink(Drink drink) {
		addDrink(drink, staticFile);
	}

	/**
	 * Saves all drinks in list to file
	 * @param file
	 */
	private static void saveDrinks(File file) {
		UtilityJson.saveObjectToJsonFile(drinks, file);
	}
}
