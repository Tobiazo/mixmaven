package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Drink implements Serializable{
	private String name;
	private List<Ingredient> ingredients;
	private double alcoholContent;


	public Drink(String name){
		if (validateDrink()){
			this.name = name;
			ingredients = new ArrayList<Ingredient>();
			alcoholContent = 0;
		} else throw new IllegalArgumentException("Invalid arguments for Drink");
	}

	public Drink(String name, ArrayList<Ingredient> ingredients){
		if (validateDrink()){
			this.name = name;
			this.ingredients = ingredients;
			calculateAlcoholContent();
		} else throw new IllegalArgumentException("Invalid arguments for Drink");
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
	
	private boolean validateDrink(){
		if(alcoholContent<=100) return true;
		return false;
	}

	private double calculateAlcoholContent(){
		double volume = 0;
		double alcoholVolume = 0;
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType()=="Mixer" || ingredient.getType()=="Alcohol"){
				volume+=ingredient.getAmount();
			}
			alcoholVolume += ingredient.getAlcohol()*ingredient.getAmount()/100;


		}
		return alcoholVolume/volume;
	}

	private void addIngredient(Ingredient Ingredient){
		ingredients.add(Ingredient);
	}

	private void removeIngredient(int index){
		ingredients.remove(index);
	}
}
