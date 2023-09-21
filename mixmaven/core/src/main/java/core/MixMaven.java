package core;

import java.util.List;

import core.json.JsonFileToObject;
import core.json.ObjectToJsonFile;

public class MixMaven {

	private static String FILEPATH = "mixmaven/core/src/main/java/core/json/data.json";
	private static List<Drink> drinks;

	@SuppressWarnings("unchecked")
	public static List<Drink> loadDrinks() {
		try {
			drinks = (List<Drink>) JsonFileToObject.loadObjectFromJson(FILEPATH);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return drinks;
	}

	private static void saveDrinks() {
		ObjectToJsonFile.saveObjectToJsonFile(drinks, FILEPATH);
	}

	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static List<Drink> getDrinks() {
		return drinks;
	}

	public static void addDrink(Drink drink){
		drinks.add(drink);
		saveDrinks();
	}

	public static void addDrink(Drink drink, int index){
		drinks.add(index, drink);
		saveDrinks();
	}
}
