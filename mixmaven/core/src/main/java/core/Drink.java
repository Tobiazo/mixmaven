package core;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Drink implements Serializable {
	private String name;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private double alcoholContent = 0;

	/**
	 * Creates a drink without ingredients
	 * 
	 * @param name
	 */
	public Drink(String name) {
		this.name = name;
	}

	/**
	 * Creates drink with ingredients
	 * 
	 * @param name
	 * @param ingredients
	 */
	public Drink(String name, List<Ingredient> ingredients) {
		this(name);
		this.ingredients = ingredients;
		this.alcoholContent = calculateAlcoholContent();
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

	/**
	 * returns the alcolcontent in a drink by calculating the alcoholvolume (ABV*volume) divided by
	 * the total volume of all ingredients in the drink.
	 * 
	 * @return alcoholcontent of the drink
	 */
	private double calculateAlcoholContent() {
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

    /**
     * 
     * @param Ingredient 
     */
	public void addIngredient(Ingredient Ingredient) {
		ingredients.add(Ingredient);
		this.alcoholContent = calculateAlcoholContent();
	}

    /**
     * 
     * @param index of the ingredient to remove
     */
	public void removeIngredient(int index) {
		ingredients.remove(index);
		this.alcoholContent = calculateAlcoholContent();
	}

	@Override
	public String toString() {
		return "Drink [name=" + name + ", alcoholContent=" + alcoholContent + "]";
	}

}
