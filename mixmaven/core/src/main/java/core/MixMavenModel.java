package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

public class MixMavenModel {
    private List<Drink> drinks;

    //to-do:
    //javadoc

    public MixMavenModel(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public final void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    /**
     *
     * @param drink
     */
    public void addDrink(Drink drink) {
        drink.setId(UUID.randomUUID().toString());
        drinks.add(drink);
    }

    public final List<Drink> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public final Drink getDrink(String drinkId) {
        return drinks.stream()
        .filter(e -> e.getId().equals(drinkId))
        .collect(Collectors.toList())
        .get(0);
    }

    public final void removeDrink(String drinkId) {
        drinks.remove(getDrink(drinkId));
    }

    public final void replaceDrink(String oldDrinkId, Drink newDrink) {
        drinks.set(drinks.indexOf(getDrink(oldDrinkId)), newDrink);
    }
}
