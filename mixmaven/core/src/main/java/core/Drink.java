package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* Dataoriented class possibly containing a list of ingredientobjects */
public class Drink implements Serializable {
	private String name;
	private List<Ingredient> ingredients;
	private double alcoholContent;

	public Drink() {
		ingredients = new ArrayList<Ingredient>();
		alcoholContent = 0;
	}

	public Drink(String name) {
		if (validateDrink()) {
			this.name = name;
			ingredients = new ArrayList<Ingredient>();
			alcoholContent = 0;
		} else
			throw new IllegalArgumentException("Invalid arguments for Drink");
	}

	public Drink(String name, ArrayList<Ingredient> ingredients) {
		if (validateDrink()) {
			this.name = name;
			this.ingredients = ingredients;
			calculateAlcoholContent();
		} else
			throw new IllegalArgumentException("Invalid arguments for Drink");
	}

	public String getName() {
		return name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public double getAlcoholContent() {
		return alcoholContent;
	}

	private boolean validateDrink() {
		if (alcoholContent <= 100)
			return true;
		return false;
	}

  /* 
   * returns the alcolcontent in a drink by calculating the alcoholvolume (ABV*volume) 
   * divided by the total volume of all ingredients in the drink.
   */
	public double calculateAlcoholContent() {
		double volume = 0;
		double alcoholVolume = 0;
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType() == "mixer" || ingredient.getType() == "alcohol") {
				volume += ingredient.getAmount();
			}
			alcoholVolume += ingredient.getAlcoholPercentage() * ingredient.getAmount() / 100;

		}
		return alcoholVolume / volume;
	}

	public void addIngredient(Ingredient Ingredient) {
		ingredients.add(Ingredient);
	}

	public void removeIngredient(int index) {
		ingredients.remove(index);
	}

	@Override
	public String toString() {
		return "Drink [name=" + name + ", alcoholContent=" + alcoholContent + "]";
	}

}
