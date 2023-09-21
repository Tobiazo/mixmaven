package core;

import java.util.ArrayList;
import java.util.List;

import core.json.JsonFileToObject;
import core.json.ObjectToJsonFile;

/* Datahandler contains the filepath and drinks field. Drinks is a list
 * containing all Drink objects.
 */
public class DataHandler {

	private static List<Drink> drinks = new ArrayList<>();
	private static String filePath;

  /* loaddrinks retrieves an object from the json file
   * The returned value is then casted to a List containing multiple Drink objects
   * and assigned to the drinks field.
   * This method is called upon startup of the application.‡
   */
	public static List<Drink> loadDrinks(String s) {
		filePath = s;
		System.out.println("HER!!!!!" + filePath);
		try {
			drinks = JsonFileToObject.loadObjectFromJson(s);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return drinks;
	}
  /* Overwrites the json file with the current content of the drinks field. */
	private static void saveDrinks() {
		ObjectToJsonFile.saveObjectToJsonFile(drinks, filePath);
	}

	public static String getFILEPATH() {
		return filePath;
	}

	public static List<Drink> getDrinks() {
		return drinks;
	}

	public static void addDrink(Drink drink){
		System.out.println(filePath);
		drinks.add(drink);
		saveDrinks();
	}

	public static void addDrink(Drink drink, int index){
		drinks.add(index, drink);
		saveDrinks();
	}

  public static void main(String[] args){
    Drink gt = new Drink("GT");

    DataHandler.addDrink(gt);
    //System.out.println(drinks);
    System.out.println(DataHandler.getDrinks());
  }
}
