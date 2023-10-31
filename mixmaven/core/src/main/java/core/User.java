package core;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Drink> drinks;
    private String userName;
    //private String password;

    public User(String username, String password) throws IllegalArgumentException {
        if (!validatePassword(password)) {
            System.out.println("Password is invalid");
            throw new IllegalArgumentException();
        }
        this.userName = username;
        //this.password = password;
        drinks = new ArrayList<>();
    }

    public final boolean validatePassword(String password) {
        return true;
    }

    public final String getUserName() {
        return userName;
    }

    /**
	 * Loads the saved drinks from file.
	 *
	 * @return the loaded list of drinks
	 */
/* 	public List<Drink> loadDrinks() {
		drinks = UtilityJson.loadObjectFromJson(dataFile);
		return new ArrayList<>(drinks);
	} */

	/**
	 * @return currently loaded drinks
	 */
/* 	public List<Drink> getDrinks() {
		if (drinks.size() == 0) loadDrinks();
		return new ArrayList<>(drinks);
	} */

	/**
	 * Adds drink to list and saves to file.
	 *
	 * @param drink
	 */
	public void addDrink(Drink drink) {
		drinks.add(drink);
	}


	/**
	 * Removes the drink at index.
	 * @param i
	 */
	public void removeDrink(int i) {
		drinks.remove(i);
	}

	/**
	 * Replaces the drink at index i with the given Drink.
	 * @param i
	 * @param drink
	 */
	public void replaceDrink(int i, Drink drink) {
		drinks.set(i, drink);
	}

    public final List<Drink> getDrinks() {
        return new ArrayList<>(drinks);
    }

}
