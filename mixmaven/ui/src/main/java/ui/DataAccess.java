package ui;

import java.util.List;
import core.Drink;
import core.MixMavenModel;

public interface DataAccess {

    List<Drink> getDrinks();

    MixMavenModel getModel();

    void deleteDrink(String drinkId);

    void addDrink(Drink drink);

    void editDrink(String oldDrinkId, Drink newDrink);

    void setFilePath(String path);

}
