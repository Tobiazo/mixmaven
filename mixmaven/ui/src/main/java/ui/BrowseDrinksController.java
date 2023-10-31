package ui;

import core.Constants;
import core.Drink;

import static core.Constants.SCENE_WIDTH;
import static core.Constants.FONT_SIZE_40;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
        drinks = mixMavenController.getMixMavenModel().getDrinks();
        System.out.println("SE HER:" + drinks);
        browseDrinksPane.setPrefSize(SCENE_WIDTH, Constants.CONTENT_HEIGHT);
        scrollPane.setLayoutX((SCENE_WIDTH - scrollPane.getPrefWidth()) / 2);

        //Generates a drinkbox for every drinks in MixMaven
        for (int i = 0; i < drinks.size(); i++) {
            VBox drinkBox = new VBox();
            drinkBox.getStyleClass().add("drinkBox");
            String drinkId = drinks.get(i).getId();

            Text drinkName = new Text(drinks.get(i).getName());
            drinkName.setFont(new Font(FONT_SIZE_40));

            //todo: Create an actual list
            StringBuilder str = new StringBuilder();
            drinks.get(i).getIngredients().stream()
                .forEach(ingredient -> str.append("     • " + ingredient.toString() + "\n"));

            Text ingredients = new Text(str.toString());

            Button deleteBtn = new Button("Delete Drink");
            deleteBtn.getStyleClass().add("drinkBtn");
            deleteBtn.setUserData(drinkId); // The drink to be deleted if the button is pressed

            Button editButton = new Button("Edit Drink");
            editButton.getStyleClass().add("drinkBtn");
            editButton.setUserData(drinkId); // The drink to be edited when the button is pressed

            deleteBtn.setOnAction(event -> {
                mixMavenController.getMixMavenModel().removeDrink((String) deleteBtn.getUserData());
                mixMavenController.getDataHandler().saveModel();
                mixMavenController.showBrowseDrinks();
            });

            editButton.setOnAction(event -> mixMavenController.showEditDrink((String) editButton.getUserData()));

            HBox buttonBox = new HBox();
            buttonBox.getStyleClass().add("buttonBox");

            buttonBox.getChildren().addAll(deleteBtn, editButton);
            drinkBox.getChildren().addAll(drinkName, ingredients, buttonBox);
            drinkContainer.getChildren().add(drinkBox);
        }
    }
}
