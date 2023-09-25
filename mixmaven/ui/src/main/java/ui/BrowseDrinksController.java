package ui;

import core.Constants;
import core.DataHandler;
import core.Drink;
import static core.Constants.SCENE_WIDTH;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.util.List;

public class BrowseDrinksController {

    @FXML private AnchorPane browseDrinksPane;
    @FXML private VBox drinkContainer;
    @FXML private Button addDrinkBtn;
    private List<Drink> drinks;

    public void initialize() {
        drinks = DataHandler.getDrinks();
        
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