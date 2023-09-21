package ui;

import core.Constants;
import core.Drink;
import core.Ingredient;
import static core.Constants.SCENE_WIDTH;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class BrowseDrinksController {

    @FXML private AnchorPane browseDrinksPane;
    @FXML private VBox drinkContainer;
    List<Drink> drinks = new ArrayList<>();

    public void initialize() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("vodka", 40, 200, "ml", "alcohol"));
        ingredients.add(new Ingredient("redbull", 0, 200, "ml", "mixer"));
        Drink vodka = new Drink("vodkaredbulll", ingredients);
        // MixMaven.loadDrinks();
        // System.out.println(MixMaven.loadDarinks());
        // MixMaven.addDrink(vodka);
        // TODO: Link to data.json file
        List<Drink> drinks = new ArrayList<>();
        drinks.add(vodka);
        drinks.add(vodka);

        browseDrinksPane.setPrefSize(SCENE_WIDTH, Constants.CONTENT_HEIGHT);
        drinkContainer.setLayoutX((SCENE_WIDTH - drinkContainer.getPrefWidth()) / 2);

        for (int i = 0; i < drinks.size(); i++) {
            VBox drinkBox = new VBox();
            drinkBox.getStyleClass().add("drinkBox");

            Text title = new Text(drinks.get(i).getName());
            title.setFont(new Font(40));
            Text in = new Text(drinks.get(i).getIngredients().toString());


            drinkBox.getChildren().addAll(title, in);
            drinkContainer.getChildren().add(drinkBox);
        }


    }




}