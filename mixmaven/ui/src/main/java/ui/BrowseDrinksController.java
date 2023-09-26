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
import javafx.scene.control.ScrollPane;
import java.util.List;

public final class BrowseDrinksController {

    @FXML private AnchorPane browseDrinksPane;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox drinkContainer;
    @FXML private Button addDrinkBtn;
    private List<Drink> drinks;
    private MixMavenController mixMavenController;

    public BrowseDrinksController(MixMavenController mixMavenController) {
        this.mixMavenController = mixMavenController;
    }

    public void initialize() {
        drinks = DataHandler.getDrinks();
        browseDrinksPane.setPrefSize(SCENE_WIDTH, Constants.CONTENT_HEIGHT);
        scrollPane.setLayoutX((SCENE_WIDTH - scrollPane.getPrefWidth()) / 2);

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
