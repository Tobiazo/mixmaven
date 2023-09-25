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

    /**
     * 
     * @param name
     * @param ingredients list of ingredient objects
     */
	public Drink(String name, List<Ingredient> ingredients){
		if (validateDrink()){
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

    /**
     * 
     * @return double, the calculated ABV of the drink
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

    /**
     * 
     * @param Ingredient 
     */
	public void addIngredient(Ingredient Ingredient) {
		ingredients.add(Ingredient);
	}

    /**
     * 
     * @param index of the ingredient to remove
     */
	public void removeIngredient(int index) {
		ingredients.remove(index);
	}

	@Override
	public String toString() {
		return "Drink [name=" + name + ", alcoholContent=" + alcoholContent + "]";
	}

}
